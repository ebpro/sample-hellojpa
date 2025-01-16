FROM maven:3.9-eclipse-temurin-21 AS stage-build
WORKDIR /app
COPY . ./
RUN --mount=type=cache,target=/root/.m2 mvn clean verify

FROM eclipse-temurin:21-jre-jammy
COPY --from=stage-build /app/target/*-jar-with-dependencies.jar /myapp.jar
CMD ["java","-jar","/myapp.jar"]
