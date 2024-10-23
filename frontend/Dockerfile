# Fase 1: Construcción (build)
FROM node:20.12.0-alpine AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de package.json y package-lock.json
COPY package*.json ./

# Instala Angular CLI globalmente y todas las dependencias
RUN npm install -g @angular/cli && npm install

# Copia el código fuente de tu aplicación al contenedor
COPY . .

# Construye la aplicación Angular
RUN npm run build --configuration=production --aot

# Fase 2: Nginx para servir en producción
FROM nginx:alpine

# Copia los archivos generados por Angular en la fase de construcción
COPY --from=build /app/dist/furbo/browser /usr/share/nginx/html

# Expone el puerto 80 para servir la aplicación
EXPOSE 80

# Comando por defecto: Nginx se encargará de servir la aplicación
CMD ["nginx", "-g", "daemon off;"]