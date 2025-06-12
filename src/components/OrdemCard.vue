<template>
    <Card class="mt-1">
        <template #content>
            <div class="d-flex w-100 align-items-center justify-content-between">
                <div class="d-flex flex-column">
                      <h3> {{ props.tipo }} </h3> 
                    <span>Preço: R$ {{ parseFloat(props.preco).toFixed(2) }}</span>
                    <span>Data Entrega: {{ new Date(props.dataEntrega).toLocaleDateString('pt-BR') }}</span>
                </div>
                <div>
                    <strong class="m-1">{{ props.status }}</strong>
                </div>
                <Button label="Ver ordem" v-on:click="loadOrdemInfo" />
            </div>
        </template>
    </Card>

</template>

<script setup lang="ts">
import { Card, Button } from 'primevue';

interface Props {
    idordem: number
    tipo: string
    preco: string
    dataEntrega: string
    status: string
    handleModal(produto_idproduto: number): void
}

const props = defineProps<Props>()

async function loadOrdemInfo(){
    props.handleModal(props.idordem)
}

</script>