<template>
    <div class="container">
        <h1>Produtos</h1>
        <div class="card">
            <DataTable :value="produtos" tableStyle="min-width: 50rem">
                <Column field="marca" header="Marca"></Column>
                <Column field="ano" header="Ano"></Column>
                <Column field="situacao" header="Situação"></Column>
                <Column field="modelo" header="Modelo"></Column>
                <Column header="Ação">
                    <!-- slot body com slotProps para acessar os dados da linha -->
                    <template #body="slotProps">
                        <Button severity="success" label="Abrir" @click="abrirProduto(slotProps.data)" />
                    </template>
                </Column>
            </DataTable>
        </div>
    </div>
</template>
<script setup lang="ts">
import { Button, Column, DataTable } from 'primevue';
import { ref } from 'vue';

const produtos = ref([
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7' },
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7' },
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7' },
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7' },
    { marca: 'Motorola', ano: '2020', situacao: 'Display rachado', modelo: 'e7' },
])

function abrirProduto(data: string){
    console.log(data)
}
</script>