package com.example.profildeveloppeur

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProfileTest {
    @Test
    fun verifyProfileSkillsList() {
        val skills = listOf("Kotlin", "Jetpack Compose", "Git")
        assertEquals(3, skills.size)
        assertTrue(skills.contains("Jetpack Compose"))
    }
}
