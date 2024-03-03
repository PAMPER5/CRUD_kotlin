package com.example.crud_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud_kotlin.databinding.ActivityAutBinding

class AutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}