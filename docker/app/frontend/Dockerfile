# Latest Node + Angular CLI for local dev builds.
FROM node:current-slim

WORKDIR /app

RUN npm install -g @angular/cli@latest

# If you have an Angular app, copy it in; otherwise mount it at runtime.
COPY package*.json ./
RUN if [ -f package.json ]; then npm install; else echo "No package.json found; skipping npm install."; fi

COPY . .

EXPOSE 4200

CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200"]
