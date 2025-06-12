<template>
    <div class="container">
        <h1>Ordens</h1>
        <div class="ordens-container">
            <OrdemCard v-for="o in ordens" :idordem="o.idordem" :handleModal="handleModal"
                :tipo="o.tipo" :dataEntrega="o.data_entrega" :status="o.status" :preco="o.preco"/>
        </div>
    </div>
    <OrdemModal :idordem="choosedOrdem" :isVisible="modalVisible" :handleModal="handleModal" />
</template>

<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import OrdemModal from '../components/modal/OrdemModal.vue';
import OrdemCard from '../components/OrdemCard.vue';
import type Ordem from '../models/Ordem';

const modalVisible: Ref<boolean> = ref(false)
const ordens = ref<Ordem[]>([])

const choosedOrdem: Ref<number | undefined> = ref()

onMounted(async () => {
    let result: Ordem[] = await window.electronAPI.findAllOrdens()

    if (result) {
        ordens.value = result
    }
})

async function handleModal(idordem: number | undefined) {
    if(idordem){
        choosedOrdem.value = idordem
    }
    modalVisible.value = !modalVisible.value
}
</script>

<style lang="css" scoped>
.ordens-container {
    overflow: auto;
    max-height: 500px;
}
</style>