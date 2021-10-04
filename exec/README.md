# 포팅 메뉴얼

## 1. gitlab 소스 클론 이후 빌드 및 배포할 수 있는 작업 문서

### 1) 사용한 JVM, 웹서버, IDE 버전 기재

#### JVM

![스크린샷_2021-10-04_오후_10.29.07](/uploads/63df318f36322b126d5eb0402d669626/스크린샷_2021-10-04_오후_10.29.07.png)

#### 웹서버

Nginx

![스크린샷_2021-10-04_오후_10.30.02](/uploads/6333b14122c557ce0e80da1b55849c3d/스크린샷_2021-10-04_오후_10.30.02.png)

Apache Tomcat 9.0.52

#### IDE

STS : 3.9.14

PyCharm : 2020.01

Visual Studio Code : 1.60.2

MySQL Workbench : 8.0.22

### 2) 빌드 시 사용되는 환경 변수

JAVA 

![스크린샷_2021-10-04_오후_11.08.32](/uploads/af84d0327698140d90c165918f17c04d/스크린샷_2021-10-04_오후_11.08.32.png)

Spring Build to Dockerfile
```
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Django build to Dockerfile
```
FROM python:3
ENV PYTHONUNBUFFERED 1
RUN apt-get -y update
RUN apt-get -y install vim

RUN mkdir /srv/docker-server
ADD . /srv/docker-server

WORKDIR /srv/docker-server

RUN pip install --upgrade pip
RUN pip install -r requirements.txt

EXPOSE 8000
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
```


### 3) 배포 시 특이사항 기재


Backend 배포에 docker image 사용

**Spring** - tjdytpq0310/lb-spring:latest

**Django** - tjdytpq0310/lb-django:latest

**MySQL** - mysql-container 설치로 진행

```
sudo docker pull [이미지이름]
sudo docker run -d -p [도커 컨테이너 포트번호]:[서버 포트번호] [이미지이름]
```

### 4) DB 접속 정보 등 파일 목록

**[Spring]**

application.properties

application.ouath.properties : gitignore에 등록

**[Django]**

settings.py

secret.py : gitignore에 등록


## 2. 프로젝트에서 사용하는 외부 서비스 정보 문서

### 구글 로그인 

https://developers.google.com/identity/sign-in/web/sign-in

### 카카오 로그인

https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api

