# Build Stage
FROM node:22-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

# Per-environment endpoints, supplied by docker-compose build args (from .env.dev / .env.ec2).
# Vite bakes VITE_* from the environment into the static bundle at build time.
ARG VITE_CUSTOMER_SERVICE_URL
ARG VITE_LOAN_SERVICE_URL
ARG VITE_AGGREGATOR_SERVICE_URL
ARG VITE_KEYCLOAK_URL
ARG VITE_KEYCLOAK_REALM=los-realm
ARG VITE_KEYCLOAK_CLIENT_ID=los-frontend
ENV VITE_CUSTOMER_SERVICE_URL=$VITE_CUSTOMER_SERVICE_URL \
    VITE_LOAN_SERVICE_URL=$VITE_LOAN_SERVICE_URL \
    VITE_AGGREGATOR_SERVICE_URL=$VITE_AGGREGATOR_SERVICE_URL \
    VITE_KEYCLOAK_URL=$VITE_KEYCLOAK_URL \
    VITE_KEYCLOAK_REALM=$VITE_KEYCLOAK_REALM \
    VITE_KEYCLOAK_CLIENT_ID=$VITE_KEYCLOAK_CLIENT_ID

RUN npm run build

# Runtime Stage
FROM nginx:alpine

COPY --from=build /app/dist /usr/share/nginx/html

# SPA routing fallback so deep links (e.g. /register) resolve to index.html.
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]