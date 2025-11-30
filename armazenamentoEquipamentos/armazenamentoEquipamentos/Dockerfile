# Etapa 1: Build da aplicação
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Build do projeto
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar o JAR da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expor porta
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
