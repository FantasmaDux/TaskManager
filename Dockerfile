FROM gradle:8.7-jdk21-alpine AS build

LABEL authors="Rus"

WORKDIR /home/gradle/project

# Копируем только файлы конфигурации (settings.gradle, build.gradle и т.п.)
COPY --chown=gradle:gradle gradlew ./
COPY --chown=gradle:gradle gradle ./gradle

COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts ./

# Кэшируем зависимости
RUN ./gradlew dependencies --no-daemon || true

# Теперь копируем остальной исходный код
COPY --chown=gradle:gradle src ./src

# Сборка Spring Boot JAR
RUN ./gradlew bootJar --no-daemon

# Финальный контейнер
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Копируем собранный .jar из стадии build
COPY --from=build /home/gradle/project/build/libs/*.jar task-manager-backend.jar

ENTRYPOINT ["java", "-jar", "task-manager-backend.jar"]