FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/ExamThourayaS2-0.0.1.jar ExamThourayaS2-0.0.1.jar
ENTRYPOINT ["java","-jar","/ExamThourayaS2-0.0.1.jar"]
