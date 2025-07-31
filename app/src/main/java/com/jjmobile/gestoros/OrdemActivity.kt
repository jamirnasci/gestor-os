package com.jjmobile.gestoros

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jjmobile.gestoros.databinding.ActivityOrdemBinding
import com.jjmobile.gestoros.models.Ordem
import com.jjmobile.gestoros.repository.OrdemRepository

class OrdemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdemBinding

    fun loadOrdem(id: Long): Ordem?{
        val or: OrdemRepository = OrdemRepository(applicationContext)
        return or.findById(id)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: Long = intent.getLongExtra("id", -1L)
        if(id != -1L){
            val ordem: Ordem? = loadOrdem(id)
            if(ordem != null){
                binding.clienteOrdem.text = ordem.cliente.nome
                binding.telefoneOrdem.text = ordem.cliente.telefone
                binding.emailOrdem.text = ordem.cliente.email
                binding.estadoOrdem.text = ordem.cliente.estado
                binding.cidadeOrdem.text = ordem.cliente.cidade
                binding.bairroOrdem.text = ordem.cliente.bairro
                binding.ruaOrdem.text = ordem.cliente.rua
                binding.numCasaOrdem.text = ordem.cliente.num_casa.toString()
                binding.servicoOrdem.text = ordem.servico.nome
                binding.precoOrdem.text = ordem.preco_final.toString()
                binding.descricaoOrdem.setText(ordem.descricao.toString())
            }
        }

    }
}