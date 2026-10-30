# Profil Développeur Compose

## 1. Titre du projet
**Fiche « Profil développeur » avec Jetpack Compose**

---

## 2. Description courte
Cet écran Android réalisé avec Jetpack Compose présente une fiche complète de profil développeur. Il affiche de manière hiérarchisée l'identité du profil (nom, rôle professionnel, courte biographie), une liste de compétences techniques clés, un statut interactif de disponibilité ainsi qu'un bouton d'action principal. L'écran est conçu selon les principes modernes de Compose : découpage en composants réutilisables et sans état (stateless), gestion claire des modificateurs et respect des standards d'accessibilité.

---

## 3. Notions utilisées
- **Fonctions composables (`@Composable`)** : découpage modulaire de l'interface en petits composants spécialisés.
- **Paramètres & Découplage** : tous les composants reçoivent leurs données en paramètres pour garantir leur réutilisabilité.
- **Modifiers & Chaînage de modifiers** : contrôle précis des dimensions, de l'espacement, du comportement au clic et de la sémantique.
- **Padding externe vs padding interne** : séparation nette entre le placement imposé par le parent et l'aération interne des composants.
- **Callbacks & Unidirectional Data Flow** : remontée des événements utilisateur sans logique métier figée dans les composants.
- **Accessibilité de base (`semantics`)** : ajout de rôles d'accessibilité (`Role.Button`) et de descriptions textuelles (`contentDescription`) pour les lecteurs d'écran (TalkBack).
- **Structure Android & Conteneurs** : `MainActivity`, `setContent`, `Scaffold`, `innerPadding`, `Surface`, `Column`.
- **Previews Compose (`@Preview`)** : visualisation instantanée de deux variantes différentes de profils directement dans l'environnement de développement.

---

## 4. Explication courte

### 1. Quels composables avez-vous créés ?
Nous avons créé six fonctions composables complémentaires :
1. `DeveloperProfileScreen` : composable représentant l'écran global qui intègre le `Scaffold` et gère les insets système (`innerPadding`).
2. `ProfileCard` : conteneur de type `Surface` représentant la fiche de profil et regroupant l'ensemble des sections d'informations.
3. `ProfileHeader` : composable dédié à l'en-tête du profil (nom du développeur, intitulé de poste / rôle et résumé biographique).
4. `SkillItem` : composable unitaire réutilisable affichant une compétence technique sous la forme d'un badge élégant.
5. `AvailabilityBadge` : zone interactive personnalisée affichant la disponibilité ou les modalités de contact avec sémantique d'accessibilité.
6. `ProfileAction` : composable réutilisable encapsulant le bouton d'action principal (`Button`).

### 2. Quel composable représente l’écran principal ?
Le composable `DeveloperProfileScreen` représente l'écran principal. Il intègre le conteneur structurel `Scaffold`, calcule le padding d'affichage global via `innerPadding` et accueille la fiche `ProfileCard`.

### 3. Où utilisez-vous un padding externe ?
Un padding externe est appliqué par le composant parent pour définir l'espacement et la position d'un composant enfant par rapport à son environnement extérieur :
- **Sur la carte de profil (`ProfileCard`)** : dans `DeveloperProfileScreen`, la carte reçoit `Modifier.padding(innerPadding).padding(16.dp)`, ce qui l'espace des bords de l'écran et des barres système.
- **Sur les compétences (`SkillItem`)** : chaque élément reçoit `Modifier.padding(vertical = 3.dp)` pour s'espacer des éléments voisins dans la liste.
- **Sur les sections de la carte** : `ProfileHeader`, `AvailabilityBadge` et `ProfileAction` reçoivent des marges externes verticales (`padding(top = ...)`, `padding(bottom = ...)`) pour aérer les sections successives.

### 4. Où utilisez-vous un padding interne ?
Un padding interne est appliqué à l'intérieur même d'un composant pour espacer son contenu par rapport à ses propres bordures :
- **À l'intérieur de `ProfileCard`** : la `Column` interne applique `Modifier.padding(16.dp)` sur elle-même afin que les textes, badges et boutons ne touchent pas les bords de la `Surface`.
- **À l'intérieur de `SkillItem`** : le `Text` applique `Modifier.padding(horizontal = 12.dp, vertical = 6.dp)` pour que le libellé respire dans son conteneur.
- **À l'intérieur de `AvailabilityBadge`** : le `Text` applique `Modifier.padding(horizontal = 12.dp, vertical = 8.dp)` pour créer une zone de frappe confortable.

### 5. Quel composant reçoit un callback ?
- `ProfileAction` reçoit en paramètre le callback `onActionClick: () -> Unit` déclenché lors du clic sur le `Button`.
- `AvailabilityBadge` reçoit le callback `onClick: () -> Unit = {}` déclenché lors du clic sur la zone de disponibilité.
- Ces callbacks sont transmis depuis `ProfileCard` et `DeveloperProfileScreen`. Le composant se contente de signaler l'événement à son parent (Event Hoisting), sans imposer de logique applicative avancée.

### 6. Quelle information d’accessibilité avez-vous ajoutée ?
Sur la zone interactive personnalisée `AvailabilityBadge`, nous avons ajouté le modificateur `.semantics { ... }` :
- `role = Role.Button` : informe les technologies d'assistance (lecteurs d'écran comme TalkBack) que cet élément graphique personnalisé doit être annoncé et traité comme un bouton cliquable.
- `contentDescription = "Statut de disponibilité : $availability. Cliquez pour interagir."` : fournit une description vocale explicite de l'état et de l'action possible.

*Note comparative* : pour le composant `ProfileAction`, l'utilisation du composant standard `Button` fournit nativement cette sémantique de bouton sans nécessiter d'attribut manuel.

### 7. Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?
Nos composants sont pleinement réutilisables pour trois raisons fondamentales :
1. **Paramétrage complet des données** : aucune donnée textuelle ou métier n'est écrite en dur dans les composants. `name`, `role`, `description`, `skills`, `availability` et les libellés de boutons sont injectés sous forme d'arguments.
2. **Contrôle du layout par le parent (`modifier: Modifier = Modifier`)** : chaque composant accepte un paramètre `modifier` avec une valeur par défaut et l'applique à son élément racine, permettant au parent d'ajuster son placement externe, sa largeur ou son padding.
3. **Composants sans état (Stateless) et découplés** : ils ne dépendent d'aucune logique métier fermée ou variable globale, et délèguent toutes leurs interactions via des lambdas callbacks. Ils peuvent donc être intégrés dans n'importe quel écran ou contexte applicatif.

---

## 5. Captures d'écran des Previews

### Variante 1 : Profil Développeur Android Junior (Disponible pour alternance)
*Montre un profil junior avec compétences Android et statut disponible en vert/bleu primaire.*

![Preview Profil Android Junior](screenshots/preview_junior.png)

### Variante 2 : Profil Développeuse Mobile Senior (En mission)
*Montre un profil senior / lead avec compétences cloud/multiplatform et statut en mission.*

![Preview Profil Développeuse Senior](screenshots/preview_senior.png)
