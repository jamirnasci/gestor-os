<template>
    <Card>
        <template #content>
            <div class="d-flex w-100 align-items-center justify-content-between">
                <div class="d-flex flex-column">
                      <h3> {{ props.tipo }} </h3> 
                    <span>Preço: {{ props.preco }}</span>
                    <span>Data Entrega: {{ props.dataEntrega }}</span>
                </div>
                <div>
                    <strong class="m-1">{{ props.status }}</strong>
                </div>
                <Button label="Ver ordem" />
            </div>
        </template>
    </Card>

</template>

<script setup lang="ts">
import { Card, Button } from 'primevue';

interface Props {
    tipo: string
    preco: string
    dataEntrega: string
    status: string
}

const props = defineProps<Props>()

</script>