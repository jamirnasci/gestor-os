package com.jjmobile.gestoros.repository

import android.content.ContentValues
import android.content.Context
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Cliente
import com.jjmobile.gestoros.models.Ordem
import com.jjmobile.gestoros.models.Servico

class OrdemRepository(context: Context) {
    private val sqlite: Sqlite = Sqlite(context)

    fun createOrdem(ordem: Ordem): Long{
        val dbWriter = sqlite.writableDatabase

        val values = ContentValues().apply {
            put("preco_final", ordem.preco_final)
            put("status", ordem.status)
            put("descricao", ordem.descricao)
            put("idcliente", ordem.cliente.idcliente)
            put("idservico", ordem.servico.idservico)
        }

        return dbWriter.insert("ordens", null, values)
    }

    fun findAll(): List<Ordem>{
        val db = sqlite.readableDatabase

        val sql = """
            SELECT
                *
            FROM ordens 
                INNER JOIN clientes ON ordens.idcliente = clientes.idcliente
                INNER JOIN servicos ON ordens.idservico = servicos.idservico
        """.trimIndent()
        val cursor = db.rawQuery(sql, null)

        val ordens: MutableList<Ordem> = mutableListOf<Ordem>()

        with(cursor){
            while(moveToNext()){
                val nome: String = getString(getColumnIndexOrThrow("nome_cliente"))
                val telefone: String = getString(getColumnIndexOrThrow("telefone"))
                val email: String = getString(getColumnIndexOrThrow("email"))
                val estado : String = getString(getColumnIndexOrThrow("estado"))
                val cidade : String = getString(getColumnIndexOrThrow("cidade"))
                val bairro : String = getString(getColumnIndexOrThrow("bairro"))
                val rua : String = getString(getColumnIndexOrThrow("rua"))
                val num_casa: Int = getInt(getColumnIndexOrThrow("num_casa"))

                val idordem: Long? = getLong(getColumnIndexOrThrow("idordem"))
                val precoFinal: Double = getDouble(getColumnIndexOrThrow("preco_final"))
                val status: String = getString(getColumnIndexOrThrow("status"))
                val descricao: String = getString(getColumnIndexOrThrow("descricao"))

                val nomeServico: String = getString(getColumnIndexOrThrow("nome_servico"))
                val precoServico: Double = getDouble(getColumnIndexOrThrow("preco_servico"))

                val cliente: Cliente = Cliente(null, nome, telefone, email, estado, cidade, bairro, rua, num_casa)
                val servico: Servico = Servico(null, nomeServico, precoServico)

                ordens.add(Ordem(idordem, precoFinal, status, descricao, cliente, servico))
            }
        }

        return ordens
    }

    fun findById(id: Long): Ordem? {
        val db = sqlite.readableDatabase

        val sql = """
            SELECT
                *
            FROM ordens 
                INNER JOIN clientes ON ordens.idcliente = clientes.idcliente
                INNER JOIN servicos ON ordens.idservico = servicos.idservico
            WHERE ordens.idordem = ?
        """.trimIndent()
        val selectionArgs = arrayOf("$id")
        val cursor = db.rawQuery(sql, selectionArgs)
        var ordem: Ordem? = null
        if(cursor.moveToFirst()) {
            with(cursor){
                val nome: String = getString(getColumnIndexOrThrow("nome_cliente"))
                val telefone: String = getString(getColumnIndexOrThrow("telefone"))
                val email: String = getString(getColumnIndexOrThrow("email"))
                val estado: String = getString(getColumnIndexOrThrow("estado"))
                val cidade: String = getString(getColumnIndexOrThrow("cidade"))
                val bairro: String = getString(getColumnIndexOrThrow("bairro"))
                val rua: String = getString(getColumnIndexOrThrow("rua"))
                val num_casa: Int = getInt(getColumnIndexOrThrow("num_casa"))

                val idordem: Long? = getLong(getColumnIndexOrThrow("idordem"))
                val precoFinal: Double = getDouble(getColumnIndexOrThrow("preco_final"))
                val status: String = getString(getColumnIndexOrThrow("status"))
                val descricao: String = getString(getColumnIndexOrThrow("descricao"))

                val nomeServico: String = getString(getColumnIndexOrThrow("nome_servico"))
                val precoServico: Double = getDouble(getColumnIndexOrThrow("preco_servico"))

                val cliente: Cliente = Cliente(null, nome, telefone, email, estado, cidade, bairro, rua, num_casa)
                val servico: Servico = Servico(null, nomeServico, precoServico)

                ordem = Ordem(idordem, precoFinal, status, descricao, cliente, servico)
            }
        }

        return ordem
    }
    fun updateStatus(status: String, id: Long): Int{
        val dbWriter = sqlite.writableDatabase

        val values = ContentValues().apply {
            put("status", status)
        }
        val where = "idordem = ?"
        val whereArgs = arrayOf("$id")
        return dbWriter.update("ordens", values, where, whereArgs)
    }
    fun deleteOrdem(id: Long): Int{
        val dbWriter = sqlite.writableDatabase

        val where = "idordem = ?"
        val whereArgs = arrayOf("$id")
        return dbWriter.delete("ordens", where, whereArgs)
    }
}