FROM openjdk:17-oracle
RUN mkdir /app
COPY build/libs/cybercube-be-task-e2e-tests.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
#RUN go build -o main .
#
#CMD [ "./main" ]
CMD ["-jar", "/app/cybercube-be-task-e2e-tests.jar"]
#RUN cybercube-be-task-e2e-tests sh -c "go test"
#RUN -it --rm --name springboot-test java-docker ./mvnw test
