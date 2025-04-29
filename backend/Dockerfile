
FROM maven:3.8.4-openjdk-17-slim AS build
#RUN apk add --no-cache tzdata

COPY . /maven
WORKDIR /maven
# Build your application using Maven
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

# Set the timezone to Asia/Kolkata
ENV TZ=Asia/Kolkata

# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Install tzdata for timezone data
#RUN apk add --no-cache tzdata

WORKDIR /app

COPY --from=build /maven/target/search-0.0.1-SNAPSHOT.war /app/backend.war

EXPOSE 8080

CMD ["java", "-jar", "backend.war"]
