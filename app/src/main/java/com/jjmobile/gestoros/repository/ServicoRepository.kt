package com.jjmobile.gestoros.repository

import android.content.ContentValues
import android.content.Context
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Cliente
import com.jjmobile.gestoros.models.Servico

class ServicoRepository(context: Context) {
    private val sqlite: Sqlite = Sqlite(context)

    fun createServico(servico: Servico){
        val db = sqlite.writableDatabase

        val values = ContentValues().apply {
            put("nome_servico", servico.nome)
            put("preco_servico", servico.preco)
        }

        db.insert("servicos", null, values)
    }
    fun findAll(): MutableList<Servico>{
        val db = sqlite.readableDatabase

        val projection = arrayOf("idservico", "nome_servico", "preco_servico")
        val selection = null

        val cursor = db.query("servicos", projection, selection, null, null, null, null)

        val servicosFound = mutableListOf<Servico>()

        with(cursor){
            while(moveToNext()){
                val id = getLong(getColumnIndexOrThrow("idservico"))
                val nome = getString(getColumnIndexOrThrow("nome_servico"))
                val preco = getDouble(getColumnIndexOrThrow("preco_servico"))
                servicosFound.add(Servico(id, nome, preco))
            }
        }
        cursor.close()
        return servicosFound
    }
}