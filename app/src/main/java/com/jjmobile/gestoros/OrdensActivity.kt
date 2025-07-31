package com.jjmobile.gestoros

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjmobile.gestoros.adapters.OrdemAdapter
import com.jjmobile.gestoros.databinding.ActivityOrdensBinding
import com.jjmobile.gestoros.models.Ordem
import com.jjmobile.gestoros.repository.OrdemRepository

class OrdensActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdensBinding

    fun openOrdem(id: Long){
        val ordemIntent: Intent = Intent(applicationContext, OrdemActivity::class.java)
        ordemIntent.putExtra("id", id)
        startActivity(ordemIntent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdensBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val or: OrdemRepository = OrdemRepository(applicationContext)
        val ordens: List<Ordem> = or.findAll()
        binding.ordemRecycler.adapter = OrdemAdapter(applicationContext, ordens, this@OrdensActivity)
        binding.ordemRecycler.layoutManager = LinearLayoutManager(applicationContext)
    }
}