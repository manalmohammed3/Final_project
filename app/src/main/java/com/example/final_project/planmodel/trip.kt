package com.example.final_project.planmodel

data class Trip(
    val title: String,
    val description: String,
    val dueDate: String,
    val creationDate: String,
    val isComplete: Boolean = false,
    val isPast: Boolean
)




