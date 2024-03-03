package com.example.crud_kotlin.room

import androidx.room.Database
import androidx.room.RoomDatabase

class DataBase {
    @Database(entities = [Entity.User::class], version = 1)
    abstract class AppDatabase:RoomDatabase() {
        abstract fun userDa(): DAO.UserDao
    }
}