from BackDjango.secret import Connection
import pandas as pd
from sklearn.metrics.pairwise import cosine_similarity
import operator

import os

os.environ.setdefault("DJANGO_SETTINGS_MODULE", "BackDjango.settings")

import django
django.setup()

from recommendation.models import UserBasedCFModel, ItemBasedCFModel

class UserBasedCF:
    def __init__(self):
        self.connection = Connection()
        self.cursor = self.connection.cursor()
        self.sql = "select user_email, book_isbn, book_grade from Book_Grade"
        self.cursor.execute(self.sql)
        self.result = self.cursor.fetchall()
        self.book_rating = pd.DataFrame(self.result)

    # 사용자 기반 필터링
    def user_based_cf(self):
        title_user = self.book_rating.pivot_table('book_grade', index='user_email', columns='book_isbn')
        title_user.fillna(0, inplace=True)

        # 유저와 유저 간의 유사도
        user_based_collab = cosine_similarity(title_user, title_user)
        user_based_collab = pd.DataFrame(user_based_collab, index=title_user.index, columns=title_user.index)

        user_book_dic = {}
        for target_user in user_based_collab.columns:

            sim_users = user_based_collab.sort_values(by=target_user, ascending=False).index[1:11]

            title_user_T = title_user.T
            best = []
            for i in sim_users:
                # 유사도가 높은 10명의 사용자들이 평가점수를 높게 주었던 item list(상위 10개)를 가져온다.
                # user가 평가하지 않았던 아이템을 추천해야한다.
                result_sorted = title_user_T.loc[:, i][(title_user_T.loc[:, target_user] == 0)].sort_values(
                    ascending=False)
                best.append(result_sorted.index[:10].tolist())

            most_common = {}
            for i in range(len(best)):
                for j in best[i]:
                    # dic에 있다면 count 추가
                    if j in most_common:
                        most_common[j] += 1
                    # 아니라면 count 1
                    else:
                        most_common[j] = 1

            # 몇명이나 선택했냐를 기준으로 판단
            # key=operator.itemgetter(1) - 튜플의 2번째 요소로 sort
            # reverse=True 역순
            sorted_list = sorted(most_common.items(), key=operator.itemgetter(1), reverse=True)
            recomm_list = [x[0] for x in sorted_list][:20]
            user_book_dic[target_user] = recomm_list
        return user_book_dic

    def save_list(self):
        user_book_dic = self.user_based_cf()

        UserBasedCFModel.objects.all().delete()

        for user_email, book_isbn_list in user_book_dic.items():
            for book_isbn in book_isbn_list:
                UserBasedCFModel(
                    user_email=user_email,
                    book_isbn=book_isbn
                ).save()

# 아이템 기반 필터링
class ItemBasedCF:
    def __init__(self):
        self.connection = Connection()
        self.cursor = self.connection.cursor()

        self.sql = "SELECT * FROM LookBookDB.Book"
        self.cursor.execute(self.sql)
        self.result_book = self.cursor.fetchall()
        self.df_book = pd.DataFrame(self.result_book)

        self.sql_bg = "SELECT * FROM LookBookDB.Book_Grade"
        self.cursor.execute(self.sql_bg)
        self.result_bookgrade = self.cursor.fetchall()
        self.df_bookgrade = pd.DataFrame(self.result_bookgrade)

    # # Create Pivot Table
    def item_based_cf(self):
        rating_df = self.df_bookgrade.pivot_table('book_grade', index='book_isbn', columns='user_email').fillna(0)
        # Create Cosine Silmilarity Dataframe
        item_sim = cosine_similarity(rating_df, rating_df)
        book_sim_df = pd.DataFrame(data=item_sim, index=rating_df.index, columns=rating_df.index)

        # Create Best Top 10 Book list (혹시 몰라서 10개 가져왔어요!)
        bk_like_df = self.df_book[['book_isbn','book_like_cnt']]
        bk_like_df = bk_like_df.sort_values(by=['book_like_cnt'], axis=0, ascending=False)

        best_book_list = bk_like_df['book_isbn'].tolist()[:10]

        # Result) Top1 도서와 유사한 도서 10권 추천 리스트
        item_book_df = book_sim_df[best_book_list[0]].sort_values(ascending=False)[1:21]

        return item_book_df

    def save_list(self):
        item_book_df = self.item_based_cf()
        item_book_dic = item_book_df.to_dict()

        item_book_dic = sorted(item_book_dic.items(), key=lambda x: x[1])

        ItemBasedCFModel.objects.all().delete()

        for book_isbn in item_book_dic:
            ItemBasedCFModel(
                book_isbn=book_isbn[0]
            ).save()


if __name__ == "__main__":
    UserBasedCF().save_list()
    ItemBasedCF().save_list()
    # print(UserBasedCF().save_list())