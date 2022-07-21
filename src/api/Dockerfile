FROM mcr.microsoft.com/openjdk/jdk:17-mariner AS build

# make the 'app' folder the current working directory
WORKDIR /app

COPY . .
RUN ./mvnw package

FROM mcr.microsoft.com/openjdk/jdk:17-mariner

ENV spring_profiles_active="prod"

COPY --from=build /app/target/todo-azure-api-1.0-SNAPSHOT.jar /todo-azure-api-1.0-SNAPSHOT.jar

CMD ["java", "-XX:MaxRAMPercentage=70", "-XX:+UseParallelGC", "-XX:ActiveProcessorCount=2", "--add-opens", "java.base/java.time=ALL-UNNAMED", "-jar", "/todo-azure-api-1.0-SNAPSHOT.jar"]
