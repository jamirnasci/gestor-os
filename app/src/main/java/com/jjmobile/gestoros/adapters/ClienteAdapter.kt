package com.jjmobile.gestoros.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jjmobile.gestoros.NovaOrdemActivity
import com.jjmobile.gestoros.R
import com.jjmobile.gestoros.models.Cliente

class ClienteAdapter(val context: Context, val clientes: List<Cliente>, val dialog: AlertDialog,val activity: NovaOrdemActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nome: TextView = view.findViewById(R.id.nomeContatoItem)
        val telefone: TextView = view.findViewById(R.id.telefoneContatoItem)
        val container: LinearLayout = view.findViewById(R.id.contatoContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cliente_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clientes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder

        viewHolder.nome.text = clientes[position].nome
        viewHolder.telefone.text = clientes[position].telefone
        val id = clientes[position].idcliente

        viewHolder.container.setOnClickListener {
            if (id != null) {
                activity.setIdCliente(id)
                dialog.dismiss()
            }
        }
    }
}