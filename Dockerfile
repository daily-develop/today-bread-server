FROM openjdk:17
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "-Dspring.datasource.url=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}", "-Dspring.datasource.username=${POSTGRES_USER}", "-Dspring.datasource.password=${POSTGRES_PASSWORD}", "-Dspring.redis.host=${REDIS_HOST}", "-Dspring.redis.port=${REDIS_PORT}", "-Dspring.redis.password=${REDIS_PASSWORD}", "-Djwt.secret=${JWT_SECRET}", "-Dstorage.bucket=${NCP_BUCKET}", "-Dstorage.end-point=${NCP_ENDPOINT}", "-Dstorage.region=${NCP_REGION}", "-Dstorage.access-key=${NCP_ACCESS_KEY}", "-Dstorage.secret-key=${NCP_SECRET_KEY}", "/app.jar"]
