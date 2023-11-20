FROM openjdk:17-oracle
RUN mkdir /app
COPY build/libs/cybercube-be-task-service.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/cybercube-be-task-service.jar"]
