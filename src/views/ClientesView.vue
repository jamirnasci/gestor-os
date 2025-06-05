<template>
    <div class="container">
        <h1>Clientes</h1>
        <div class="card">
            <div class="d-flex align-items-end p-2">
                <div class="w-75">
                    <label for="">Nome</label><br>
                    <InputText fluid/>
                </div>
                <Button label="Procurar"/>
            </div>
            <DataTable :value="clientes" tableStyle="min-width: 50rem">
                <Column field="nome" header="Nome"></Column>
                <Column field="cpf" header="CPF/CNPJ"></Column>
                <Column field="telefone" header="Telefone"></Column>
                <Column field="email" header="Email"></Column>
                <Column header="Ação">
                    <!-- slot body com slotProps para acessar os dados da linha -->
                    <template #body="slotProps">
                        <Button severity="info" label="Abrir" @click="abrirCliente(slotProps.data)" />
                    </template>
                </Column>
            </DataTable>
        </div>
    </div>
    <ClienteModal 
        :idCliente="idCliente"
        :isVisible="modalVisible" 
        :handleModal="handleModal"
    />
</template>
<script setup lang="ts">
import { Button, Column, DataTable, InputText } from 'primevue';
import { onMounted, ref, type Ref } from 'vue';
import ClienteModal from '../components/modal/ClienteModal.vue';

const clientes = ref([])
const idCliente = ref(-1)

onMounted(async ()=>{
    const result = await window.electronAPI.findAllClientes()
    if(result){
        clientes.value = result
    }
})

const modalVisible: Ref<boolean> = ref(false)
async function abrirCliente(data: any){
    idCliente.value = data.idcliente
    handleModal()
}

function handleModal(){
    modalVisible.value = !modalVisible.value
}
</script>