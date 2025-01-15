# Projet CPOO

## Description

Ce projet en Java vise à analyser et optimiser les dispositions de clavier en fonction d'un corpus choisit.
Les fonctionnalités principales incluent l'analyse des fréquences de caractères, la génération et l'optimisation des claviers, ainsi que l'affichage des résultats.

## Structure du projet

- **controller** : Contient le point d'entrée principal du programme (`Main`).
- **main** : Fichier principal de l'application (`App`).
- **model** :
    - `analyse` : Analyse des fichiers pour extraire les fréquences des caractères.
    - `couple` : Gestion des couples de données.
    - `keyboard` : Définit les classes pour représenter un clavier et ses touches.
    - `keyboardeval` : Évalue et optimise les claviers.
- **util** : Gestion des entrées/sorties JSON.
- **view** : Affichage des échanges entre le terminal et l'utilisateur.
- **resources** :
    - `file` : Contient les fichiers de texte (les corpus) à faire analyser.
    - `json` : Contient les fichiers JSON pour les claviers initiaux et optimisés mais aussi un fichier de sauvegarde des Ngrammes.

## Prérequis

- Java 21 ou supérieur.
- Gradle (pour la gestion des dépendances et la compilation).

## Compilation

Compilez le projet avec Gradle :
```bash
./gradlew build
```

## Exécution

Pour exécuter le programme principal :
```bash
TERM=dumb ./gradlew run
```


## Fonctionnalités implémentées

- **Analyse de fichiers** : Analyse des fréquences des caractères dans un fichier donné.
- **Optimisation de clavier** : Génération de dispositions de clavier optimisées.
- **Lecture/Écriture JSON** : Sauvegarde et chargement des dispositions de clavier au format JSON.
- **Affichage** : Visualisation des résultats de l'optimisation.

## Choix techniques

1. **Structure en packages** : Le projet est organisé en plusieurs packages pour séparer les responsabilités (MVC : Modèle, Vue, Contrôleur).

2. **Utilisation de JSON** : Format choisi pour sa simplicité et son interopérabilité avec d'autres systèmes.

3. **Gradle** : Automatisation de la compilation et des tests pour une meilleure gestion du cycle de vie du projet.

## Fichiers JSON de départ

- `init_keyboard.json` : Contient la disposition initiale du clavier.

## Instructions supplémentaires

- Pour modifier les paramètres d'entrée, placez les fichiers nécessaires dans le dossier `resources/file`.
- Vérifiez que les fichiers JSON de départ sont bien formatés avant d'exécuter le programme.
