# Utilizar OpenJDK 17 como imagen base
FROM openjdk:17

# Establecer el directorio de trabajo en la imagen de Docker
WORKDIR /app

# Copiar los archivos de tu proyecto en la imagen de Docker
COPY . /app

# Asegurarse de que el script del envoltorio Maven sea ejecutable
RUN chmod +x /app/mvnw

# Ejecutar la construcción de Maven, omitiendo las pruebas
RUN ./mvnw clean install -DskipTests
