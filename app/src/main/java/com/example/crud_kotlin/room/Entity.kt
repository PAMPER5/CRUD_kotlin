package com.example.crud_kotlin.room
import androidx.room.PrimaryKey
import androidx.room.Entity

class Entity {
    @Entity
    data class User(
        @PrimaryKey(autoGenerate = true)
        val idUser: Int = 0,
        val login: String,
        val password: String,
        val name: String,
        val surname: String
    )
}