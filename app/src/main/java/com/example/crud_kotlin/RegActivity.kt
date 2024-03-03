package com.example.crud_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud_kotlin.databinding.ActivityRegBinding

class RegActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}