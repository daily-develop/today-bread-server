FROM openjdk:17
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "-Dspring.datasource.url=${DATASOURCE_URL}", "-Dspring.datasource.username=${POSTGRES_USER}", "-Dspring.datasource.password=${POSTGRES_PASSWORD}", "-Dspring.redis.host=${REDIS_HOST}", "-Dspring.redis.port=${REDIS_PORT}", "-Djwt.secret=${JWT_SECRET}", "-Dstorage.bucket=${NCP_BUCKET}", "-Dstorage.end-point=${NCP_ENDPOINT}", "-Dstorage.region=${NCP_REGION}", "-Dstorage.access-key=${NCP_ACCESS_KEY}", "-Dstorage.secret-key=${NCP_SECRET_KEY}", "-Dsteppay.end-point=${STEPPAY_ENDPOINT}", "-Dsteppay.secret=${STEPPAY_SECRET_KEY}", "/app.jar"]
