import itertools
from collections import Counter

from IPython.core.display import display
from parse import load_dataframes
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import matplotlib.font_manager as fm
from datetime import datetime
import folium


def set_config():
    # 폰트, 그래프 색상 설정
    font_list = fm.findSystemFonts(fontpaths=None, fontext="ttf")
    # if any(["notosanscjk" in font.lower() for font in font_list]):
    if any(["sfprorounded" in font.lower() for font in font_list]):
        plt.rcParams["font.family"] = "SF Pro Rounded"
        # plt.rcParams["font.family"] = "Noto Sans CJK JP"

    else:
        if not any(["applegothic" in font.lower() for font in font_list]):
            raise Exception(
                "Font missing, please install Noto Sans CJK or Malgun Gothic. If you're using ubuntu, try `sudo apt install fonts-noto-cjk`"
            )

        plt.rcParams["font.family"] = "AppleGothic"
        # plt.rcParams["font.family"] = "Malgun Gothic"

    sns.set_palette(sns.color_palette("Spectral"))
    plt.rc("xtick", labelsize=6)


def show_store_categories_graph(dataframes, n=100):
    """
    Tutorial: 전체 음식점의 상위 `n`개 카테고리 분포를 그래프로 나타냅니다.
    """

    stores = dataframes["stores"]

    # 모든 카테고리를 1차원 리스트에 저장합니다
    categories = stores.category.apply(lambda c: c.split("|"))
    categories = itertools.chain.from_iterable(categories)

    # 카테고리가 없는 경우 / 상위 카테고리를 추출합니다
    categories = filter(lambda c: c != "", categories)
    categories_count = Counter(list(categories))
    best_categories = categories_count.most_common(n=n)
    df = pd.DataFrame(best_categories, columns=["category", "count"]).sort_values(
        by=["count"], ascending=False
    )

    # 그래프로 나타냅니다
    chart = sns.barplot(x="category", y="count", data=df)
    chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("음식점 카테고리 분포")
    plt.show()


def show_store_review_distribution_graph(dataframes, n=1000):
    """
    Req. 1-3-1 전체 음식점의 리뷰 개수 분포를 그래프로 나타냅니다. 
    """

    stores = dataframes["stores"].head(n)

    # store_name = stores.store_name.apply(lambda c: c.split("|"))
    # store_name = itertools.chain.from_iterable(store_name)
    #
    # store_name = filter(lambda c: c != "", store_name)
    # store_name_count = Counter(list(store_name))
    # hun_store_name = store_name_count.most_common(n=n)
    #
    # df = pd.DataFrame(hun_store_name, columns=["store_name", "review_cnt"])

    chart = sns.relplot(x="store_name", y="review_cnt", data=stores, hue="review_cnt")
    # chart.set_xticklabels(chart.get_xticklabels(), rotation=45)
    plt.title("음식점 리뷰개수 분포")
    plt.show()


def show_store_average_ratings_graph(dataframes):
    """
    Req. 1-3-2 각 음식점의 평균 평점을 그래프로 나타냅니다.
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )
    scores_group = stores_reviews.groupby(["store", "store_name"])
    scores = scores_group.mean()

    chart = sns.relplot(x="store_name", y="score", data=scores)
    plt.title("음식점 평균 평점")
    plt.show()


def show_user_review_distribution_graph(dataframes):
    """
    Req. 1-3-3 전체 유저의 리뷰 개수 분포를 그래프로 나타냅니다.
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )
    scores_group = stores_reviews.groupby(["store", "store_name"])
    scores = scores_group.mean()
    test = scores_group.count()["content"]
    result = pd.merge(left=test, right=scores, how="inner", on=["store", "store_name"])

    chart = sns.relplot(x="id_x", y="review_cnt", data=result, hue="review_cnt")
    plt.title("유저 리뷰 개수")
    plt.show()



def show_user_age_gender_distribution_graph(dataframes):
    """
    Req. 1-3-4 전체 유저의 성별/나이대 분포를 그래프로 나타냅니다.
    """
    user = dataframes["users"]
    user_result = user[(user["born_year"].astype(int) > 0) & (user["born_year"].astype(int) <= datetime.today().year)].copy()
    user_result["age"] = user_result.apply(lambda x: datetime.today().year - int(x["born_year"]) + 1, axis=1)

    chart = sns.relplot(x="gender", y="age", data=user_result)
    plt.title("성별/나이대 분포")
    plt.show()


def show_stores_distribution_graph(dataframes):
    """
    Req. 1-3-5 각 음식점의 위치 분포를 지도에 나타냅니다.
    """
    stores = dataframes["stores"].head(1000)
    lat = stores["latitude"].astype(float).mean()
    long = stores["longitude"].astype(float).mean()
    map = folium.Map([lat, long], zoom_start=7)
    for i in stores.index:
        lat, long = stores.loc[i, "latitude"], stores.loc[i, "longitude"]
        title = stores.loc[i, "store_name"]
        folium.Marker([lat, long], tooltip=title).add_to(map)
    map.save('example.html')
    display(map)



def main():
    set_config()
    data = load_dataframes()
    # show_store_categories_graph(data)
    # show_store_review_distribution_graph(data)
    # show_store_average_ratings_graph(data)
    show_user_review_distribution_graph(data)
    # show_user_age_gender_distribution_graph(data)
    # show_stores_distribution_graph(data)


if __name__ == "__main__":
    main()
