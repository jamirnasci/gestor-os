<template>
    <div class="container">
        <h1>Ordens</h1>
        <div class="ordens-container">
            <OrdemCard tipo="Reparo" dataEntrega="06/06/2025" status="Em Andamento" preco="400.50"/>
        </div>
    </div>
</template>

<script setup lang="ts">
import OrdemCard from '../components/OrdemCard.vue';

    
</script>

<style lang="css" scoped>
.ordens-container{
    overflow: auto;
    max-height: 500px;
}
</style>