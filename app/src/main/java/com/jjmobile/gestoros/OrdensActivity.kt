package com.jjmobile.gestoros

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jjmobile.gestoros.adapters.OrdemAdapter
import com.jjmobile.gestoros.databinding.ActivityOrdensBinding
import com.jjmobile.gestoros.models.Ordem
import com.jjmobile.gestoros.repository.OrdemRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class OrdensActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdensBinding

    fun filterByClients(nome: String, ordens: List<Ordem>): List<Ordem>{
        val filteredOrdens: List<Ordem> = ordens.filter { item ->
            item.cliente.nome.lowercase().contains(nome.lowercase())
        }
        return filteredOrdens
    }

    private fun filterByDate(data: String, ordens: List<Ordem>): List<Ordem>{
        val filteredOrdens: List<Ordem> = ordens.filter { item ->
            item.data_ordem == data
        }
        return filteredOrdens
    }

    fun openOrdem(id: Long){
        val ordemIntent: Intent = Intent(applicationContext, OrdemActivity::class.java)
        ordemIntent.putExtra("id", id)
        startActivity(ordemIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdensBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val or: OrdemRepository = OrdemRepository(applicationContext)
        val ordens: List<Ordem> = or.findAll()
        binding.ordemRecycler.adapter = OrdemAdapter(applicationContext, ordens, this@OrdensActivity)
        binding.ordemRecycler.layoutManager = LinearLayoutManager(applicationContext)

        binding.nomeClienteSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val nome = binding.nomeClienteSearch.text.toString()
                val filteredOrdens: List<Ordem> = filterByClients(nome, ordens)
                binding.ordemRecycler.adapter = OrdemAdapter(applicationContext, filteredOrdens, this@OrdensActivity)
            }

            override fun afterTextChanged(s: Editable?) {
                //
            }

        })

        binding.hojeCheckFilter.setOnCheckedChangeListener { buttonView, isChecked ->
            if(binding.hojeCheckFilter.isChecked){
                val calendar = Calendar.getInstance()
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(calendar.time)
                Toast.makeText(applicationContext, formattedDate, Toast.LENGTH_SHORT).show()
                val filteredOrdens = filterByDate(formattedDate, ordens)
                binding.ordemRecycler.adapter = OrdemAdapter(applicationContext, filteredOrdens, this@OrdensActivity)
            }else{
                binding.ordemRecycler.adapter = OrdemAdapter(applicationContext, ordens, this@OrdensActivity)
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}