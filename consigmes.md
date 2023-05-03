git clone git@github.com:DonovanLef/docker-sae203.git
cd docker-sae203

# Modification du fichier front/var.js

nano front/var.js
# Modifier la constante serverAdress pour l'adresse ip de votre machine 

cd back
CHMOD 777 exec.bash
docker build -t back .

cd front
CHMOD 777 exec.bash
docker build -t front .

cd ../mp3
docker run -it -p 8090:80 -v "$PWD:/var/java/mp3" back


Si MacOS
docker run -e IP=$(ifconfig | grep -Eo 'inet ([0-9].){3}[0-9]' | awk '{print $2}'| tail -1) -it -p 8080:80 -v "$PWD:/var/www/mp3" front

Si Linux
docker run -e IP=$(ifconfig | grep "inet " | grep -v 127.0.0.1 | awk '{print $2}') -it -p 8080:80 -v "$PWD:/var/www/mp3" front

