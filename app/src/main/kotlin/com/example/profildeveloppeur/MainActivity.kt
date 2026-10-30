package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                    actionButtonText = "Contacter",
                    onActionClick = {
                        // Clic sur le bouton contacter
                    },
                    onAvailabilityClick = {
                        // Clic sur le badge de disponibilité
                    }
                )
            }
        }
    }
}
