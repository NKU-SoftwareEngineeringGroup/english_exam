FROM openjdk:8

MAINTAINER zouxianyu

ENV TZ=Asia/Shanghai

ADD ./target/english_exam-0.0.1.jar /www/Server.jar

EXPOSE 8080

WORKDIR /www/

CMD ["java", "-jar", "Server.jar"]
