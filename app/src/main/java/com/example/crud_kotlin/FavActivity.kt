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
import kotlinx.coroutines.withContext

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
            val records = userDatabase.favDao().getFavRecords(id)

            withContext(Dispatchers.Main) {
                if (!records.isNullOrEmpty()) {
                    // Вывод информации о записях
                    for ((index, record) in records.withIndex()) {
                        when (index) {
                            0 -> {
                                binding.tvHeader1.text = record.header
                                binding.tvName1.text = record.name
                                binding.tvCount1.text = "Количество: ${record.count}"
                                binding.tvPrice1.text = "Цена: ${record.price} золотых"
                            }
                            1 -> {
                                binding.tvHeader2.text = record.header
                                binding.tvName2.text = record.name
                                binding.tvCount2.text = "Количество: ${record.count}"
                                binding.tvPrice2.text = "Цена: ${record.price} золотых"
                            }
                            2 -> {
                                binding.tvHeader3.text = record.header
                                binding.tvName3.text = record.name
                                binding.tvCount3.text = "Количество: ${record.count}"
                                binding.tvPrice3.text = "Цена: ${record.price} золотых"
                            }
                        }
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