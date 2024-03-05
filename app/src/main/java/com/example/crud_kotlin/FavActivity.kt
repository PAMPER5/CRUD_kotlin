package com.example.crud_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.crud_kotlin.databinding.ActivityFavBinding
import com.example.crud_kotlin.room.DataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavBinding
    private lateinit var userDatabase: DataBase.AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //инициализация бд
        userDatabase = Room.databaseBuilder(
            applicationContext,
            DataBase.AppDatabase::class.java,
            "user_database"
        ).build()

        //id пользователя, который авторизовался
        val id = intent.getIntExtra("id",0)

        CoroutineScope(Dispatchers.IO).launch {
            val record = userDatabase.favDao().getFavRecords(id)
            if (record != null) {
                if (record.isNotEmpty()) {
                    //вывод информации первой записи
                    binding.tvHeader1.text = record[0].header
                    binding.tvName1.text = record[0].name
                    binding.tvCount1.text = "Колличество: ${record[0].count}"
                    binding.tvPrice1.text = "Цена: ${record[0].price} золотых"
                }

                if (record.size > 1) {
                    //вывод информации второй записи
                    binding.tvHeader2.text = record[1].header
                    binding.tvName2.text = record[1].name
                    binding.tvCount2.text = "Колличество: ${record[1].count}"
                    binding.tvPrice2.text = "Цена: ${record[1].price} золотых"
                }

                if (record.size > 2) {
                    //вывод информации третьей записи
                    binding.tvHeader3.text = record[2].header
                    binding.tvName3.text = record[2].name
                    binding.tvCount3.text = "Колличество: ${record[2].count}"
                    binding.tvPrice3.text = "Цена: ${record[2].price} золотых"
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