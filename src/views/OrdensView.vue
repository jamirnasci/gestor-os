<template>
    <div class="container">
        <h1>Ordens</h1>
        <div class="ordens-container">
            <OrdemCard :handleModal="handleModal" tipo="Reparo" dataEntrega="06/06/2025" status="Em Andamento" preco="400.50"/>
            <OrdemCard :handleModal="handleModal" tipo="Reparo" dataEntrega="06/06/2025" status="Em Andamento" preco="400.50"/>
        </div>
    </div>
    <OrdemModal :isVisible="modalVisible" :handleModal="handleModal"/>
</template>

<script setup lang="ts">
import { ref, type Ref } from 'vue';
import OrdemModal from '../components/modal/OrdemModal.vue';
import OrdemCard from '../components/OrdemCard.vue';

const modalVisible: Ref<boolean> = ref(false)

function handleModal(){
    modalVisible.value = !modalVisible.value
}
</script>

<style lang="css" scoped>
.ordens-container{
    overflow: auto;
    max-height: 500px;
}
</style>