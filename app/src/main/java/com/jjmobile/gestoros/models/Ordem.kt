package com.jjmobile.gestoros.models

data class Ordem(
    val idordem: Long?,
    val preco_final: Double,
    val status: String,
    val descricao: String,
    val cliente: Cliente,
    val servico: Servico
)
