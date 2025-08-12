package com.jjmobile.gestoros

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jjmobile.gestoros.databinding.ActivityFinanceiroBinding
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Ordem
import com.jjmobile.gestoros.repository.OrdemRepository

class FinanceiroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinanceiroBinding

    fun filterOrdens(ordens: List<Ordem>, status: String): List<Ordem>{
        return ordens.filter { item ->
            item.status.lowercase() == status.lowercase()
        }
    }

    fun sumValues(ordens: List<Ordem>, status: String?): Double {
        var total: Double = 0.00
        if(status != null){
            ordens.forEach { item ->
                if(item.status == status){
                    total += item.preco_final
                }
            }
            return total
        }
        ordens.forEach { item ->
            total += item.preco_final
        }
        return total
    }

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinanceiroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val or: OrdemRepository = OrdemRepository(applicationContext)
        val ordens: List<Ordem> = or.findAll()

        binding.emabertoValue.text = filterOrdens(ordens, Sqlite.ORDEM_STATUS_ABERTO).size.toString()
        binding.canceladasValue.text = filterOrdens(ordens, Sqlite.ORDEM_STATUS_CANCELADO).size.toString()
        binding.concluidasValue.text = filterOrdens(ordens, Sqlite.ORDEM_STATUS_CONCLUIDO).size.toString()
        binding.projetadoValue.text = String.format("R$ %.2f", sumValues(ordens, null))
        binding.lucroValue.text = String.format("R$ %.2f", sumValues(ordens, Sqlite.ORDEM_STATUS_CONCLUIDO))
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}