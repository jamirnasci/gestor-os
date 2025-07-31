package com.jjmobile.gestoros.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jjmobile.gestoros.OrdemActivity
import com.jjmobile.gestoros.OrdensActivity
import com.jjmobile.gestoros.R
import com.jjmobile.gestoros.models.Ordem
import com.jjmobile.gestoros.repository.ClienteRepository

class OrdemAdapter(val context: Context, val ordens: List<Ordem>, val activity: OrdensActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nomeCliente: TextView = view.findViewById(R.id.nomeClienteItem)
        val descricaoOrdem: TextView = view.findViewById(R.id.descricaoOrdemItem)
        val ordemContainer: LinearLayout = view.findViewById<LinearLayout>(R.id.ordemContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.ordem_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ordens.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: ViewHolder = holder as ViewHolder
        viewHolder.descricaoOrdem.setText(ordens[position].descricao)
        viewHolder.nomeCliente.setText(ordens[position].cliente.nome)
        viewHolder.ordemContainer.setOnClickListener {
            val id: Long? = ordens[position].idordem
            if (id != null) {
                activity.openOrdem(id)
            }
        }
    }
}