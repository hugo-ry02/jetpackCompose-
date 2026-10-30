# Profil Développeur Compose

## Description
Cette application Android réalisée avec Jetpack Compose affiche une fiche de « Profil développeur ». 
L'écran présente les informations essentielles d'un profil technique : nom, poste, courte présentation, liste de compétences, statut de disponibilité et bouton de contact. 
L'interface est découpée en plusieurs composants réutilisables qui reçoivent leurs données et leurs actions en paramètres.

## Notions utilisées
- **Fonctions composables (`@Composable`)** : découpage de l'interface en sous-composants clairs.
- **Paramètres** : passage des données (textes, liste, booléen) pour éviter les valeurs en dur.
- **Modifiers & chaînage** : gestion de la largeur (`fillMaxWidth`), des espacements (`padding`), du clic (`clickable`) et de l'accessibilité.
- **Padding externe et interne** : distinction entre le placement d'un composant par son parent et l'espacement de son contenu interne.
- **Callbacks** : gestion des clics via des fonctions passées en paramètres (`() -> Unit`).
- **Accessibilité de base (`semantics`)** : utilisation de `Role.Button` et `contentDescription` sur une zone cliquable personnalisée.
- **Structure Compose** : `MainActivity`, `setContent`, `Scaffold` (`innerPadding`), `Surface`, `Column`, `Button`, `Text`.
- **Previews (`@Preview`)** : deux previews illustrant deux profils différents (disponible / en mission).

## Questions / Réponses

### 1. Quels composables avez-vous créés ?
J'ai découpé l'interface en 6 composables :
- `DeveloperProfileScreen` : l'écran principal qui contient le `Scaffold`.
- `ProfileCard` : la carte (`Surface`) qui regroupe toutes les informations du profil.
- `ProfileHeader` : l'en-tête avec le nom, le rôle et la description.
- `SkillItem` : un composant représentant une compétence de la liste.
- `AvailabilityBadge` : une zone cliquable personnalisée indiquant la disponibilité.
- `ProfileAction` : le composant contenant le `Button` d'action principal.

### 2. Quel composable représente l’écran principal ?
C'est `DeveloperProfileScreen`. Il met en place le `Scaffold` sur tout l'écran et passe le `innerPadding` à la fiche `ProfileCard`.

### 3. Où utilisez-vous un padding externe ?
Le padding externe est passé par le composant parent via le paramètre `modifier` pour positionner un enfant par rapport aux autres éléments :
- Dans `DeveloperProfileScreen`, j'applique `.padding(innerPadding).padding(16.dp)` sur `ProfileCard` pour l'éloigner des bords de l'écran.
- Dans `ProfileCard`, j'applique des paddings externes verticaux sur `SkillItem` (`padding(vertical = 3.dp)`), `AvailabilityBadge` (`padding(top = 12.dp, bottom = 8.dp)`) et `ProfileAction` (`padding(top = 8.dp)`) pour espacer les blocs entre eux.

### 4. Où utilisez-vous un padding interne ?
Le padding interne est défini directement à l'intérieur d'un composant pour éviter que son contenu ne colle à ses propres bordures :
- Dans `ProfileCard`, la `Column` interne a un `.padding(16.dp)` pour laisser une marge à l'intérieur de la `Surface`.
- Dans `SkillItem` et `AvailabilityBadge`, le `Text` interne utilise un `.padding(horizontal = 12.dp, ...)` pour aérer le texte dans son badge.

### 5. Quel composant reçoit un callback ?
- `ProfileAction` reçoit `onActionClick: () -> Unit` pour le clic sur le bouton principal.
- `AvailabilityBadge` reçoit `onClick: () -> Unit` pour le clic sur la zone de disponibilité.
Ces callbacks remontent jusqu'à `DeveloperProfileScreen` : les composants signalent simplement le clic sans exécuter de logique métier complexe.

### 6. Quelle information d’accessibilité avez-vous ajoutée ?
Comme `AvailabilityBadge` est construit avec une `Surface` rendue cliquable (et non un `Button` natif), j'ai ajouté un bloc `.semantics` dans la chaîne de modifiers avec :
- `role = Role.Button` pour indiquer aux lecteurs d'écran qu'il s'agit d'un élément interactif de type bouton.
- `contentDescription` pour préciser vocalement le statut et le fait qu'on peut cliquer dessus.
À l'inverse, `ProfileAction` utilise le composant `Button` de Material 3, qui intègre déjà nativement ce rôle et ce comportement d'accessibilité.

### 7. Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?
Ils sont réutilisables car :
1. Ils prennent toutes leurs données en paramètres (nom, rôle, liste de compétences, texte du bouton, callbacks) au lieu d'avoir du texte figé en dur.
2. Ils acceptent tous un paramètre `modifier: Modifier = Modifier` appliqué à leur conteneur racine, ce qui laisse le parent gérer librement leur placement.
3. On peut facilement afficher deux profils complètement différents avec les mêmes composants, comme le montrent les deux fonctions `@Preview`.

## Aperçu (Previews)

### Variante 1 : Profil Junior (Disponible)
![Preview Profil Android Junior](screenshots/preview_junior.png)

### Variante 2 : Profil Senior (En mission)
![Preview Profil Développeuse Senior](screenshots/preview_senior.png)
