package com.example.crud_kotlin.room
import androidx.room.Entity
import androidx.room.PrimaryKey

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

    @Entity
    data class Fav(
        @PrimaryKey(autoGenerate = true)
        val idFav: Int = 0,
        val idUser: Int,
        val idRecord: Int,

    )

    @Entity
    data class Record(
        @PrimaryKey(autoGenerate = true)
        val idRecord: Int = 0,
        val name: String,
        val header: String,
        val price: Int,
        val count: Int
    )

}