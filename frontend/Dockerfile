# Fase de construcción
FROM node:18 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el package.json y package-lock.json
COPY package*.json ./

# Instala Angular CLI globalmente
RUN npm install -g @angular/cli

# Instala las dependencias
RUN npm install

# Copia el resto de la aplicación
COPY . .

# Compila la aplicación Angular
RUN npm run build --configuration furbo --aot  

# Usa un servidor web para servir la aplicación
FROM nginx:alpine

# Copia los archivos de construcción a la carpeta de Nginx
COPY --from=build /app/dist/furbo/browser /usr/share/nginx/html 

# Expone el puerto en el que Nginx escuchará
EXPOSE 80

# Comando para ejecutar Nginx
CMD ["nginx", "-g", "daemon off;"]

