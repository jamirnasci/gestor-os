package com.jjmobile.gestoros

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jjmobile.gestoros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.novaOrdemBtn.setOnClickListener {
            val novaOrdemIntent: Intent = Intent(applicationContext, NovaOrdemActivity::class.java)
            startActivity(novaOrdemIntent)
        }
        binding.ordensBtn.setOnClickListener {
            val ordensIntent: Intent = Intent(applicationContext, OrdensActivity::class.java)
            startActivity(ordensIntent)
        }
        binding.financeiroBtn.setOnClickListener {
            val financeiroIntent: Intent = Intent(applicationContext, FinanceiroActivity::class.java)
            startActivity(financeiroIntent)
        }
        binding.confBtn.setOnClickListener {
            val confIntent: Intent = Intent(applicationContext, ConfigActivity::class.java)
            startActivity(confIntent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}