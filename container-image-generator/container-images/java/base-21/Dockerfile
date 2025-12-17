FROM maven:3.9.9-eclipse-temurin-21 AS build

ADD ./app/pom.xml /app/pom.xml
ADD ./app/Main.java /app/src/main/java/Main.java

WORKDIR /app

RUN mvn compile exec:java

FROM maven:3.9.9-eclipse-temurin-21

LABEL "com.javaaidev.llm-code-executor.description"="Java 21 with libraries: com.google.guava:guava:33.5.0-jre, org.apache.commons:commons-lang3:3.20.0, com.fasterxml.jackson.core:jackson-databind:2.20.1, com.squareup.okhttp3:okhttp:5.3.2"

COPY --from=build /root/.m2 /root/.m2

WORKDIR /app
ADD ./app/pom.xml /app/pom.xml