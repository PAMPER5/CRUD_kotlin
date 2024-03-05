package com.example.crud_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_kotlin.databinding.ActivityFavBinding

class FavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //id пользователя, который авторизовался
        val id = intent.getIntExtra("id",0)

        //Обратно в личный кабинет
        binding.btBackMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()
        }
    }
}