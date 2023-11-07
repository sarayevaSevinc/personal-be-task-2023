FROM gradle:8.4.0-jdk17 AS test-stage
WORKDIR /app
COPY . ./
RUN gradle clean
RUN gradle test

FROM scratch AS export-stage
COPY --from=test-stage /app/build/reports/tests/test/* /