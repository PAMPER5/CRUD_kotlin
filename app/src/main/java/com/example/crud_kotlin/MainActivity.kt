package com.example.crud_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.crud_kotlin.databinding.ActivityMainBinding
import com.example.crud_kotlin.room.DataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userDatabase: DataBase.AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //id пользователя, который авторизовался
        val id = intent.getIntExtra("id",0)

        //инициализация бд
        userDatabase = Room.databaseBuilder(
            applicationContext,
            DataBase.AppDatabase::class.java,
            "user_database"
        ).build()

        //записываем всю информацию о авторизовавшемся пользователе
        CoroutineScope(Dispatchers.IO).launch {
            val user = userDatabase.userDao().getUser(id)

            //вывод информации о авторизовавшемся пользователе
            binding.etUserName.text = "Имя пользователя: ${user.name}"
            binding.etUserSurname.text = "Фамилия пользователя: ${user.surname}"
        }

        //выход из аккаунта
        binding.btExit.setOnClickListener {
            val intent = Intent(this, AutActivity::class.java)
            startActivity(intent)
            finish()
        }

        //переход в основную ленту
        binding.btLenta.setOnClickListener {
            val intent = Intent(this, LentaActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()
        }

        //переход к избранным
        binding.btFav.setOnClickListener {
            val intent = Intent(this, FavActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()
        }
    }
}