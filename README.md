<div align="center">
	<h1>
		Mp3 Application Projet
	</h1>
</div>

**Nom :** LEFEVRE Donovan, BERNOUY Matthias, JANOT Florian

**Groupe :** D

**Année :** 2023

**IUT Le Havre - Cours GIT**

### Compte-rendu SAE 2.03

# Introduction

Le projet consiste à développer une application de chat en réseau utilisant un protocole client-serveur en Java. 
Les utilisateurs pourront se connecter au serveur et discuter avec les autres utilisateurs connectés au même serveur. 
De plus, notre application possède une fonctionnalité permettant de contrôler un lecteur de MP3 installé sur la machine du serveur à distance. 
Ce lecteur de MP3 réside sur un principe de vote de tous les utilisateurs pour choisir la musique qu'ils souhaitent écouter. 
Ce projet a été implementé dans la ressource R2.05 de réseau.
Lors de cette SAÉ, nous y avons ajouté un système d'importation de fichier MP3. Ce système permet à chaque utilisateur d'importer les musiques de son choix. 

# Problèmes et solutions  

## Verification du fichier envoyé

Tout d'abord, nous avons mis en place un système vérifiant que le fichier importé est bien un fichier MP3.
Notre première idée était de vérifier que le fichier soit lisible par notre lecteur MP3. Ainsi, on ajouterait uniquement des fichiers qui pourrainet fonctionner avec notre lecteur.
Suite à cet ajout, nous avons rencontrés différents problèmes : en effet, le lecteur que nous utilisons arrivait à lire aussi les fichiers non-MP3. 
Nous avons donc dû mettre en place des solutions, comme le fait de verifier que le fichier avait bien une durée de lecture.

## Envoi du fichier

Dans un second temps, nous avons traité l'envoi du fichier par le client.
Notre première idée était de passer par des websockets comme c'était le cas pour notre système de chat.
Néanmoins, les fichiers MP3 étant trop volumineux pour passer par des websockets (16Ko max), il a fallu trouver une alternative pour l'envoi.
La solution trouvée a été de passer par le biais d'une requête POST dans laquelle les données nécessaires sont transferées avec un fichier Json. 
Ces données sont ensuite reconstitutées et envoyées à notre programme de vérification de fichier créé plus tôt, afin de générer la musique et de l'ajouter à la liste de lectures.

## Lecture audio avec Docker

Quand nous avons souhaité implémenter ce projet sous Docker, nous avons recontré un problème : la lecture de l'audio n'est pas prise en charge par Docker.
Suite à cela ainsi que plusieurs recherches infructueuses, nous avons décidé de reprendre totalement notre système de lecture de musique : ce ne serait désormais plus le serveur qui jouera la musique, mais le client. 
Notre système de vérification de fichier a donc été changé lui aussi : il vérifie maintenant du côté web que la durée de la musique est existante afin de pouvoir la jouer.


# Mise en service du projet

## Implementation serveur
Ces instructions vous permettront de lancer le serveur de l'application
### Pré-requis
1. Installer `Docker` sur votre machine `serveur`
2. Installer `GIT` sur votre machine `serveur`
### Nécessaire au fonctionnement
1. * Se placer dans le dossier de votre choix et éxecuter la commande `git clone git@github.com:DonovanLef/docker-sae203.git`
2. * Se placer dans le dossier `docker-sae203` avec la commande `cd docker-sae203`

### Lancement du Serveur
* Se placer dans le dossier `back` avec la commande `cd back`
* Modifier les droits du fichier `exec.bash` avec la commande `chmod 777 exec.bash`
* Construire l'image Docker du Serveur avec la commande `docker build -t back .`
* Se placer dans le dossier `mp3` avec la commande `cd ../mp3`
* Lancer le Serveur avec la commande `sudo docker run -it -p 8090:80 -v "$PWD:/var/java/mp3" back`


### Lancement du Web
* Se placer dans le dossier `front` avec la commande `cd ../front`
* Modifier les droits du fichier `exec.bash` avec la commande `chmod 777 exec.bash`
* Construire l'image Docker du Web avec la commande `docker build -t front .`
* Modifier le fichier `var.js` avec la commande `nano html/var.js` pour remplacer l'adresse ip par celle de la machine hôte 
* Se placer dans le dossier `mp3` avec la commande `cd ../mp3`
* Lancer le Web avec la commande : `sudo docker run -e IP="<VOTRE_ADRESSE_IP>" -it -p 8080:80 -v "$PWD:/var/www/mp3" front`



## Utilisation côté client
Ces instructions vous permettront d'utiliser l'application à partir de n'importe quel appareil
### Pré-requis
1. Etre connecté sur le même réseau local que le serveur
2. Posseder un navigateur internet
### Accès à l'application
* Recupérer l'adresse ip du serveur (nous prendrons 172.0.1.1 pour notre exemple)
* Accéder au dossier `Web` du serveur sur le port choisi ce qui donne pour l'exemple `172.0.1.1:8080/Web`
