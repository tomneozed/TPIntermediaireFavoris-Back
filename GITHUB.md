# Gérer son Dépôt git local et distant

Il se peut que votre branche **main** s'appelle **master** en fonction des configurations

*En local (via git bash)*

0. Se déplacer vers la racine du repository :
```
cd chemin/vers/le/repository
```

1. Initialiser un repo Git:
```
git init
```


2. Créer un  nouveau projet sur Github ou Gitlab, attention, si vous ajoutez un README.md, rdv au 4.

3. Lier le repo distant au repo local :

```
git remote add origin url/de/votre/repo/github.git
```


4. Mettre à jour le repo local en fonction du repo distant (github/gitlab) :
```
git pull origin main
```

5. Créer une nouvelle branche dev (depuis main): 
```
git checkout -b dev
```


6. Créer une nouvelle branche pour implémenter une fonctionalité (depuis dev):
```
git checkout -b ma-branche-pour-l-us-numero-3
```

7. Une fois les modifications effectuées, pour voir les modifications : 
```
git status
```

8. Ajouter les fichiers à commiter : 
```
git add <fichier1-a-ajouter> <fichier2-a-ajouter>
```

ou 
```
git add .
```


9. Pour ajouter un commit :
```
git commit -m "Commentaire de votre commit"
```


10. Pour être sûr :
```
git status
```


11. Pour afficher l'historique des commits : 
```
git log
```


Maintenant, il faut faire remonter les modifications dans la branche main 
afin de pousser les changements du local vers le distant (github/gitlab)

12. Revenir sur la branche dev :
```
git switch dev
```


13. Merger les modifications de ma-branche-pour-l-us-numero-3 vers dev
```
git merge ma-branche-pour-l-us-numero-3
```


14. Merger les modifications de dev vers main
```
git switch main
git merge dev
```


15. Pousser les modifications vers le dépôt distant :
```
git push origin main
```



