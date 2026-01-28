# Creating Images in stages

# Stage 1

# Import docker image with maven installed
FROM maven:3.9.12-eclipse-temurin-21 as builder
# Adding the maintainer
MAINTAINER Sanjana Mahapatra<sanjanamahapatra46206@gmail.com>
# Setting the workdir
WORKDIR /src
#Copy the source code from local to container
COPY . /src
#Build application and skip the test cases
RUN mvn clean install -DskipTests=true

# Stage 2

# Using small size java image
FROM eclipse-temurin:21-alpine
# Copy the build from stage 1
COPY --from=builder /src/target/*.jar /src/target/moviemsapp.jar
# Expose the application port
EXPOSE 8882
# Start the application
ENTRYPOINT ["java", "-jar", "src/target/moviemsapp.jar"]



#FROM mysql:latest
#COPY mysql.cnf /etc/mysql/conf.d/mysql.cnf
#RUN chmod 600 /etc/mysql/conf.d/mysql.cnf