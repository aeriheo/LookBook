# Sub I 기능 명세 구현

## REQ 1 데이터 전처리 (parse.py)
### 1-1 데이터 로딩 및 Pandas DataFrame 변환

![스크린샷_2021-09-02_오후_2.37.36](/uploads/eba26c771adc2ed3a8491bcbe3ba5033/스크린샷_2021-09-02_오후_2.37.36.png)
![스크린샷_2021-09-02_오후_2.37.47](/uploads/78e8b19a3a0861f9398a2edf9b33aedf/스크린샷_2021-09-02_오후_2.37.47.png)
![스크린샷_2021-09-02_오후_2.37.58](/uploads/659b05a40987ad36f9b8ee3a40e02441/스크린샷_2021-09-02_오후_2.37.58.png)


## REQ 2 데이터 통계 값 구하기 (analyze.py)


### 2-1 음식점 평점 순 출력하기
### 2-2 최소 리뷰 개수 필터링 (2-1에서 일정 개수 이하인 음식점 제외)

```
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
```

![스크린샷_2021-09-02_오후_2.40.29](/uploads/191dd445bacb6cbed68c12f2bba07944/스크린샷_2021-09-02_오후_2.40.29.png)

### 2-3 리뷰 개수 기준 음식점 정렬

```
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
```

![스크린샷_2021-09-02_오후_2.41.07](/uploads/9f8dccec79329dab1101f7f1f497d3da/스크린샷_2021-09-02_오후_2.41.07.png)

### 2-4 리뷰 개수 기준 유저 정렬

```
def get_most_active_users(dataframes, n=20):
    """
    Req. 1-2-4 가장 많은 리뷰를 작성한 `n`명의 유저를 정렬하여 리턴합니다.
    """
    users_group = dataframes["users"].groupby(["id"])
    users_result = users_group.count().sort_values(by='review_id', ascending=False)["review_id"]

    return users_result.head(n=n).reset_index()
```

![스크린샷_2021-09-02_오후_2.41.59](/uploads/23093928685ddbf46b40fdd4f65a3439/스크린샷_2021-09-02_오후_2.41.59.png)


## REQ 3 데이터 시각화 (visualize.py)

### 3-1 음식점 리뷰 수 분포 구하기

```
def show_store_review_distribution_graph(dataframes, n=1000):
    """
    Req. 1-3-1 전체 음식점의 리뷰 개수 분포를 그래프로 나타냅니다. 
    """

    stores = dataframes["stores"].head(n)
    chart = sns.relplot(x="store_name", y="review_cnt", data=stores, hue="review_cnt")
    plt.title("음식점 리뷰개수 분포")
    plt.show()
```

![스크린샷_2021-09-02_오후_2.44.31](/uploads/fb01f1aec9d0b694b5582af526fcbb4d/스크린샷_2021-09-02_오후_2.44.31.png)

### 3-2 평균 평점 분포 구하기

```
def show_store_average_ratings_graph(dataframes, n=1000):
    """
    Req. 1-3-2 각 음식점의 평균 평점을 그래프로 나타냅니다.
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    ).head(n)
    scores_group = stores_reviews.groupby(["store", "store_name"])
    scores = scores_group.mean()

    chart = sns.relplot(x="store_name", y="score", data=scores)
    plt.title("음식점 평균 평점")
    plt.show()
```

![스크린샷_2021-09-02_오후_2.47.25](/uploads/6b032a2d9178da2a1a69726fe4ffdcb1/스크린샷_2021-09-02_오후_2.47.25.png)


### 3-3 유저 리뷰 수 분포 구하기

```
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
    result.rename(columns={'id_x': 'store_id'}, inplace=True)
    chart = sns.relplot(x="store_id", y="review_cnt", data=result, hue="review_cnt")
    plt.title("유저 리뷰 개수")
    plt.show()
```

![스크린샷_2021-09-02_오후_2.52.03](/uploads/9501feaafda39b0e268a6ee65ade84a7/스크린샷_2021-09-02_오후_2.52.03.png)


### 3-4 유저 나이대, 성별 분포 구하기

```
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
```

![스크린샷_2021-09-02_오후_2.52.51](/uploads/adcd7e3ecb48cbbcbfd82635f92a10db/스크린샷_2021-09-02_오후_2.52.51.png)

### 3-5 음식점 위치 분포 구하기

```
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
```
![스크린샷_2021-09-02_오후_2.53.27](/uploads/d7e0d32cb65e54dd7ea91dd137cb6621/스크린샷_2021-09-02_오후_2.53.27.png)

## REQ 4 유저-아이템 행렬 생성(matrix.py)

### 4-1 유저-음식점 행렬 생성

```
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
```

![Req4-1행렬](/uploads/f264814a5260844d427dc85855b51a0a/Req4-1행렬.png)
![Req4-1Sparse](/uploads/1cb0c5bede3f457bc6c2c791cb2e4794/Req4-1Sparse.png)
![Req4-1Sparse2](/uploads/e0f6c4c2c5d6f71084ab5e62ae653a20/Req4-1Sparse2.png)

(왼쪽 : pivot_table / 오른쪽(두 개) : sparse matrix)

### 4-2 유저-카테고리 행렬 생성

```
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
```

![Req4-2행렬](/uploads/bc3baeb5bdfc5effe2b1a7c1a2aa8a89/Req4-2행렬.png)

![Req4-2Sparse](/uploads/3b816bfd851628dcdd303b3067455c4a/Req4-2Sparse.png)
![Req4-2Sparse2](/uploads/2053e31f41d8cda3726c9c35a67adb30/Req4-2Sparse2.png)

(위 : pivot_table / 아래 : sparse matrix)

# SSAFY Bigdata project Sub I

## How to Run

### Sub1

```sh
cd sub1
pip install -r requirements.txt
python parse.py
python analyze.py
python visualize.py
```

### Sub 2

**Backend**

```sh
cd sub2/backend
pip install -r requirements.txt
python manage.py makemigrations
python manage.py migrate
python manage.py initialize
python manage.py runserver
```

**Frontend**

```sh
cd sub2/frontend
npm install
npm run serve
```

### data file
  - 기본 제공 데이터: 맛집 데이터
    - 스켈레톤 폴더 내 포함
    - PW: ssafy2021!@#$ - 확인 후 본 문서에서 PW 삭제 요망
  - 추가 제공 데이터: 카드사 데이터
    - 다운로드 링크: https://lab.ssafy.com/s05-bigdata-rec/card-data/-/blob/master/card-data.zip
    - PW: ssafy2021!@#$ - 확인 후 본 문서에서 PW 삭제
  - ** SSAFY에서 제공하는 기업 데이터는 다른 목적으로 사용할 수 없으며, 데이터 원본의 외부 반출을 금합니다.**

