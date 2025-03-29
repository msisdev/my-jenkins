# Stage: Build

FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build --no-daemon

# Stage: Run

FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# docker build -t my-jenkins .
# docker run -p 8080:8080 my-jenkins

# How to remove dangling image
#
# docker ps
# docker stop <container_id>
# docker rm <container_id>
# docker rmi <image_id or tag>
