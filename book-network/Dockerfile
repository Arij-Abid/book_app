# Étape 1: Utiliser une image Maven pour construire le projet
FROM maven:3.8.5-openjdk-17 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances Maven
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copier les fichiers source du projet
COPY src ./src

# Construire l'application Spring Boot
RUN mvn clean package -DskipTests

# Étape 2: Utiliser une image Java légère pour exécuter l'application
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré à partir de l'étape de construction
COPY --from=build /app/target/*.jar app.jar

# Configurer les variables d'environnement
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=8085

# Exposer le port utilisé par Spring Boot
EXPOSE 8085

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]