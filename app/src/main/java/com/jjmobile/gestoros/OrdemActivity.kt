package com.jjmobile.gestoros

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jjmobile.gestoros.databinding.ActivityOrdemBinding
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Ordem
import com.jjmobile.gestoros.repository.OrdemRepository

class OrdemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdemBinding
    private lateinit var or: OrdemRepository
    fun loadOrdem(id: Long): Ordem?{
        val or: OrdemRepository = OrdemRepository(applicationContext)
        return or.findById(id)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        or = OrdemRepository(applicationContext)
        val id: Long = intent.getLongExtra("id", -1L)

        if(id != -1L){
            val ordem: Ordem? = loadOrdem(id)
            if(ordem != null){
                binding.clienteOrdem.text = ordem.cliente.nome
                binding.telefoneOrdem.text = ordem.cliente.telefone
                binding.emailOrdem.text = ordem.cliente.email
                binding.endereco.text = ordem.cliente.endereco
                binding.dataOrdem.text = ordem.data_ordem
                binding.servicoOrdem.text = ordem.servico.nome
                binding.precoOrdem.text = String.format("R$ %.2f", ordem.preco_final)
                binding.descricaoOrdem.setText(ordem.descricao.toString())
            }
        }
        binding.cancelarBtn.setOnClickListener {
            val rowsAffected: Int = or.updateStatus(Sqlite.ORDEM_STATUS_CANCELADO, id)
            if(rowsAffected > 0){
                Toast.makeText(applicationContext, "Ordem atualizada", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
        binding.concluirBtn.setOnClickListener {
            val rowsAffected: Int = or.updateStatus(Sqlite.ORDEM_STATUS_CONCLUIDO, id)
            if(rowsAffected > 0){
                Toast.makeText(applicationContext, "Ordem atualizada", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
        binding.apagarOrdemBtn.setOnClickListener {
            val rowsAffected: Int = or.deleteOrdem(id)
            if(rowsAffected > 0){
                Toast.makeText(applicationContext, "Ordem removida com sucesso", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        binding.copyPhone.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("phone", binding.telefoneOrdem.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(applicationContext, "Telefone copiado !", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}