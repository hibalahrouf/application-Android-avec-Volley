# Application Android – Gestion des Étudiants  
Web Service PHP + MySQL + Volley + Gson

## Description du Projet

Ce projet consiste à développer une application Android connectée à une base de données MySQL via un Web Service PHP.

L’application permet de :

- Ajouter un étudiant
- Afficher la liste complète des étudiants
- Modifier un étudiant
- Supprimer un étudiant
- Actualiser dynamiquement la liste après chaque opération

---

# Partie 1 – Base de Données MySQL

## Création de la base

```sql
CREATE DATABASE school1;
```

## Création de la table

```sql
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    ville VARCHAR(50),
    sexe VARCHAR(10)
);
```

## Aperçu Base de Données (phpMyAdmin)

![Base de données](screenshots/database.png)

---

# Partie 2 – Web Service PHP (Architecture REST)

## Structure du projet backend

```
lab8/
│
├── connexion/
├── dao/
├── service/
├── ws/
```

## Web Services Implémentés

| Méthode | Fichier | Description |
|----------|----------|--------------|
| POST | createStudent.php | Ajouter un étudiant |
| GET | loadStudent.php | Charger tous les étudiants |
| POST | updateStudent.php | Modifier un étudiant |
| POST | deleteStudent.php | Supprimer un étudiant |

Toutes les réponses sont retournées au format JSON.

---

## Test avec Postman

### Ajouter un étudiant

![Test Postman - Add](screenshots/postman_add.png)

### Charger les étudiants

![Test Postman - Load](screenshots/postman_load.png)

### Modifier un étudiant

![Test Postman - Update](screenshots/postman_update.png)

### Supprimer un étudiant

![Test Postman - Delete](screenshots/postman_delete.png)

---

# Partie 3 – Application Android

## Technologies utilisées

- Java
- Volley (requêtes HTTP)
- Gson (parsing JSON)
- ListView
- AlertDialog
- Spinner
- RadioButton

---

# Fonctionnalités Implémentées

## 1. Ajouter un étudiant

Formulaire contenant :

- Nom
- Prénom
- Ville (Spinner)
- Sexe (RadioButton)
- Bouton ADD

Après validation :

- Envoi des données au Web Service
- Insertion en base de données
- Réponse JSON retournée

### Interface Ajout

![Interface Ajout](screenshots/add_student.png)

---

## 2. Afficher la liste des étudiants

- Utilisation d’un ListView
- Adaptateur personnalisé EtudiantAdapter
- Chargement automatique via loadStudent.php
- Bouton Refresh

### Liste des étudiants

![Liste des étudiants](screenshots/list_students.png)

---

# Étape 4.8 – Challenge

## Objectif

Créer une activité affichant la liste complète des étudiants avec :

- Clic sur un élément
- Popup d’actions (Modifier / Supprimer)
- Confirmation avant suppression
- Actualisation dynamique après chaque opération

---

## Popup d’actions

Lorsqu’on clique sur un étudiant :

- Modifier
- Supprimer

![Popup actions](screenshots/popup.png)

---

## Suppression avec Confirmation

- Affichage d’un AlertDialog
- Confirmation obligatoire
- Envoi vers deleteStudent.php
- Mise à jour automatique de la liste

---

## Modification

- Popup personnalisé avec champs pré-remplis
- Envoi vers updateStudent.php
- Rafraîchissement automatique

---

# Actualisation Dynamique

Après chaque opération (Ajout / Modification / Suppression) :

```java
adapter.notifyDataSetChanged();
```

La liste se met à jour automatiquement sans redémarrer l’activité.

---

# Configuration Réseau

Pour l’émulateur Android :

```
http://10.0.2.2/lab8/ws/
```

Permission Internet :

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

---

# Démonstration Vidéo

Vidéo du projet :

Ajouter ici le lien Google Drive ou YouTube

---

# Compétences Démontrées

- Création d’une base de données MySQL
- Développement d’un Web Service PHP
- Mise en place d’une architecture DAO
- Communication Android ↔ PHP
- Manipulation du format JSON
- Utilisation de la bibliothèque Volley
- Parsing avec Gson
- Gestion dynamique d’une ListView
- Utilisation d’AlertDialog et interactions utilisateur
- Implémentation complète des opérations CRUD

---

