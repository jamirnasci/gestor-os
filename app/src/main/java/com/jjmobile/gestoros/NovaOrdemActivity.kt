package com.jjmobile.gestoros

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jjmobile.gestoros.databinding.ActivityNovaOrdemBinding
import com.jjmobile.gestoros.models.Servico
import com.jjmobile.gestoros.repository.ServicoRepository

class NovaOrdemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNovaOrdemBinding

    fun loadServicos(): List<Servico>{
        val sr: ServicoRepository = ServicoRepository(applicationContext)
        return sr.findAll()
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovaOrdemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val servicos: List<Servico> = loadServicos()
        val adapter: ArrayAdapter<Servico> = ArrayAdapter<Servico>(applicationContext, android.R.layout.simple_spinner_item, servicos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.servicosSpinner.adapter = adapter

        binding.servicosSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selecteditem: Servico = parent?.getItemAtPosition(position) as Servico
                Toast.makeText(applicationContext, "${selecteditem.nome} ${selecteditem.id}", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

        }
    }
}