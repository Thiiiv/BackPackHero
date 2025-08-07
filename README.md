# Backpack Hero - Clone Java

Ce projet est un clone du jeu "Backpack Hero", développé en Java en utilisant la bibliothèque graphique `zen5`.

## Fonctionnalités

*   **Gestion de l'Inventaire :** Le sac à dos du héros peut être géré avec des fonctionnalités de glisser-déposer pour les objets.
*   **Système de Combat :** Un système de combat au tour par tour contre des monstres.
*   **Génération de Niveaux Aléatoire :** Les niveaux sont générés de manière procédurale pour une meilleure rejouabilité.
*   **Salles Spéciales :** Explorez différentes salles comme des trésors, des marchands et des guérisseurs.

## Comment Lancer le Projet

Pour lancer le projet, suivez ces étapes depuis la racine du projet.

1.  **Placez-vous dans le bon dossier :**
    ```bash
    cd BackPackHero
    ```

2.  **Compilez le code :**
    ```bash
    javac -d bin -cp include/zen5.jar $(find src -name "*.java")
    ```

3.  **Exécutez le jeu :**
    ```bash
    java -cp bin:include/zen5.jar fr.game.data.game.GameController
    ```

## Dépendances

Ce projet utilise la bibliothèque `zen5.jar` qui est incluse dans le dossier `BackPackHero/include`.
