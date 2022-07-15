FROM mcr.microsoft.com/openjdk/jdk:17-mariner AS build

# make the 'app' folder the current working directory
WORKDIR /app

COPY . .

RUN ./mvnw package

FROM mcr.microsoft.com/openjdk/jdk:17-mariner

COPY --from=build /app/target/todo-azure-api-1.0-SNAPSHOT.jar /todo-azure-api-1.0-SNAPSHOT.jar

CMD ["java", "-jar", "/todo-azure-api-1.0-SNAPSHOT.jar"]
