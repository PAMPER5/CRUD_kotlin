package com.example.crud_kotlin.room
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

interface DAO {
    @Dao
    interface UserDao{
        @Insert
        fun insertUser(user: Entity.User)

        @Query("SELECT * from User where login = :login and password =:password")
        fun checkUser(login: String, password: String): Entity.User?

        @Query("SELECT * from User where login = :login")
        fun checkLogin(login: String): Entity.User?
    }
}