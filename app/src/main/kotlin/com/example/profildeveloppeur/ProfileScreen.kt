package com.example.profildeveloppeur

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

// Écran principal avec Scaffold et innerPadding
@Composable
fun DeveloperProfileScreen(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availability: String,
    isAvailable: Boolean = true,
    actionButtonText: String = "Contacter",
    onActionClick: () -> Unit = {},
    onAvailabilityClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        ProfileCard(
            name = name,
            role = role,
            description = description,
            skills = skills,
            availability = availability,
            isAvailable = isAvailable,
            actionButtonText = actionButtonText,
            onActionClick = onActionClick,
            onAvailabilityClick = onAvailabilityClick,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

// Carte principale contenant les informations du profil
@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availability: String,
    isAvailable: Boolean,
    actionButtonText: String,
    onActionClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ProfileHeader(
                name = name,
                role = role,
                description = description,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Compétences clés",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )

            skills.forEach { skill ->
                SkillItem(
                    skill = skill,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                )
            }

            AvailabilityBadge(
                availability = availability,
                isAvailable = isAvailable,
                onClick = onAvailabilityClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp)
            )

            ProfileAction(
                buttonText = actionButtonText,
                onActionClick = onActionClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

// En-tête avec nom, rôle et description
@Composable
fun ProfileHeader(
    name: String,
    role: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 2.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
    }
}

// Badge pour une compétence
@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text = "• $skill",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// Zone cliquable personnalisée pour la disponibilité
@Composable
fun AvailabilityBadge(
    availability: String,
    isAvailable: Boolean,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val containerColor = if (isAvailable) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }

    val contentColor = if (isAvailable) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }

    Surface(
        modifier = modifier
            .semantics {
                role = Role.Button
                contentDescription = "Statut de disponibilité : $availability. Cliquez pour interagir."
            }
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.small,
        color = containerColor
    ) {
        Text(
            text = if (isAvailable) "✓ $availability" else "⏳ $availability",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = contentColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

// Bouton d'action principal
@Composable
fun ProfileAction(
    buttonText: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onActionClick,
        modifier = modifier
    ) {
        Text(text = buttonText)
    }
}

// Previews
@Preview(showBackground = true, name = "Profil Android Junior - Disponible")
@Composable
fun PreviewDeveloperProfileJunior() {
    ProfilDeveloppeurTheme {
        DeveloperProfileScreen(
            name = "Alexandre Martin",
            role = "Développeur Android Junior",
            description = "Étudiant passionné par Kotlin et le développement mobile moderne. À la recherche d'une alternance pour concevoir des applications performantes avec Jetpack Compose.",
            skills = listOf(
                "Kotlin & Coroutines",
                "Jetpack Compose & Material 3",
                "Git & GitHub",
                "Architecture Android (bases)"
            ),
            availability = "Disponible pour une alternance (Septembre 2026)",
            isAvailable = true,
            actionButtonText = "Contacter par email",
            onActionClick = {
                // Clic contacter
            },
            onAvailabilityClick = {
                // Clic statut
            }
        )
    }
}

@Preview(showBackground = true, name = "Profil Développeuse Senior - En mission")
@Composable
fun PreviewDeveloperProfileSenior() {
    ProfilDeveloppeurTheme {
        DeveloperProfileScreen(
            name = "Sophie Dubois",
            role = "Lead Développeuse Mobile & Cloud",
            description = "Plus de 6 ans d'expérience dans l'écosystème mobile et back-end. Spécialisée dans la conception d'interfaces déclaratives et d'architectures résilientes.",
            skills = listOf(
                "Jetpack Compose & Kotlin Multiplatform",
                "API REST & Microservices",
                "CI/CD Mobile & Automatisation",
                "UI Testing & Accessibilité (WCAG)"
            ),
            availability = "Actuellement en mission (disponible dès Janvier 2027)",
            isAvailable = false,
            actionButtonText = "Voir le portfolio",
            onActionClick = {
                // Clic portfolio
            },
            onAvailabilityClick = {
                // Clic statut
            }
        )
    }
}
