ARG BASE_IMAGE
FROM ${BASE_IMAGE}

WORKDIR /app

ARG JAR_FILE
COPY target/${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "app.jar"]
