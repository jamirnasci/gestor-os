<template>
    <div class="container">
        <h1>Clientes</h1>
        <div class="card">
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
</template>
<script setup lang="ts">
import { Button, Column, DataTable } from 'primevue';
import { ref } from 'vue';

const clientes = ref([
    { nome: 'Jamir', cpf: '12313212333', telefone: '91983733456', email: 'jamir@gmail.com' },
    { nome: 'Jamir', cpf: '12313212333', telefone: '91983733456', email: 'jamir@gmail.com' },
    { nome: 'Jamir', cpf: '12313212333', telefone: '91983733456', email: 'jamir@gmail.com' },
    { nome: 'Jamir', cpf: '12313212333', telefone: '91983733456', email: 'jamir@gmail.com' },
    { nome: 'Jamir', cpf: '12313212333', telefone: '91983733456', email: 'jamir@gmail.com' },
])

function abrirCliente(data: string){
    console.log(data)
}
</script>