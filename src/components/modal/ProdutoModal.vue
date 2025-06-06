<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Edit Profile" :closable="false">
            <div class="d-flex">
                <div>
                    <span class="text-success">Produto</span>
                    <div>
                        <label for="">Marca</label><br>
                        <InputText v-model="produto.marca" type="text" />
                    </div>
                    <div>
                        <label for="">Modelo</label><br>
                        <InputText v-model="produto.modelo" type="text" />
                    </div>
                    <div>
                        <label for="">Ano</label><br>
                        <InputNumber v-model="produto.ano" type="number" />
                    </div>
                    <div>
                        <label for="">Situação</label><br>
                        <InputText v-model="produto.situacao" type="text" />
                    </div>
                </div>
                <Divider layout="vertical" />
                <div>
                    <span class="text-success">Cliente</span>
                    <div>Cliente: {{ cliente.nome }}</div>
                    <div>CPF/CNPJ: {{ cliente.cpf }}</div>
                    <div>Telefone: {{ cliente.telefone }}</div>
                    <div>Email: {{ cliente.email }}</div>
                </div>
            </div>
            <div class="mt-1">
                <Button type="button" label="Fechar" severity="danger" @click="handleModal"></Button>
                <Button class="ms-1" type="button" label="Salvar" v-on:click="updateProduto"></Button>
            </div>
        </Dialog>
    </div>
</template>

<script setup lang="ts">
import { Button, Dialog, Divider, InputText, InputNumber } from 'primevue';
import { ref, watch, type Ref } from 'vue';
import type Produto from '../../models/Produto';
import type Cliente from '../../models/Cliente';

interface Props {
    idProduto: number
    isVisible: boolean
    handleModal(): void
}


const props = defineProps<Props>()

const produto: Ref<Produto> = ref({
    idproduto: -1,
    marca: '',
    modelo: '',
    ano: 0,
    situacao: '',
    cliente_idcliente: 0,
})
const cliente: Ref<Cliente> = ref({
    cpf: '',
    email: '',
    idcliente: -1,
    nome: '',
    telefone: ''
})

watch(
    () => props.isVisible,
    async () => {
        let result = await window.electronAPI.findOneProduto(props.idProduto)

        if (result) {
            produto.value = result

            let clienteResult = await window.electronAPI.findOneCliente(result.cliente_idcliente)
            if (clienteResult) {
                cliente.value = clienteResult
            }
        }
    }
)

async function updateProduto() {
    const payload: Produto = {
        idproduto: produto.value.idproduto,
        marca: produto.value.marca,
        modelo: produto.value.modelo,
        ano: produto.value.ano,
        situacao: produto.value.situacao,
        cliente_idcliente: produto.value.cliente_idcliente
    }
    const result = await window.electronAPI.updateProduto(payload)
    if (result) {
        alert(result.msg)
    }
}
</script>