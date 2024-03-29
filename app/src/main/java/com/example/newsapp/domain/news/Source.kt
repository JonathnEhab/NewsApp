package com.example.newsapp.domain.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


data class Source(
    val id: String,
    val name: String
)