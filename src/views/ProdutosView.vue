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
                <Column field="nome" header="Proprietário"></Column>
                <Column header="Ação">
                    <!-- slot body com slotProps para acessar os dados da linha -->
                    <template #body="slotProps">
                        <Button severity="success" label="Abrir" @click="abrirProduto(slotProps.data)" />
                    </template>
                </Column>
            </DataTable>
        </div>
    </div>
    <ProdutoModal :idProduto="idProduto" :isVisible="modalVisible" :handleModal="handleModal"/>
</template>
<script setup lang="ts">
import { Button, Column, DataTable, InputText } from 'primevue';
import { onMounted, ref, type Ref } from 'vue';
import ProdutoModal from '../components/modal/ProdutoModal.vue';

const produtos = ref([])
const modalVisible: Ref<boolean> = ref(false)
const idProduto: Ref<number> = ref(-1)

onMounted(async ()=>{
    const result = await window.electronAPI.findAllProdutos()
    if(result){
        produtos.value = result[0]
    }else{
        alert('Nenhum produto encontrado')
    }
    
})

function abrirProduto(data: any) {
    idProduto.value = data.idproduto
    handleModal()
    
}
function handleModal(){
    modalVisible.value = !modalVisible.value
}
</script>