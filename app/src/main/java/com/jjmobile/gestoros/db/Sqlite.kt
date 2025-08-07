package com.jjmobile.gestoros.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Sqlite(context: Context) : SQLiteOpenHelper(context, "data.db", null, 4) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS clientes(
                    idcliente INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome_cliente TEXT NOT NULL,
                    telefone TEXT NOT NULL,
                    email TEXT NOT NULL,
                    endereco TEXT NOT NULL
                );         
            """.trimIndent())
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS servicos(
                    idservico INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome_servico TEXT UNIQUE NOT NULL,
                    preco_servico DECIMAL(10, 2) NOT NULL
                );
            """.trimIndent())
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS ordens(
                    idordem INTEGER PRIMARY KEY AUTOINCREMENT,                 
                    preco_final DECIMAL(10, 2) NOT NULL,
                    status TEXT NOT NULL,
                    descricao TEXT NOT NULL,
                    data_ordem DATE NOT NULL,
                    idcliente INTEGER NOT NULL,
                    idservico INTEGER NOT NULL,
                    FOREIGN KEY(idcliente) REFERENCES clientes(id),
                    FOREIGN KEY(idservico) REFERENCES servicos(id)
                );
            """.trimIndent())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS clientes")
            db.execSQL("DROP TABLE IF EXISTS ordens")
            db.execSQL("DROP TABLE IF EXISTS servicos")
            onCreate(db)
        }
    }

    companion object{
        val ORDEM_STATUS_ABERTO = "ABERTO"
        val ORDEM_STATUS_CANCELADO = "CANCELADO"
        val ORDEM_STATUS_CONCLUIDO = "CONCLUIDO"
    }
}