package com.example.crud_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.crud_kotlin.databinding.ActivityRegBinding
import com.example.crud_kotlin.room.DataBase
import com.example.crud_kotlin.room.Entity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegBinding
    private lateinit var userDatabase: DataBase.AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //инициализация бд
        userDatabase = Room.databaseBuilder(
            applicationContext,
            DataBase.AppDatabase::class.java, "user_database"
        ).build()

        //регистрация пользователя
        binding.btAction.setOnClickListener {
            if (binding.etName.text.isEmpty() || binding.etSurname.text.isEmpty() || binding.etLogin.text.isEmpty() || binding.etPassword.text.isEmpty()){
                Toast.makeText(this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show()
            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    val userExist = userDatabase.userDao().checkLogin(binding.etLogin.text.toString())
                    if (userExist != null){
                        Toast.makeText(this@RegActivity, "Логин не доступен", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val user = Entity.User(
                            name = binding.etName.text.toString(),
                            surname = binding.etSurname.text.toString(),
                            login = binding.etLogin.text.toString(),
                            password = binding.etPassword.text.toString(),
                        )
                        userDatabase.userDao().insertUser(user)
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@RegActivity, "Запись добавлена", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        //переход в авторизацию
        binding.btBack.setOnClickListener {
            val intent = Intent(this, AutActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}