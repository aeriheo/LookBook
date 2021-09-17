import itertools
from collections import Counter

from parse import load_dataframes
import pandas as pd
import scipy.sparse as sparse
import scipy.stats as stats
import numpy as np

def get_user_store_matrix(dataframes):
    """
    Req. 4-1 유저와 음식점을 축으로 하고 평점을 값으로 갖는 행렬을 만들어 저장합니다.
    """
    # stores, users를 축 score를 값으로 갖는 행렬
    users = dataframes["users"]
    reviews = dataframes["reviews"]
    df = pd.merge(left=users, right=reviews, left_on="id", right_on="user")

    # pivot_table
    user_store_matrix = df.pivot_table('score', index="user", columns="store").fillna(0)
    # sparse csr_matrix
    csr_matrix = sparse.csr_matrix(user_store_matrix)

    return csr_matrix



def get_user_category_matrix(dataframes):
    """
    Req. 4-2 유저와 음식점 카테고리를 축으로 하고 평점 평균을 값으로 갖는 행렬을 만들어 저장합니다.
    """
    stores = dataframes["stores"]
    reviews = dataframes["reviews"]

    temp = pd.merge(left=stores, right=reviews, left_on="id", right_on="store")
    stores_score = temp.groupby(["user", "category"])["score"].mean().reset_index()

    # pivot_table
    user_category = stores_score.pivot_table('score', index="user", columns="category").fillna(0)
    # sparse csr_matrix
    csr_matrix = sparse.csr_matrix(user_category)

    return user_category


def main():
    data = load_dataframes()

    # user_store_matrix = get_user_store_matrix(data)
    user_category_matrix = get_user_category_matrix(data)

    # print(user_store_matrix)
    print(user_category_matrix)


if __name__ == "__main__":
    main()
