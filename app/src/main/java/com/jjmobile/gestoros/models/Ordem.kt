package com.jjmobile.gestoros.models

data class Ordem(
    val id: Int?,
    val preco_final: Double,
    val status: String,
    val descricao: String,
    val idcliente: Int,
    val idservico: Int
)
