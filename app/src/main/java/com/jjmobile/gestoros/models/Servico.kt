package com.jjmobile.gestoros.models

class Servico(
    val idservico: Long?,
    val nome: String,
    val preco: Double
){
    override fun toString(): String {
        return this.nome
    }
}
