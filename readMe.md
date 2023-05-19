API de Statistiques des Joueurs de Tennis

Cette application Java est une API simple qui fournit des statistiques sur les joueurs de tennis. 

L'API respecte les principes REST et utilise Spring Boot pour la mise en œuvre.

Prérequis :

Avant de commencer, assure-toi que les éléments suivants sont installés sur ta machine :

Java JDK 17

Apache Maven 3.3.6

Spring 2.5.9

Installation :

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

L'application ne contient pas une base de données mais plutot un fichier json

Les DTO servent lorsqu'on veut externaliser les services.

La couche Repository est mise en place pour des mises à jour lorsqu'on veut intégrer une base de données

Utilisation de l'API :

Pour documenter l'API on utilise swagger

L'API fournit les endpoints suivants :

- GET /api/players/all : Récupère la liste des joueurs triée par le ratio de parties gagnées.
- GET /api/players/{id} : Récupère les informations d'un joueur spécifique en utilisant son ID.
- GET /api/players/statistics : Récupère les statistiques demandées, telles que le pays avec le plus grand ratio de parties gagnées, l'IMC moyen de tous les joueurs et la médiane de la taille des joueurs.

Les statistiques sont :

- Pays qui a le plus grand ratio de parties gagnées

- IMC moyen de tous les joueurs

- La médiane de la taille des joueurs

Tests

L'application comprend des tests unitaires pour vérifier le bon fonctionnement des endpoints et des méthodes de service.

Pour les tests l'application utlise Mockito et Junit

Pour exécuter les tests, utilise la commande suivante :

mvn test

Les résultats des tests seront affichés dans la console.

Diagramme de classe de l'application 

             +-----------------+
             |   Player        |
             +-----------------+
             | - id: Long      |
             | - name: String  |
             | - country: String |
             | - gamesWon: int |
             | - height: double|
             +-----------------+
                    ^
                    |
             +-----------------+
             | PlayerRepository|
             +-----------------+
             | - findAllByOrderByGamesWonDesc(): List<Player> |
             | - findById(id: Long): Optional<Player>         |
             +-----------------+
                    ^
                    |
             +-----------------+
             |  PlayerService  |
             +-----------------+
             | - getAllPlayersSortedByGamesWon(): List<Player> |
             | - getPlayerById(id: Long): Player               |
             | - getCountryWithHighestWinRatio(): String       |
             | - getAverageIMC(): double                       |
             | - getMedianHeight(): double                     |
             +-----------------+
                    ^
                    |
             +-----------------+
             | PlayerController|
             +-----------------+
             | - getAllPlayersSortedByGamesWon(): List<Player> |
             | - getPlayerById(id: Long): Player               |
             | - getPlayerStatistics(): StatisticsDto          |
             +-----------------+