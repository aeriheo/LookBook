from BackDjango.secret import Connection
import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
import operator
import warnings

warnings.filterwarnings(action='ignore')

import os

os.environ.setdefault("DJANGO_SETTINGS_MODULE", "BackDjango.settings")

import django

django.setup()

from user.models import User
from book.models import Book
from recommendation.models import UserBasedCFModel, UserPredictedGradeModel


class GetUserEmail:
    def getUserEmail(self, user_email):
        self.user_email = user_email
        user = User.objects.get(user_email=user_email)
        print(user.user_nickname)
        return self.user_email


class UpdateUserBasedCF:
    def __init__(self, user_email):
        self.user_email = user_email
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

        sim_users = user_based_collab.sort_values(by=self.user_email, ascending=False).index[1:11]

        title_user_T = title_user.T
        best = []
        for i in sim_users:
            # 유사도가 높은 10명의 사용자들이 평가점수를 높게 주었던 item list(상위 10개)를 가져온다.
            # user가 평가하지 않았던 아이템을 추천해야한다.
            result_sorted = title_user_T.loc[:, i][(title_user_T.loc[:, self.user_email] == 0)].sort_values(
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
        user_book_dic[self.user_email] = recomm_list

        return user_book_dic

    def save_list(self):
        user_book_dic = self.user_based_cf()

        for user_email, book_isbn_list in user_book_dic.items():
            book_isbn_list.reverse()
            for book_isbn in book_isbn_list:
                print(user_email, book_isbn)
                # UserBasedCFModel(
                #     user_email=User.objects.get(user_email=user_email),
                #     book_isbn=Book.objects.get(book_isbn=book_isbn)
                # )

class UpdateUserPredictedGrade:
    def __init__(self, user_email):
        self.user_email = user_email
        self.connection = Connection()
        self.cursor = self.connection.cursor()
        self.sql = "SELECT * FROM LookBookDB.Book_Grade"
        self.cursor.execute(self.sql)
        self.result_bookgrade = self.cursor.fetchall()
        self.df_bookgrade = pd.DataFrame(self.result_bookgrade)

        # Create Pivot Table
        self.rating_df = self.df_bookgrade.pivot_table('book_grade', index='book_isbn', columns='user_email').fillna(0)

        # Create Cosine Silmilarity Dataframe
        self.item_sim = cosine_similarity(self.rating_df, self.rating_df)
        self.book_sim_df = pd.DataFrame(data=self.item_sim, index=self.rating_df.index, columns=self.rating_df.index)

        # def. User 별 도서 예상 평점 계산

    def predict_rating_topsim(self, ratings_arr, item_sim_arr, n=5):
        predict_rating_np = np.zeros(ratings_arr.shape)

        for col in range(ratings_arr.shape[1]):
            # 유사도가 큰 순으로 n개의 데이터 행렬의 index 반환
            top_n_items = [np.argsort(item_sim_arr[:, col])[:-n - 1:-1]]

            # 각 item 별로 전체 사용자들의 예측 평점
            for row in range(ratings_arr.shape[0]):
                predict_rating_np[row, col] = item_sim_arr[col, :][top_n_items].dot(
                    ratings_arr[row, :][top_n_items].T)
                predict_rating_np[row, col] /= np.sum(item_sim_arr[col, :][top_n_items])

        return predict_rating_np

        # def. User "000"이(가) 읽은 도서 리스트

    def get_user_book_list(self, user_email):
        bg_user = self.df_bookgrade.loc[self.df_bookgrade['user_email'] == user_email]
        bg_user_book = bg_user['book_isbn']
        user_book_list = bg_user_book.tolist()

        return user_book_list

    def make_predicted_dic(self):
        # 모든 사용자 리스트
        user_list = self.rating_df.columns.tolist()

        # 모든 사용자에 대해 모든 도서 예상 평점 구하기
        rating_df_T = self.rating_df.transpose()
        predict_rating_np = self.predict_rating_topsim(rating_df_T.values, self.book_sim_df.values, n=5)

        # 예상 평점 np를 dataframe으로 변환
        predict_rating_df = pd.DataFrame(data=predict_rating_np, index=rating_df_T.index, columns=rating_df_T.columns)

        # 결과 딕셔너리 생성
        user_book_dic = {}

        # 사용자 한명한명에 대해 리스트 저장하기

        # 해당 사용자의 예상 평점을 높은 순서대로 가져오기
        user_predict_rating_df = predict_rating_df.loc[self.user_email, :].sort_values(ascending=False)
        # 예상 평점 df를 list로 변환
        user_predict_rating_list = user_predict_rating_df.index.tolist()
        # 사용자가 읽은 도서 리스트 받아오기
        target_user_book_list = self.get_user_book_list(self.user_email)

        # 예상 평점 리스트에서 사용자가 이미 읽은 도서 제거
        not_read_rating_list = [i for i in user_predict_rating_list if i not in target_user_book_list][:20]

        # Result) 모든 User에 대한 상위 예상 평점 도서 리스트 추천
        user_book_dic[self.user_email] = not_read_rating_list

        return user_book_dic

    def save_list(self):
        user_predicted_book_dic = self.make_predicted_dic()

        for user_email, book_isbn_list in user_predicted_book_dic.items():
            book_isbn_list.reverse()
            for book_isbn in book_isbn_list:
                print(user_email, book_isbn)
                # UserPredictedGradeModel(
                #     user_email=User.objects.get(user_email=user_email),
                #     book_isbn=Book.objects.get(book_isbn=book_isbn)
                # ).save()
