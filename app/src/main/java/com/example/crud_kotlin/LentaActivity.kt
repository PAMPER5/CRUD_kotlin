package com.example.crud_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_kotlin.databinding.ActivityLentaBinding

class LentaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLentaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLentaBinding.inflate(layoutInflater)
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