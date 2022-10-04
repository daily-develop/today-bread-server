FROM openjdk:17
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [
  "java", "-jar",
  "-Dspring.profiles.active=dev",
  "spring.datasource.url=jdbc:postgresql://${POSTGERS_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}",
  "spring.datasource.username=${POSTGRES_USER}",
  "spring.datasource.password=${POSTGRES_PASSWORD}",
  "spring.redis.host=${REDIS_HOST}",
  "spring.redis.port=${REDIS_PORT}",
  "spring.redis.password=${REDIS_PASSWORD}",
  "jwt.secret=${JWT_SECRET}",
  "storage.bucket=${NCP_BUCKET}",
  "storage.end-point=${NCP_ENDPOINT}",
  "storage.region=${NPC_REGION}",
  "storage.access-key=${NCP_ACCESS_KEY}",
  "storage.secret-key=${NCP_SECRET_KEY}",
  "/app.jar"
]