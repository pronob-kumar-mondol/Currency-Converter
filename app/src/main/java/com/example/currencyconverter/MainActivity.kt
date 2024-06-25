package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.main.CurrencyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            viewModel.convert(
                binding.ammount.text.toString(),
                binding.spinnerTo.selectedItem.toString(),
                binding.spinnerFrom.selectedItem.toString()
            )
        }

    }
}