package com.example.crud_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val login = intent.getStringExtra("login")
        binding.etHello.text = "Привет $login"

        //выход из аккаунта
        binding.btExit.setOnClickListener {
            val intent = Intent(this, AutActivity::class.java)
            startActivity(intent)
            finish()
        }

        //переход в основную ленту
        binding.btLenta.setOnClickListener {
            val intent = Intent(this, LentaActivity::class.java)
            startActivity(intent)
            finish()
        }

        //переход к избранным
        binding.btFav.setOnClickListener {
            val intent = Intent(this, FavActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}