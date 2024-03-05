package com.example.crud_kotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.crud_kotlin.databinding.ActivityLentaBinding
import com.example.crud_kotlin.room.DataBase
import com.example.crud_kotlin.room.Entity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LentaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLentaBinding
    private lateinit var userDatabase: DataBase.AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //инициализация бд
        userDatabase = Room.databaseBuilder(
            applicationContext,
            DataBase.AppDatabase::class.java,
            "user_database"
        ).build()

        //id пользователя, который авторизовался
        val id = intent.getIntExtra("id",0)

        //записываем всю информацию о авторизовавшемся пользователе
        CoroutineScope(Dispatchers.IO).launch {
            val record = userDatabase.recordDao().getAllRecords()

            //проверяем на наличие первой записи у пользователя в избранных
            CoroutineScope(Dispatchers.IO).launch {
                val result = userDatabase.favDao().checkFav(1, id)
                if (result == null) {
                    binding.btFav1.setBackgroundColor(Color.GRAY)
                } else {
                    binding.btFav1.setBackgroundColor(Color.RED)
                }
            }

            //проверяем на наличие второй записи у пользователя в избранных
            CoroutineScope(Dispatchers.IO).launch {
                val result = userDatabase.favDao().checkFav(2, id)
                if (result == null) {
                    binding.btFav2.setBackgroundColor(Color.GRAY)
                } else {
                    binding.btFav2.setBackgroundColor(Color.RED)
                }
            }

            //проверяем на наличие третье записи у пользователя в избранных
            CoroutineScope(Dispatchers.IO).launch {
                val result = userDatabase.favDao().checkFav(3, id)
                if (result == null) {
                    binding.btFav3.setBackgroundColor(Color.GRAY)
                } else {
                    binding.btFav3.setBackgroundColor(Color.RED)
                }
            }

            //вывод информации первой записи
            binding.tvHeader1.text = record[1].header
            binding.tvName1.text = record[1].name
            binding.tvCount1.text = "Колличество: ${record[1].count}"
            binding.tvPrice1.text = "Цена: ${record[1].price} золотых"

            //добавление/удкаление записи в/из избранных
            binding.btFav1.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = userDatabase.favDao().checkFav(1, id)
                    if (result == null) {
                        userDatabase.favDao().insertFav(Entity.Fav(idRecord = 1, idUser = id))
                        binding.btFav1.setBackgroundColor(Color.RED)
                    } else {
                        userDatabase.favDao().deleteFav(result)
                        binding.btFav1.setBackgroundColor(Color.GRAY)
                    }
                }
            }

            //вывод информации второй записи
            binding.tvHeader2.text = record[2].header
            binding.tvName2.text = record[2].name
            binding.tvCount2.text = "Колличество: ${record[2].count}"
            binding.tvPrice2.text = "Цена: ${record[2].price} золотых"

            //добавление/удкаление записи в/из избранных
            binding.btFav2.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = userDatabase.favDao().checkFav(2, id)
                    if (result == null) {
                        userDatabase.favDao().insertFav(Entity.Fav(idRecord = 2, idUser = id))
                        binding.btFav2.setBackgroundColor(Color.RED)
                    } else {
                        userDatabase.favDao().deleteFav(result)
                        binding.btFav2.setBackgroundColor(Color.GRAY)
                    }
                }
            }

            //вывод информации третьей записи
            binding.tvHeader3.text = record[3].header
            binding.tvName3.text = record[3].name
            binding.tvCount3.text = "Колличество: ${record[3].count}"
            binding.tvPrice3.text = "Цена: ${record[3].price} золотых"

            //добавление/удкаление записи в/из избранных
            binding.btFav3.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = userDatabase.favDao().checkFav(3, id)
                    if (result == null) {
                        userDatabase.favDao().insertFav(Entity.Fav(idRecord = 3, idUser = id))
                        binding.btFav3.setBackgroundColor(Color.RED)
                    } else {
                        userDatabase.favDao().deleteFav(result)
                        binding.btFav3.setBackgroundColor(Color.GRAY)
                    }
                }
            }
        }

        //Обратно в личный кабинет
        binding.btBackMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()
        }
    }
}