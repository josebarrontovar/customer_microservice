# Usar una imagen base con Java 17 y Maven (opcional para build)
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código y compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests

# Imagen final para ejecutar la app
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el jar compilado
COPY --from=build /app/target/*.jar app.jar

# Puerto que expone la app
EXPOSE 8080

# Comando para iniciar la app
ENTRYPOINT ["java","-jar","app.jar"]
