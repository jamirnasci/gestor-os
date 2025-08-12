package com.jjmobile.gestoros

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jjmobile.gestoros.databinding.ActivityConfigBinding
import com.jjmobile.gestoros.models.Servico
import com.jjmobile.gestoros.repository.ServicoRepository

class ConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.addServicoBtn.setOnClickListener {
            val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
            val view: View = layoutInflater.inflate(R.layout.cadastrar_servico_dialog, null)
            dialog.setView(view)
            dialog.setPositiveButton("Cadastrar") { dialog, which ->
                val sr: ServicoRepository = ServicoRepository(applicationContext)

                val nome: String = view.findViewById<EditText>(R.id.nomeCreateServico).text.toString()
                val preco: Double = view.findViewById<EditText>(R.id.precoCreateServico).text.toString().toDouble()
                val id: Long = sr.createServico(Servico(null, nome, preco))
                if(id == -1L){
                    Toast.makeText(applicationContext, "Falha ao adicionar serviço", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Serviço cadastrado", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            dialog.setNegativeButton("Fechar") { dialog, which ->
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}