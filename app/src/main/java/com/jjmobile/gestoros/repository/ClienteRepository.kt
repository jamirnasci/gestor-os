package com.jjmobile.gestoros.repository

import android.content.ContentValues
import android.content.Context
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Cliente

class ClienteRepository(context: Context) {

    private val db: Sqlite = Sqlite(context)

    fun createCliente(cliente: Cliente){
        val dbWriter = db.writableDatabase

        val values = ContentValues().apply {
            put("nome", cliente.nome)
            put("telefone", cliente.nome)
            put("email", cliente.nome)
            put("estado", cliente.nome)
            put("cidade", cliente.nome)
            put("bairro", cliente.nome)
            put("rua", cliente.nome)
            put("num_casa", cliente.nome)
        }

        dbWriter.insert("clientes", null, values)
    }
}