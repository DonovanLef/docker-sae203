# Utiliser l'image httpd officielle comme image parent
FROM httpd

VOLUME ../mp3 

# Copier le répertoire html du répertoire courant vers le répertoire de l'image /usr/.../htdocs
COPY ./html/ /usr/local/apache2/htdocs/

# Rendre le port 80 accessible au monde en dehors de ce conteneur
EXPOSE 80