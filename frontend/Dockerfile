# Fase 1: Desarrollo (build)
FROM node:20.12.0-alpine AS development

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de package.json y package-lock.json
COPY package*.json ./

# Instala Angular CLI globalmente y todas las dependencias (incluidas las de desarrollo)
RUN npm install -g @angular/cli && npm install && \
    npm --version && ng --version && \
    ls -la /usr/local/bin && ls -la /app/node_modules/.bin

# Verifica las instalaciones
RUN npm --version
RUN ng --version

# Agrega node_modules/.bin al PATH
ENV PATH /app/node_modules/.bin:$PATH
ENV PATH /app/node_modules/.bin:/usr/local/bin:$PATH

# Copia el código fuente de tu aplicación al contenedor
COPY . .

# Verifica la instalación de Angular CLI y npm
RUN npm --version
RUN ng --version
RUN ls -la /usr/local/bin
RUN ls -la /app/node_modules/.bin

# Expone el puerto 4200 para acceder a la aplicación en desarrollo
EXPOSE 4200

# Comando por defecto: inicia el servidor de desarrollo de Angular
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200"]

# Fase 2: Producción
FROM node:20.12.0-alpine AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de package.json y package-lock.json
COPY package*.json ./

# Instala todas las dependencias (incluidas las de desarrollo)
RUN npm install

# Copia el código fuente de tu aplicación al contenedor
COPY . .

# Construye la aplicación Angular
RUN npm run build --configuration=production --aot

# Fase 3: Nginx para servir en producción
FROM nginx:alpine

# Copia los archivos generados por Angular en la fase de construcción
COPY --from=build /app/dist/furbo/browser /usr/share/nginx/html

# Expone el puerto 80 para servir la aplicación
EXPOSE 80

# Comando por defecto: Nginx se encargará de servir la aplicación
CMD ["nginx", "-g", "daemon off;"]      
