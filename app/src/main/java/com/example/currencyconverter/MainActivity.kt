package com.example.currencyconverter

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.main.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: CurrencyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            viewModel.convert(
                binding.ammount.text.toString(),
                binding.spinnerFrom.selectedItem.toString(),
                binding.spinnerTo.selectedItem.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect(){event->
                when(event){
                    is CurrencyViewModel.CurrencyEvent.Success->{
                        binding.progressBar.isVisible=false
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text=event.result
                    }
                    is CurrencyViewModel.CurrencyEvent.Failure->{
                        binding.progressBar.isVisible=false
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text=event.error
                        Log.d("Error","It is an eror msge: ${event.error}")
                    }
                    is CurrencyViewModel.CurrencyEvent.Loading->{
                        binding.progressBar.isVisible=true
                    }
                    else->Unit
                }
            }
        }

    }
}