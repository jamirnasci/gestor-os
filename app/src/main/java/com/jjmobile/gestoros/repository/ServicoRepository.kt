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
            put("nome", servico.nome)
            put("preco", servico.preco)
        }

        db.insert("servicos", null, values)
    }
    fun findAll(): MutableList<Servico>{
        val db = sqlite.readableDatabase

        val projection = arrayOf("id", "nome", "preco")
        val selection = null

        val cursor = db.query("servicos", projection, selection, null, null, null, null)

        val servicosFound = mutableListOf<Servico>()

        with(cursor){
            while(moveToNext()){
                val id = getInt(getColumnIndexOrThrow("id"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val preco = getDouble(getColumnIndexOrThrow("preco"))
                servicosFound.add(Servico(id, nome, preco))
            }
        }
        cursor.close()
        return servicosFound
    }
}