# POEI SAFE - TP Intermédiaire - Backend

# Vue d'ensemble

Vous allez créer une stack complète (frontend - backend) ainsi que l’outillage (script shell) permettant de gérer un annuaire de favoris de navigation.

# Stack technique

L’architecture REST est retenue pour homogénéiser la communication frontend - backend.

Côté frontend : HTML / CSS / JS (pure JS)

Côté backend : SpringBoot + Maven + MySQL

# US Techniques
Mettre en place le script de création du scaffold frontend 

Le DevOps doit aider les développeurs à architecturer “proprement” leurs projets frontend et backend.
Pour cela, il doit fournir des scripts permettant d’organiser les dossiers, sous-dossiers,
ainsi que la configuration de GIT pour les projets front et back.

## UST 1

Mettre en place le script de création du scaffold frontend

## UST 2

Mettre en place le script de création du scaffold backend

## UST 3

Créer le script de création de la base de données

# User Stories

## US 1
En tant qu’utilisateur, je dois pouvoir visualiser la liste des Favoris, 
afin de pouvoir accéder rapidement au site web correspondant

## US 2
En tant qu’utilisateur, je dois pouvoir trier le résultat affiché par “Catégorie” ou “Date de mise à jour” 
afin d’avoir une vision plus pertinente de mes favoris

## US 3
En tant qu’utilisateur, je dois pouvoir filtrer les favoris par catégorie afin de ne visualiser que les favoris correspondant

## US 4
En tant qu’utilisateur je dois pouvoir sélectionner / tout désélectionner les favoris 
afin d’activer / désactiver le bouton de suppression

## US 5
En tant qu’utilisateur, en décochant une boîte de favori, je dois voir la boîte globale décochée afin de respecter
l’homogénéité du comportement des boîtes

## US 6
En tant qu’utilisateur, la sélection de tous les favoris individuellement doit cocher la case “globale”, afin de garder
l’homogénéité de comportement des boîtes

## US 7
En tant qu’utilisateur, je dois cliquer sur le bouton “Add item” 
pour faire apparaître le formulaire de création d’un nouveau favori

## US 8
En tant qu’utilisateur, je souhaite voir une boîte de dialogue affichant le formulaire de création 
afin de ne pas perdre de vue le tableau des favoris.

## US 9
En tant qu’utilisateur, je dois remplir l’ensemble des champs obligatoires, afin de rendre le bouton “Add” actif

## US 10 
En tant qu’utilisateur, je dois pouvoir retourner à la liste des favoris après avoir ajouté un favori, 
afin de visualiser le résultat

## US 11
En tant qu’utilisateur, je dois rester sur le formulaire d’ajout si un problème est survenu, 
afin de pouvoir corriger ma saisie

## US 12
En tant qu’utilisateur, je dois pouvoir visualiser un toast, afin de connaître le résultat de l’ajout d’un favori

# Objectifs

1. Analysez et conceptualisez avant de vous lancer dans le code, vous gagnerez du temps
2. Fixez vous des objectifs réalistes, commitez souvent, et testez à chaque étape,
3. Identifiez vos manques, mais surtout, faites vous plaisir,
4. Ne faites pas plus qu’il vous est demandé, mais pas moins non plus


## CRUD

### Category

**Create : ✅**

http://localhost:8080/api/category

Body : 

```
{
    "id": "null",
    "label": "MUSIC"
}
```

**Read : ✅**

http://localhost:8080/api/category/{id}

**ReadAll : ✅**

http://localhost:8080/api/category


**Update : ✅**

http://localhost:8080/api/category/{id}

```
{
    "id": "{id}",
    "label": "MUSIC"
}
```

**Delete : ✅**

http://localhost:8080/api/category/{id}

---

### Favorite

**Create : ✅**

http://localhost:8080/api/category/{catgeory_id}/favorite

```
{
    "id": "null",
    "label": "Spring",
    "link": "https://www.globalknowledge.com/fr-FR/Formation/SAFe/Agile_and_Scrum/SAFE",
    "label": "INETUM ",
    "last_updated": "2023-08-25"
}
```

**Read : ✅**

http://localhost:8080/api/favorite/{id}

**ReadAll : ✅**

http://localhost:8080/api/favorite

**Update : ✅**

http://localhost:8080/api/category/{catgeory_id}/favorite

```
{
    "id": "{favorite_id}",
    "label": "Spring",
    "link": "https://www.globalknowledge.com/fr-FR/Formation/SAFe/Agile_and_Scrum/SAFE",
    "label": "INETUM ",
    "last_updated": "2023-08-25"
}
```

**DeleteMultiple : ✅**

http://localhost:8080/api/favorite/{favorite_ids}

