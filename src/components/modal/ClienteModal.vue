<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Cliente" :style="{ width: '25rem' }" :closable="false">
            <span class="text-success">Informações do cliente</span>
            <div class="flex items-center gap-4 mb-4">
                <div>
                    <label for="">Nome</label><br>
                    <InputText v-model="cliente.nome" type="text" />
                </div>
                <div>
                    <label for="">Email</label><br>
                    <InputText v-model="cliente.email" type="email" />
                </div>
                <div>
                    <label for="">Telefone</label><br>
                    <InputText v-model="cliente.telefone" type="tel" />
                </div>
                <div>
                    <label for="">CPF/CNPJ</label><br>
                    <InputText v-model="cliente.cpf" type="text" />
                </div>
            </div>
            <div class="flex justify-end gap-2">
                <Button type="button" label="Editar" v-on:click="updateCliente"></Button>
                <Button class="ms-1" type="button" label="Fechar" severity="danger" @click="handleModal"></Button>
            </div>
        </Dialog>
    </div>
</template>

<script setup lang="ts">
import { Button, Dialog, InputText } from 'primevue';
import { ref, watch, type Ref } from 'vue';
import type Cliente from '../../models/Cliente';

interface Props {
    idCliente: number
    isVisible: boolean
    handleModal(): void
}
const props = defineProps<Props>()

console.log(props)
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
    const result: Cliente = await window.electronAPI.findOneCliente(props.idCliente)
    
    if (result) {
        cliente.value = result
    }
})

async function updateCliente(){
    const payload = {
        idcliente: props.idCliente,
        nome: cliente.value.nome,
        email: cliente.value.email,
        telefone: cliente.value.telefone,
        cpf: cliente.value.cpf
    }
    const result = await window.electronAPI.updateCliente(payload)
    if(result){
        alert(result.msg)
    }
}



</script>