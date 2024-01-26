FROM maven:3.9.0-eclipse-temurin-17-focal AS stage-build
WORKDIR /app
COPY . ./
RUN --mount=type=cache,target=/root/.m2 mvn clean verify

FROM eclipse-temurin:17-jre-jammy
COPY --from=stage-build /app/target/*-jar-with-dependencies.jar /myapp.jar
CMD ["java","-jar","/myapp.jar"]
