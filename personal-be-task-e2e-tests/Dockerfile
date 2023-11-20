FROM openjdk:17-oracle
RUN microdnf install findutils
WORKDIR /app
COPY . /app
RUN chmod +x gradlew
COPY --from=cybercube-be-task-service /build/libs/cybercube-be-task-service.jar /app/libs/
CMD ["./gradlew", "test"]