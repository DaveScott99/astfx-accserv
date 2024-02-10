FROM eclipse-temurin:17-jre-alpine
LABEL authors="Davi Santos"
WORKDIR /app
COPY target/account-1.1.5.jar account-1.1.5.jar
EXPOSE 8763
CMD ["java", "-jar", "account-1.1.5.jar"]