package com.jjmobile.gestoros.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Sqlite(context: Context) : SQLiteOpenHelper(context, "data.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS clientes(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    telefone TEXT NOT NULL,
                    email TEXT NOT NULL,
                    estado TEXT NOT NULL,
                    cidade TEXT NOT NULL,
                    bairro TEXT NOT NULL,
                    rua TEXT NOT NULL,
                    num_casa INTEGER NOT NULL
                );         
            """.trimIndent())
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS servicos(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT UNIQUE NOT NULL,
                    preco DECIMAL(10, 2) NOT NULL
                );
            """.trimIndent())
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS ordens(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,                 
                    preco_final DECIMAL(10, 2) NOT NULL,
                    status TEXT NOT NULL,
                    descricao TEXT NOT NULL,
                    idcliente INTEGER NOT NULL,
                    idservico INTEGER NOT NULL,
                    FOREIGN KEY(idcliente) REFERENCES clientes(id),
                    FOREIGN KEY(idservico) REFERENCES servicos(id)
                );
            """.trimIndent())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.e("ok", "ok")
    }
}