FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Dmitriy I. Yakovlev zimy@yandex.ru
ENV MONGO_PASSWORD password
ENV SPRING_PROFILES_ACTIVE production
EXPOSE 80
EXPOSE 443
CMD java -jar /questionnaire-ng.jar --mongo-password=$MONGO_PASSWORD --server.port=80
ADD questionnaire-ng-0.0.1-SNAPSHOT.jar /questionnaire-ng.jar
ADD QuestionList.ods /QuestionList.ods
ADD MOL.csv /MOL.csv
