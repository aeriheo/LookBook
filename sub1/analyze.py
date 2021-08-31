from parse import load_dataframes
import pandas as pd
import shutil


def sort_stores_by_score(dataframes, n=20, min_reviews=30):
    """
    Req. 1-2-1 각 음식점의 평균 평점을 계산하여 높은 평점의 음식점 순으로 `n`개의 음식점을 정렬하여 리턴합니다
    Req. 1-2-2 리뷰 개수가 `min_reviews` 미만인 음식점은 제외합니다.
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )
    scores_group = stores_reviews.groupby(["store", "store_name"])
    scores = scores_group.mean()
    test = scores_group.count()
    reviews_cnt = test.loc[test.score > min_reviews, 'user']
    result = pd.merge(left=reviews_cnt, right=scores, how="inner", on=["store", "store_name"]).sort_values(by=["score"], ascending=[False])["score"].round(2)

    return result.head(n=n).reset_index()


def get_most_reviewed_stores(dataframes, n=20):
    """
    Req. 1-2-3 가장 많은 리뷰를 받은 `n`개의 음식점을 정렬하여 리턴합니다
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )
    scores_group = stores_reviews.groupby(["store", "store_name"])
    scores = scores_group.mean()
    test = scores_group.count().sort_values(by='score', ascending=False)["content"]
    result = pd.merge(left=test, right=scores, how="inner", on=["store", "store_name"]).sort_values(by='content', ascending=False)

    return result.head(n=n).reset_index()


def get_most_active_users(dataframes, n=20):
    """
    Req. 1-2-4 가장 많은 리뷰를 작성한 `n`명의 유저를 정렬하여 리턴합니다.
    """
    users_group = dataframes["users"].groupby(["id"])
    users_result = users_group.count().sort_values(by='review_id', ascending=False)["review_id"]

    return users_result.head(n=n).reset_index()



def main():
    data = load_dataframes()

    term_w = shutil.get_terminal_size()[0] - 1
    separater = "-" * term_w

    stores_most_scored = sort_stores_by_score(data)
    most_reviewed_stores = get_most_reviewed_stores(data)
    most_reviewed_user = get_most_active_users(data)

    print("[최고 평점 음식점]")
    print(f"{separater}\n")
    for i, store in stores_most_scored.iterrows():
        print(
            "{rank}위: {store}({score}점)".format(
                rank=i + 1, store=store.store_name, score=store.score
            )
        )
    print(f"\n{separater}\n\n")

    print("[많은 리뷰를 받은 음식점]")
    print(f"{separater}\n")
    for i, store in most_reviewed_stores.iterrows():
        print(
            "{rank}위: {store}({content}개)".format(
                rank=i + 1, store=store.store_name, content=store.content
            )
        )
    print(f"\n{separater}\n\n")

    print("[많은 리뷰를 한 유저]")
    print(f"{separater}\n")
    for i, user in most_reviewed_user.iterrows():
        print(
            "{rank}위: {id}님 ({review}개)".format(
                rank=i + 1, id=user.id, review=user.review_id
            )
        )
    print(f"\n{separater}\n\n")


if __name__ == "__main__":
    main()
