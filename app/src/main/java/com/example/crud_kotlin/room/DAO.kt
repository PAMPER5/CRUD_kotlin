package com.example.crud_kotlin.room
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
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

        @Query("SELECT * from User where idUser = :id")
        fun getUser(id: Int): Entity.User
    }

    @Dao
    interface FavDao{
        @Insert
        fun insertFav(fav: Entity.Fav)

        @Delete
        fun deleteFav(fav: Entity.Fav)

        @Query("SELECT * FROM Record WHERE idRecord IN (SELECT idRecord FROM Fav WHERE idUser = :idUser)")
        fun getFavRecords(idUser: Int): LiveData<List<Entity.Record>>?
    }

    @Dao
    interface RecordDao{
        @Query("SELECT * FROM Record")
        fun getAllRecords(): LiveData<List<Entity.Record>>
    }
}