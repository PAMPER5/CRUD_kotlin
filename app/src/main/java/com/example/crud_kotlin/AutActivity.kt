package com.example.crud_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.crud_kotlin.databinding.ActivityAutBinding
import com.example.crud_kotlin.room.DataBase

class AutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAutBinding
    private lateinit var userDatabase: DataBase.AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //инициализация бд
        userDatabase = Room.databaseBuilder(
            applicationContext,
            DataBase.AppDatabase::class.java, "user_database"
        ).build()

        //авторизация пользователя
        binding.btAction.setOnClickListener {

        }

        //переход в регистрацию
        binding.btBack.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}