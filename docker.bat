docker buildx build --platform linux/amd64,linux/arm64 -t javimartinluque/backend:latest --push ./backend

docker buildx build --platform linux/amd64,linux/arm64 -t javimartinluque/frontend:latest --push ./frontend

# En la mv
# docker-compose pull
# docker-compose up -d