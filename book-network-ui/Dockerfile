# Étape 1: Utiliser l'image de Node.js pour la construction
FROM node:16 AS build

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copier package.json et package-lock.json (si présents) pour installer les dépendances
COPY package*.json ./
RUN npm install -g @angular/cli@16.1.0
RUN npm install

# Copier tous les fichiers source dans le conteneur
COPY . .

# Construire l'application Angular en mode production
RUN ng build --prod

# Étape 2: Utiliser l'image Nginx pour servir l'application
FROM nginx:alpine

# Copier les fichiers buildés dans le répertoire d'Nginx
COPY --from=build /app/dist/book-network-ui /usr/share/nginx/html

# Exposer le port 80 pour l'application
EXPOSE 80

# Démarrer Nginx
CMD ["nginx", "-g", "daemon off;"]
