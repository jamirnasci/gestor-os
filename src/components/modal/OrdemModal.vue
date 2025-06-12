<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Informações da ordem" :closable="false">
            <div class="d-flex">
                <div class="flex items-center gap-4 mb-4">
                    <span class="text-success">Cliente</span>
                    <div>Cliente: {{ cliente?.nome }}</div>
                    <div>CPF/CNPJ: {{ cliente?.cpf }}</div>
                    <div>Telefone: {{ cliente?.telefone }}</div>
                    <div>Email: {{ cliente?.email }}</div>
                </div>
                <Divider layout="vertical" />
                <div class="flex items-center gap-4 mb-8">
                    <span class="text-success">Produto</span>
                    <div>Marca: {{ produto?.marca }}</div>
                    <div>Modelo: {{ produto?.modelo }}</div>
                    <div>Ano: {{ produto?.ano }}</div>
                    <div>Situação: {{ produto?.situacao }}</div>
                </div>
                <Divider layout="vertical" />
                <div class="flex items-center gap-4 mb-8">
                    <span class="text-success">Ordem de serviço</span>
                    <div>Tipo: {{ ordem?.tipo }}</div>
                    <div>Data Entrega: {{ ordem?.data_entrega }}</div>
                    <div>Status: {{ ordem?.status }}</div>
                    <div>Preço: R$ {{ ordem?.preco }}</div>
                </div>
            </div>
            <div class="ms-1 w-25">
                <label for="status">Atualizar Status</label><br>
                <Select id="status" v-model="status" :options="statusOptions" optionLabel="name" placeholder="Status"
                    :maxSelectedLabels="3" class="w-full md:w-80" fluid />
            </div>
            <div class="mt-1">
                <Button type="button" label="Excluir Ordem" severity="danger" v-on:click="excluirOrdem" />
                <Button class="ms-1" type="button" label="Fechar" severity="warn"
                    v-on:click="handleModal(undefined)"></Button>
                <Button class="ms-1" type="button" label="Editar" v-on:click="updateOrdem"></Button>
            </div>
        </Dialog>
    </div>
</template>

<script setup lang="ts">
import { Button, Dialog, Divider, Select } from 'primevue';
import type Ordem from '../../models/Ordem';
import { ref, watch, type Ref } from 'vue';
import type Produto from '../../models/Produto';
import type Cliente from '../../models/Cliente';

interface Props {
    isVisible: boolean
    idordem: number | undefined
    handleModal(produto_idproduto: number | undefined): void
}
const status: Ref<string> = ref('')
const statusOptions: Ref = ref([
    { name: 'Aberta' },
    { name: 'Em andamento' },
    { name: 'Concluída' },
    { name: 'Cancelada' }
])
//
const props = defineProps<Props>()

const produto: Ref<Produto | undefined> = ref()
const ordem: Ref<Ordem | undefined> = ref()
const cliente: Ref<Cliente | undefined> = ref()

async function updateOrdem(){
    const result = await window.electronAPI.updateOrdem(props.idordem, (status.value as any).name)
    if(result){
        alert(result.msg)
    }
}

watch(
    () => props.isVisible,
    async () => {
        if (props.idordem) {
            const ordemResult: Ordem = await window.electronAPI.findOneOrdem(props.idordem)
            const produtoResult: Produto = await window.electronAPI.findOneProduto(ordemResult.produto_idproduto)
            const clienteResult: Cliente = await window.electronAPI.findOneCliente(produtoResult.cliente_idcliente)

            produto.value = produtoResult
            ordem.value = ordemResult
            ordem.value.data_entrega = new Date(ordemResult.data_entrega).toLocaleDateString("pt-BR")
            cliente.value = clienteResult

        }
    }
)

async function excluirOrdem(){
    if(props.idordem){
        const result = await window.electronAPI.deleteOrdem(props.idordem)
        if(result.success){
            alert(result.msg)
        }
    }
}


</script>