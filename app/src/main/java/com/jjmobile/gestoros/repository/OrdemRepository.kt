package com.jjmobile.gestoros.repository

import android.content.ContentValues
import android.content.Context
import com.jjmobile.gestoros.db.Sqlite
import com.jjmobile.gestoros.models.Cliente
import com.jjmobile.gestoros.models.Ordem

class OrdemRepository(context: Context) {
    private val db: Sqlite = Sqlite(context)

    fun createOrdem(ordem: Ordem){
        val dbWriter = db.writableDatabase

        val values = ContentValues().apply {
            put("preco_final", ordem.preco_final)
            put("status", ordem.status)
            put("descricao", ordem.descricao)
            put("idcliente", ordem.idcliente)
            put("idservico", ordem.idservico)
        }

        dbWriter.insert("ordens", null, values)
    }
}