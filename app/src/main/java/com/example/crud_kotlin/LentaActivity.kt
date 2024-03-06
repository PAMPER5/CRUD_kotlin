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

        // Инициализация базы данных
        userDatabase = Room.databaseBuilder(
            applicationContext,
            DataBase.AppDatabase::class.java,
            "user_database"
        ).build()

        // Получение ID авторизованного пользователя
        val id = intent.getIntExtra("id", 0)

        // Загрузка информации о пользователе
        CoroutineScope(Dispatchers.IO).launch {
            val records = userDatabase.recordDao().getAllRecords()

            // Проверка на наличие записей в избранном
            val fav1Result = userDatabase.favDao().checkFav(1, id)
            val fav2Result = userDatabase.favDao().checkFav(2, id)
            val fav3Result = userDatabase.favDao().checkFav(3, id)

            // Установка цвета кнопок избранного
            setFavButtonColor(binding.btFav1, fav1Result)
            setFavButtonColor(binding.btFav2, fav2Result)
            setFavButtonColor(binding.btFav3, fav3Result)

            // Вывод информации о записях
            binding.tvHeader1.text = records[0].header
            binding.tvName1.text = records[0].name
            binding.tvCount1.text = "Колличество: ${records[0].count}"
            binding.tvPrice1.text = "Цена: ${records[0].price} золотых"

            binding.tvHeader2.text = records[1].header
            binding.tvName2.text = records[1].name
            binding.tvCount2.text = "Колличество: ${records[1].count}"
            binding.tvPrice2.text = "Цена: ${records[1].price} золотых"

            binding.tvHeader3.text = records[2].header
            binding.tvName3.text = records[2].name
            binding.tvCount3.text = "Колличество: ${records[2].count}"
            binding.tvPrice3.text = "Цена: ${records[2].price} золотых"

            // Обработчики кнопок избранного
            binding.btFav1.setOnClickListener { toggleFav(1, id, fav1Result) }
            binding.btFav2.setOnClickListener { toggleFav(2, id, fav2Result) }
            binding.btFav3.setOnClickListener { toggleFav(3, id, fav3Result) }
        }

        // Обработчик кнопки "Назад"
        binding.btBackMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()
        }
    }

    //функция смены цвета кнопки
    private fun setFavButtonColor(button: android.widget.Button, result: Entity.Fav?) {
        if (result == null) {
            button.setBackgroundColor(Color.GRAY)
        } else {
            button.setBackgroundColor(Color.RED)
        }
    }

    //функция добавления/удаления записи в/из Fav
    private fun toggleFav(idRecord: Int, idUser: Int, result: Entity.Fav?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (result == null) {
                userDatabase.favDao().insertFav(Entity.Fav(idRecord = idRecord, idUser = idUser))
                setFavButtonColor(binding.btFav1, userDatabase.favDao().checkFav(idRecord, idUser))
            } else {
                userDatabase.favDao().deleteFav(result)
                setFavButtonColor(binding.btFav1, null)
            }
        }
    }
}