FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/book_service.jar
COPY ${JAR_FILE} book_service.jar
ENTRYPOINT ["java","-jar","/book_service.jar"]
