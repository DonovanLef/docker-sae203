FROM debian:latest

RUN  apt-get update && \
    apt-get -y install  \
    default-jdk

VOLUME ./java

# Copier le répertoire html du répertoire courant vers le répertoire de l'image /usr/.../htdocs
COPY ./java /var/java
COPY ./dependency /var/dependency

WORKDIR /var/java

# Rendre le port 80 accessible au monde en dehors de ce conteneur
EXPOSE 80