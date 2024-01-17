FROM alpine:latest
RUN apk update
RUN apk add npm
RUN apk add maven
COPY games-log-ui/package*.json /app/games-log-ui/
WORKDIR /app/games-log-ui
RUN npm install
COPY games-log-ui .
COPY pom.xml /app/
RUN npm run build
RUN mvn clean install
WORKDIR /app
COPY games-log/pom.xml games-log/pom.xml
RUN mvn dependency:resolve
COPY . .
RUN mvn clean install
WORKDIR games-log
ENTRYPOINT ["mvn","spring-boot:run"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]
