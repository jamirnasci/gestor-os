package com.jjmobile.gestoros

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jjmobile.gestoros.adapters.ClienteAdapter
import com.jjmobile.gestoros.databinding.ActivityNovaOrdemBinding
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Cliente
import com.jjmobile.gestoros.models.Ordem
import com.jjmobile.gestoros.models.Servico
import com.jjmobile.gestoros.repository.ClienteRepository
import com.jjmobile.gestoros.repository.OrdemRepository
import com.jjmobile.gestoros.repository.ServicoRepository

class NovaOrdemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNovaOrdemBinding
    private lateinit var cr: ClienteRepository
    private lateinit var or: OrdemRepository
    private lateinit var sr: ServicoRepository

    private var selectedServico: Servico? = null
    private var idCliente: Long? = null

    fun setIdCliente(id: Long){
        idCliente = id
    }

    fun loadServicos(): List<Servico>{
        return sr.findAll()
    }

    fun loadClientes(): List<Cliente>{
        return cr.findAll()
    }

    fun filterClientes(nome: String, clientes: List<Cliente>): List<Cliente>{
        val filteredClientes: List<Cliente> = clientes.filter { item ->
            item.nome.lowercase().contains(nome.lowercase())
        }
        return filteredClientes
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    fun showClientDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Clientes Resgistrados")
        val view: View = layoutInflater.inflate(R.layout.clientes_dialog, null)
        builder.setView(view)
        val dialog: AlertDialog = builder.create()
        val recycler = view.findViewById<RecyclerView>(R.id.clientesRecycler)
        val searchNome = view.findViewById<EditText>(R.id.searchClienteDialogEdit)

        val clientes = cr.findAll()
        searchNome.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val nome: String = searchNome.text.toString()
                val filteredClientes = filterClientes(nome, clientes)
                recycler.adapter = ClienteAdapter(applicationContext, filteredClientes, dialog, this@NovaOrdemActivity)
            }

            override fun afterTextChanged(s: Editable?) {
                //
            }

        })
        recycler.adapter = ClienteAdapter(applicationContext, clientes, dialog, this@NovaOrdemActivity)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        dialog.setOnDismissListener {
            if(idCliente != null){
                val cliente: Cliente? = cr.findById(idCliente!!)
                if(cliente != null){
                    binding.nomeInput.setText(cliente.nome)
                    binding.telefoneInput.setText(cliente.telefone)
                    binding.emailInput.setText(cliente.email)
                    binding.enderecoInput.setText(cliente.endereco)
                }
            }
        }
        dialog.show()
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovaOrdemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cr = ClienteRepository(applicationContext)
        or = OrdemRepository(applicationContext)
        sr = ServicoRepository(applicationContext)
        binding.datePicker.visibility = View.GONE
        setSupportActionBar(binding.toolbar)

        val servicos: List<Servico> = loadServicos()
        val adapter: ArrayAdapter<Servico> = ArrayAdapter<Servico>(applicationContext, android.R.layout.simple_spinner_item, servicos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.servicosSpinner.adapter = adapter

        binding.servicosSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedServico = parent?.getItemAtPosition(position) as Servico
                if(selectedServico != null){
                    binding.precoInput.setText(selectedServico!!.preco.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

        }

        binding.procurarClienteBtn.setOnClickListener {
            showClientDialog()
        }

        binding.datePicker.setOnDateChangeListener { view, year, month, dayOfMonth ->
            binding.datePicker.visibility = View.INVISIBLE
            val d = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth
            val m = if (month < 10) "0" + (month + 1) else month
            binding.dataInput.setText("$d/$m/$year")
        }
        binding.openDatePickerBtn.setOnClickListener {
            binding.datePicker.visibility = View.VISIBLE
            binding.datePicker.invalidate()
            binding.datePicker.requestLayout()
        }
        binding.criarOrdemBtn.setOnClickListener {
            val nome: String = binding.nomeInput.text.toString()
            val email: String = binding.emailInput.text.toString()
            val telefone: String = binding.telefoneInput.text.toString()
            val endereco: String = binding.enderecoInput.text.toString()
            val dataOrdem: String = binding.dataInput.text.toString()
            val preco: String = binding.precoInput.text.toString()
            val descricao: String = binding.descricaoInput.text.toString()
            if(
                nome.isEmpty() ||
                email.isEmpty()  ||
                telefone.isEmpty() ||
                endereco.isEmpty() ||
                dataOrdem.isEmpty() ||
                preco.isEmpty() ||
                descricao.isEmpty() ||
                selectedServico == null
            ){
                Toast.makeText(applicationContext, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{

                val cliente: Cliente = Cliente(null, nome, telefone, email, endereco)
                if(idCliente == null){
                    idCliente = cr.createCliente(cliente)
                    if(idCliente == -1L){
                        Toast.makeText(applicationContext, "Falha ao cadastrar cliente", Toast.LENGTH_SHORT).show()
                    }
                }

                cliente.idcliente = idCliente
                val ordem: Ordem = Ordem(null, preco.toDouble(), Sqlite.ORDEM_STATUS_ABERTO, descricao, dataOrdem, cliente, selectedServico!!)
                val idOrdem: Long = or.createOrdem(ordem)
                if(idOrdem == -1L){
                    Toast.makeText(applicationContext, "Falha ao criar ordem $idOrdem", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Ordem criada com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}