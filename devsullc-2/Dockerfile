# Utilizar una imagen base con Java
#FROM openjdk:17-jdk-slim as build
FROM maven:3.8.5-openjdk-17 AS build

# Directorio en el contenedor donde se alojará la aplicación
WORKDIR /app

# Copiar el archivo jar de tu aplicación al contenedor
#COPY target/otece-0.0.1-SNAPSHOT.jar oteceDocker.jar
COPY . /app/

RUN mvn clean package -DskipTests

# Comando para ejecutar la aplicación
#ENTRYPOINT ["java","-jar","/app/oteceDocker.jar"]
ENTRYPOINT ["mvn","spring-boot:run"]