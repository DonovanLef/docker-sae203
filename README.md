<div align="center">
	<h1>
		Mp3 Application Projet
	</h1>
</div>

# Implementation serveur
Ces instructions vous permettront de lancer le serveur de l'application
## Pré-requis
1. Installer `Java 17` ou une version ultérieure sur votre machine `serveur`
2. Télécharger les `dépendences` disponibles dans le dossier `dependency` du projet
3. Ajouter dans votre `CLASSPATH` le chemin du dossier `dependency` du projet
## Nécessaire au fonctionnement
1. * Se placer dans le dossier `Web` et éditer le fichier **socket.js** avec l'IDE de votre choix
   * Remplacer `localhost` à la 3e ligne par l'adresse ip de votre machine serveur
2. * Pour que le serveur joue la musique, inserer les musiques (format .mp3) dans le dossier `java/mp3`
   * Les musiques doivent etre dans un format precis qui est `Auteur - Titre`
   * Pour que le client puisse jouer la musique sur son appareil, inserer les memes musiques (format .mp3) dans le dossier `Web/mp3`
## Lancement de l'application
* Lancer un Open Live Server depuis VS Code ou un Apache Server qui pointe vers le index.html du dossier `Web`
* Se placer dans le dossier `java` du projet
* Compiler tous les fichiers java avec la commande `javac */*.java`
* Exécuter le fichier `Main` du dossier server à l'aide de la commande `java server.Main`


# Utilisation côté client
Ces instructions vous permettront d'utiliser l'application à partir de n'importe quel appareil
## Pré-requis
1. Etre connecté sur le même réseau local que le serveur
2. Posseder un navigateur internet
## Accès à l'application
* Recupérer l'adresse ip du serveur (nous prendrons 172.0.1.1 pour notre exemple)
* Accéder au dossier `Web` du serveur sur le port choisi ce qui donne pour l'exemple `172.0.1.1:8080/Web`
* Enjoy!

