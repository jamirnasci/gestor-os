package com.jjmobile.gestoros.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jjmobile.gestoros.OrdensActivity
import com.jjmobile.gestoros.R
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Ordem

class OrdemAdapter(val context: Context, val ordens: List<Ordem>, val activity: OrdensActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nomeCliente: TextView = view.findViewById(R.id.nomeClienteItem)
        val dataOrdem: TextView = view.findViewById(R.id.dataOrdemItem)
        val ordemContainer: LinearLayout = view.findViewById<LinearLayout>(R.id.ordemContainer)
        val img: ImageView = view.findViewById<ImageView>(R.id.statusImg)
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
        viewHolder.dataOrdem.text = ordens[position].data_ordem
        viewHolder.nomeCliente.text = ordens[position].cliente.nome
        viewHolder.ordemContainer.setOnClickListener {
            val id: Long? = ordens[position].idordem
            if (id != null) {
                activity.openOrdem(id)
            }
        }

        when(ordens[position].status){
            Sqlite.ORDEM_STATUS_ABERTO -> viewHolder.img.setImageResource(R.drawable.exclamation_circle_svgrepo_com__1_)
            Sqlite.ORDEM_STATUS_CANCELADO -> viewHolder.img.setImageResource(R.drawable.cancel_svgrepo_com)
            Sqlite.ORDEM_STATUS_CONCLUIDO -> viewHolder.img.setImageResource(R.drawable.check_circle_svgrepo_com__2_)
        }
    }
}