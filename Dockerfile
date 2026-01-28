# 1. Builder Stage
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /workspace

# Copy wrapper and build configuration first for layer caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (BuildKit cache mount avoids re-downloading on every build)
RUN --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -B

# Copy source and build
COPY src src
RUN --mount=type=cache,target=/root/.m2 ./mvnw package -DskipTests -B

# 2. Runtime Stage
FROM eclipse-temurin:21-jre-jammy

LABEL org.opencontainers.image.source="https://github.com/ebpro/sample-hellojpa" \
      org.opencontainers.image.licenses="MIT"

# Security: Run as non-privileged user
RUN addgroup --system javauser && adduser --system --group javauser
WORKDIR /app

# Copy artifacts with correct ownership
COPY --from=builder --chown=javauser:javauser /workspace/target/*.jar app.jar
COPY --from=builder --chown=javauser:javauser /workspace/target/lib ./lib

USER javauser

# Native JVM variable - automatically picked up by 'java' command
# Added ExitOnOutOfMemoryError so the container restarts if the heap dies
ENV JDK_JAVA_OPTIONS="-XX:MaxRAMPercentage=75.0 \
                      -XX:+ExitOnOutOfMemoryError \
                      -XX:+UseG1GC \
                      -Djava.security.egd=file:/dev/./urandom \
                      -Dfile.encoding=UTF-8"

# Exec form: No 'sh -c'. Java is PID 1, allowing for graceful shutdowns (SIGTERM)
ENTRYPOINT ["java", "-jar", "app.jar"]
