# LookBook
#### 빅데이터기반 도서 추천 웹 서비스

<img src="https://i.imgur.com/lh5vDHk.jpg" width="800">

## 목차
1. [LookBook](#LookBook-Description)
2. [Tech Stack](#Tech-Stack)
3. [Feature Description](#Feature-Description)
4. [Server Strucutre](#Server-Strucutre)
5. [Recommendation System Structure](#Recommendation-System-Structure)
6. [Database Structure](#Database-Structure)
7. [Getting Started](#Getting-Started)
8. [Contributor](#Contributor)

<br>
<br>

# LookBook Description
## LookBook
**책을 보다, 룩북.**
잡지 속의 룩북처럼 최신 도서 트렌드와 사용자 취향저격 도서를 추천해주는 서비스입니다.

## Editor
**룩북의 편집자, 에디터.**
잡지의 한꼭지 한꼭지를 써내려가는 에디터처럼 룩북을 완성해가는 개발자입니다.

<br>
<br>

# Tech Stack
## Fronted
<img style="display: inline;" alt="HTML5" src ="https://img.shields.io/badge/html5-%23E34F26.svg?&style=for-the-badge&logo=HTML5&logoColor=white"/><img style="display: inline;" alt="CSS3" src ="https://img.shields.io/badge/CSS3-%231572B6.svg?&style=for-the-badge&logo=css3&logoColor=white"/><img style="display: inline;" alt="javascript" src ="https://img.shields.io/badge/JavaScript-%23F7DF1E.svg?&style=for-the-badge&logo=javascript&logoColor=white"/><img style="display: inline;" alt="react" src ="https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=React&logoColor=%2361DAFB"/><img style="display: inline;" alt="meterialui" src ="https://img.shields.io/badge/materialui-%230081CB.svg?style=for-the-badge&logo=material-ui&logoColor=white"/><img style="display: inline;" alt="npm" src ="https://img.shields.io/badge/npm-%23CB3837.svg?&style=for-the-badge&logo=npm&logoColor=white"/>
- HTML5, CSS3, JavaScript
- [React](https://ko.reactjs.org/)
- [Material UI](https://mui.com/)
- npm
    - [aws-sdk](https://www.npmjs.com/package/aws-sdk)
    - [react-google-login](https://www.npmjs.com/package/react-google-login)
    - [react-js-pagination](https://www.npmjs.com/package/react-js-pagination)
    - [react-responsive](https://www.npmjs.com/package/react-responsive)
    - [react-slick](https://www.npmjs.com/package/react-slick)
    - [slick-carousel](https://www.npmjs.com/package/slick-carousel)
    - [rc-tabs](https://www.npmjs.com/package/rc-tabs)
- [Geoloaction](https://apis.map.kakao.com/)

## Backend
<img style="display: inline;" alt="java" src ="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white"/><img style="display: inline;" alt="springboot" src ="https://img.shields.io/badge/spring boot-%236DB33F.svg?&style=for-the-badge&logo=springboot&logoColor=white"/><img style="display: inline;" alt="gradle" src ="https://img.shields.io/badge/gradle-%2302303A.svg?&style=for-the-badge&logo=gradle&logoColor=white"/><img style="display: inline;" alt="jwt" src ="https://img.shields.io/badge/jwt-%23000000.svg?&style=for-the-badge&logo=JSON Web Tokens&logoColor=white"/><img style="display: inline;" alt="python" src ="https://img.shields.io/badge/python-%2314354C.svg?style=for-the-badge&logo=python&logoColor=white"/><img style="display: inline;" alt="django" src ="https://img.shields.io/badge/django-%23092E20.svg?style=for-the-badge&logo=django&logoColor=white"/><img style="display: inline;" alt="mysql" src ="https://img.shields.io/badge/mysql-%234479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white"/>
- [Java](https://www.java.com/ko/)
- [SpringBoot](https://spring.io/)
- [Gradle](https://gradle.org/)
- [JWT](https://jwt.io/)
- [Python](https://www.python.org/)
- [Django](https://www.djangoproject.com/)
- [MySQL](https://www.mysql.com/)

## Deploy
<img style="display: inline;" alt="aws" src ="https://img.shields.io/badge/aws-%23232F3E.svg?&style=for-the-badge&logo=amazon aws&logoColor=white"/><img style="display: inline;" alt="nginx" src ="https://img.shields.io/badge/nginx-%23009639.svg?&style=for-the-badge&logo=nginx&logoColor=white"/><img style="display: inline;" alt="docker" src ="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"/><img style="display: inline;" alt="s3" src ="https://img.shields.io/badge/s3-%23569A31.svg?&style=for-the-badge&logo=amazon s3&logoColor=white"/>
- [AWS](https://aws.amazon.com/ko/?nc2=h_lg)
- [NginX](https://www.nginx.com/)
- [Docker](https://www.docker.com/)
- [AWS S3](https://aws.amazon.com/ko/s3/)

<br>
<br>

# Feature Description
## Sign Up/In
Social Login (Google & Kakao)

|Google Login            |  Kakao Login | Normal Login | Sign In|
|:-------------------------:|:-------------------------: | :----------: | :------:|
|![](https://i.imgur.com/jmOYyNg.png)  |  ![](https://i.imgur.com/nqfqSSO.png) | ![](https://i.imgur.com/eJFXJxr.png) | ![](https://i.imgur.com/mjDdvOW.png)|

## Best 10
좋아요 수를 기준으로 사용자들이 선호하는 Best 10 도서  

## Best Review
가장 많은 좋아요를 받은 리뷰  
<img src="https://i.imgur.com/8pcJLID.png" width="600">

## Item Based Recommendation
가장 인기있는 도서와 유사한 도서 추천  
<img src="https://i.imgur.com/VROJj2X.jpg" width="600">

## User Predicted Grade Recommendation
사용자 예상 평점 상위 도서 추천  
<img src="https://i.imgur.com/Ec66u0h.png" width="600">

## User Based Recommendation
유사한 사용자가 읽은 도서 추천  
<img src="https://i.imgur.com/U2pMDgT.jpg" width="600">

## Library Location
현재 위치를 기반으로 해당 도서를 대여할 수 있는 도서관 위치 제공  
<img src="https://i.imgur.com/nU9TETt.png" width="600">

## Like, Grade, Review
도서에 좋아요, 평점, 리뷰 작성  
|MY PAGE         |  GRADE | REVIEW | LIKE|
|:-------------------------:|:-------------------------: | :----------: |:----:|
|![](https://i.imgur.com/B1W5nAa.png)  |  ![](https://i.imgur.com/jCTFAM1.png) | ![](https://i.imgur.com/AaOSVIF.png) | ![](https://i.imgur.com/laFGql1.png)|

## Search
전체, 도서명, 저자명으로 도서 검색  
<img src="https://i.imgur.com/WnQKZIQ.png" width="600">

## Category
총 18개의 카테고리로 도서 검색  
<img src="https://i.imgur.com/n6GIcax.png" width="600">


<br>
<br>


# Server Strucutre
![](https://i.imgur.com/wYauvfW.png)


<br>
<br>

# Recommendation System Structure
## Case 1) Crontab – 스케줄러를 활용한 주기적 실행
<img src="https://i.imgur.com/gMp89Xc.png" width="280">

## Case 2) MSA(Spring-Django) – 사용자 회원가입
<img src="https://i.imgur.com/Rqh7vZh.png" width="300">


<br>
<br>


# Database Structure
![](https://i.imgur.com/bzwxtR5.png)


<br>
<br>


# Getting Started
## Frontend
```
./frontend
```
```
npm install
```
```
npm start
```
## Backend
### Spring
```
./backSpring
```
```
./gradlew clean build
```
```
cd build/libs
```
```
java -jar [생성된 스냅샷파일 이름].jar
```
### Django
```
pip install -r requirements.txt
```
```
python manage.py makemigrations
python manage.py migrate
```
```
python manage.py runserver
```

<br>
<br>


# Contributor

|<img src="https://i.imgur.com/TxP2QeJ.png" width="150">|<img src="https://i.imgur.com/nLP3gDK.png" width="150">|<img src="https://i.imgur.com/zeX4Gyr.png" width="150">|<img src="https://i.imgur.com/2CWF5Pc.png" width="150">|
| :--------: | :--------: | :--------: | :--------: |
| **서민영** <a href="https://github.com/smy999"><img src="https://i.imgur.com/SBDd7pE.png" width="20"></a> <br> Minyeong Seo | **서요셉** <a href="https://github.com/yoseph0310"><img src="https://i.imgur.com/SBDd7pE.png" width="20"></a> <br> Yosep Seo | **이가빈** <a href="https://github.com/Ariel-G-Lee"><img src="https://i.imgur.com/SBDd7pE.png" width="20"></a> <br> Gavin Lee | **허애리** <a href="https://github.com/aeriheo"><img src="https://i.imgur.com/SBDd7pE.png" width="20"></a> <br> Aeri Heo|
|Backend<br>Design|Backend<br>Server|Backend<br>Video Director|Frontend<br> Scenario|
