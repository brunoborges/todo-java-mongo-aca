FROM mcr.microsoft.com/openjdk/jdk:17-mariner AS build

# make the 'app' folder the current working directory
WORKDIR /app

COPY . .

RUN ./mvnw package

FROM mcr.microsoft.com/openjdk/jdk:17-mariner

RUN curl -LO https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.3.0/applicationinsights-agent-3.3.0.jar

COPY --from=build /app/target/todo-azure-api-1.0-SNAPSHOT.jar /todo-azure-api-1.0-SNAPSHOT.jar

CMD ["java", "-javaagent:/applicationinsights-agent-3.3.0.jar", "-jar", "/todo-azure-api-1.0-SNAPSHOT.jar"]
