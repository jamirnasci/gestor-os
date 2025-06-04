<template>
    <div class="container">
        <h1>Produtos</h1>
        <div class="card">
            <div class="d-flex align-items-end p-2">
                <div class="w-75">
                    <label for="">Nome</label><br>
                    <InputText fluid />
                </div>
                <Button label="Procurar" />
            </div>
            <DataTable :value="produtos" tableStyle="min-width: 50rem">
                <Column field="marca" header="Marca"></Column>
                <Column field="ano" header="Ano"></Column>
                <Column field="situacao" header="Situação"></Column>
                <Column field="modelo" header="Modelo"></Column>
                <Column field="proprietario" header="Proprietário"></Column>
                <Column header="Ação">
                    <!-- slot body com slotProps para acessar os dados da linha -->
                    <template #body="slotProps">
                        <Button severity="success" label="Abrir" @click="abrirProduto(slotProps.data)" />
                    </template>
                </Column>
            </DataTable>
        </div>
    </div>
    <ProdutoModal :isVisible="modalVisible" :handleModal="handleModal"/>
</template>
<script setup lang="ts">
import { Button, Column, DataTable, InputText } from 'primevue';
import { ref, type Ref } from 'vue';
import ProdutoModal from '../components/modal/ProdutoModal.vue';

const produtos = ref([
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7', proprietario: 'Jamir' },
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7', proprietario: 'Jamir' },
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7', proprietario: 'Jamir' },
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7', proprietario: 'Jamir' },
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7', proprietario: 'Jamir' },
])
const modalVisible: Ref<boolean> = ref(false)

function abrirProduto(data: string) {
    handleModal()
    console.log(data)
}
function handleModal(){
    modalVisible.value = !modalVisible.value
}
</script>