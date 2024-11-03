# Usa una imagen de Maven con JDK 17
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos pom.xml y src al contenedor
COPY pom.xml .
COPY src ./src

# Ejecuta Maven para compilar el proyecto y generar el archivo JAR
RUN mvn clean package -DskipTests

# Usa una imagen más ligera de Java para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en la nueva imagen
WORKDIR /app

# Copia el archivo JAR desde la imagen de construcción (build stage)
COPY --from=build /app/target/*.jar /app/app.jar

# Expone el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]