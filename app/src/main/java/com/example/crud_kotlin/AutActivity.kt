package com.example.crud_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.crud_kotlin.databinding.ActivityAutBinding
import com.example.crud_kotlin.room.DataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            DataBase.AppDatabase::class.java,
            "user_database"
        ).build()

        //авторизация пользователя
        binding.btAction.setOnClickListener {
            if (binding.etLogin.text.isEmpty() || binding.etPassword.text.isEmpty()){
                Toast.makeText(this, "Введены не все данные", Toast.LENGTH_SHORT).show()
            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    val result = userDatabase.userDao().checkUser(binding.etLogin.text.toString(), binding.etPassword.text.toString())
                    withContext(Dispatchers.Main) {
                        if (result != null){
                            val intent = Intent(this@AutActivity, MainActivity::class.java)
                            intent.putExtra("id", result.idUser)
                            startActivity(intent)
                            finish()
                        }
                        else {
                            Toast.makeText(this@AutActivity, "Пользователя не существует", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        //переход в регистрацию
        binding.btBack.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}