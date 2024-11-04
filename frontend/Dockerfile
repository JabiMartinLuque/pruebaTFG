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

# Instala gettext para usar envsubst
RUN apk add --no-cache gettext

# Copia los archivos generados por Angular en la fase de construcción
COPY --from=build /app/dist/furbo/browser /usr/share/nginx/html

# Copia el archivo de configuración de Nginx
COPY nginx.conf.template /etc/nginx/templates/nginx.conf.template

# Expone el puerto 80 para servir la aplicación
EXPOSE 80

# Reemplaza las variables de entorno en el archivo de configuración de Nginx y luego inicia Nginx
CMD ["/bin/sh", "-c", "envsubst '${SERVER_NAME} ${BACKEND_HOST} ${BACKEND_PORT} ${CORS_ORIGIN}' < /etc/nginx/templates/nginx.conf.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"]