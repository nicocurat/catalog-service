FROM node:8

WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install --prod
COPY . .
ENTRYPOINT ["node","index.js"]
