FROM maven:3.9.9-eclipse-temurin-21 AS build

ADD ./app/pom.xml /app/pom.xml
ADD ./app/Main.java /app/src/main/java/Main.java

WORKDIR /app

RUN mvn -Dexec.mainClass=Main compile exec:java

FROM maven:3.9.9-eclipse-temurin-21
COPY --from=build /root/.m2 /root/.m2

WORKDIR /app
ADD ./app/pom.xml /app/pom.xml