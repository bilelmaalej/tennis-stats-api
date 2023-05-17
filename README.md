# tennis-stats-api
Projet de statistique des joueurs de Tennis

API de Statistiques des Joueurs de Tennis
Cette application Java est une API simple qui fournit des statistiques sur les joueurs de tennis.
L'API respecte les principes REST et utilise Spring Boot pour la mise en œuvre.

Prérequis
Avant de commencer, assure-toi que les éléments suivants sont installés sur ta machine :

Java JDK 8 ou supérieur
Apache Maven
Installation
Clone le dépôt GitHub de l'application :

git clone https://github.com/ton-utilisateur/tennis-stats-api.git
Accède au répertoire de l'application :


cd tennis-stats-api
Compile le projet à l'aide de Maven :

mvn clean install
Lancement de l'application
Exécute la commande suivante pour démarrer l'application :


mvn spring-boot:run
L'application sera accessible à l'adresse suivante : http://localhost:8080

Utilisation de l'API
L'API fournit les endpoints suivants :

GET /api/players : Récupère la liste des joueurs triée par le ratio de parties gagnées.
GET /api/players/{id} : Récupère les informations d'un joueur spécifique en utilisant son ID.
GET /api/players/statistics : Récupère les statistiques demandées, telles que le pays avec le plus grand ratio de parties gagnées, l'IMC moyen de tous les joueurs et la médiane de la taille des joueurs.
Tests
L'application comprend des tests unitaires pour vérifier le bon fonctionnement des endpoints et des méthodes de service.

Pour exécuter les tests, utilise la commande suivante :

mvn test
Les résultats des tests seront affichés dans la console.

Contribuer
Si tu souhaites contribuer à ce projet, tu peux ouvrir une pull request avec tes modifications.

Problèmes et suggestions
Si tu rencontres des problèmes ou si tu as des suggestions, n'hésite pas à ouvrir une issue sur le dépôt GitHub du projet.

