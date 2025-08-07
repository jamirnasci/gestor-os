package com.jjmobile.gestoros.models

data class Cliente(
    var idcliente: Long?,
    val nome: String,
    val telefone: String,
    val email: String,
    val endereco : String
)
