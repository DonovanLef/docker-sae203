FROM debian:latest

RUN  apt-get update && \
    apt-get -y install  \
    openjdk-17-jdk openjdk-17-jre

# Copier le répertoire html du répertoire courant vers le répertoire de l'image /usr/.../htdocs
COPY ./exec.bash /bin/exec.bash
COPY ./java /var/java
COPY ./dependency /var/dependency

WORKDIR /bin

# Rendre le port 80 accessible au monde en dehors de ce conteneur
EXPOSE 80
CMD ["./exec.bash"]