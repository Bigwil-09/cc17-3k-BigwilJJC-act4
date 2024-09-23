package com.example.tipcalculator

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.calcu.setOnClickListener { calculateTip() }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun calculateTip() {
        val cost = binding.cost.text.toString().toDouble()
        val selectedID = binding.tips.checkedRadioButtonId
        val tipPercentage = when(selectedID) {
            R.id.twenty -> 0.2
            R.id.eighteen -> 0.18
            else -> 0.15
        }
        var tip = cost*tipPercentage
        var roundUp = binding.round.isChecked
        if (roundUp) {
            tip = ceil(tip)
        }
        var currencyTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.result.text = currencyTip
    }
}