package com.jjmobile.gestoros.models

data class Cliente(
    var idcliente: Long?,
    val nome: String,
    val telefone: String,
    val email: String,
    val estado : String,
    val cidade : String,
    val bairro : String,
    val rua : String,
    val num_casa: Int
)
