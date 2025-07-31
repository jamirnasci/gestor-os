package com.jjmobile.gestoros.repository

import android.content.ContentValues
import android.content.Context
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Cliente
import com.jjmobile.gestoros.models.Servico

class ClienteRepository(context: Context) {

    private val sqlite: Sqlite = Sqlite(context)

    fun createCliente(cliente: Cliente): Long{
        val dbWriter = sqlite.writableDatabase

        val values = ContentValues().apply {
            put("nome_cliente", cliente.nome)
            put("telefone", cliente.telefone)
            put("email", cliente.email)
            put("estado", cliente.estado)
            put("cidade", cliente.cidade)
            put("bairro", cliente.bairro)
            put("rua", cliente.rua)
            put("num_casa", cliente.num_casa)
        }

        return dbWriter.insert("clientes", null, values)
    }
    fun findAll(): List<Cliente>{
        val db = sqlite.readableDatabase

        val projection = null
        val selection = null

        val cursor = db.query("clientes", projection, selection, null, null, null, null)

        val clientesFound = mutableListOf<Cliente>()

        with(cursor){
            while(moveToNext()){
                val id = getLong(getColumnIndexOrThrow("idcliente"))
                val nome = getString(getColumnIndexOrThrow("nome_cliente"))
                val telefone = getString(getColumnIndexOrThrow("telefone"))
                val email = getString(getColumnIndexOrThrow("email"))
                val estado = getString(getColumnIndexOrThrow("estado"))
                val cidade = getString(getColumnIndexOrThrow("cidade"))
                val bairro = getString(getColumnIndexOrThrow("bairro"))
                val rua = getString(getColumnIndexOrThrow("rua"))
                val numCasa = getInt(getColumnIndexOrThrow("num_casa"))
                clientesFound.add(Cliente(id, nome, telefone, email, estado, cidade, bairro, rua, numCasa))
            }
        }
        cursor.close()
        return clientesFound
    }
    fun findById(id: Long): Cliente? {
        val db = sqlite.readableDatabase

        val selection = "idcliente = ?"
        val selectionArgs = arrayOf("$id")
        val cursor = db.query("clientes", null, selection, selectionArgs, null, null, null)

        var clienteFound: Cliente? = null

        if (cursor.moveToFirst()) {
            clienteFound = Cliente(
                cursor.getLong(cursor.getColumnIndexOrThrow("idcliente")),
                cursor.getString(cursor.getColumnIndexOrThrow("nome_cliente")),
                cursor.getString(cursor.getColumnIndexOrThrow("telefone")),
                cursor.getString(cursor.getColumnIndexOrThrow("email")),
                cursor.getString(cursor.getColumnIndexOrThrow("estado")),
                cursor.getString(cursor.getColumnIndexOrThrow("cidade")),
                cursor.getString(cursor.getColumnIndexOrThrow("bairro")),
                cursor.getString(cursor.getColumnIndexOrThrow("rua")),
                cursor.getInt(cursor.getColumnIndexOrThrow("num_casa"))
            )
        }

        cursor.close()
        return clienteFound
    }

}