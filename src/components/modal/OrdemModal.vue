<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Informações da ordem"
            :closable="false">
            <div class="d-flex">
                <div class="flex items-center gap-4 mb-4">
                    <span class="text-success">Cliente</span>
                    <div>Cliente: Jamir</div>
                    <div>CPF/CNPJ: 12312312300</div>
                    <div>Telefone: 9183293212</div>
                    <div>Email: jamir@gmail.com</div>
                </div>
                <Divider layout="vertical" />
                <div class="flex items-center gap-4 mb-8">
                    <span class="text-success">Produto</span>
                    <div>Marca: Motorola</div>
                    <div>Modelo: e7</div>
                    <div>Ano: 2020</div>
                    <div>Situação: Display quebrado</div>
                </div>
                <Divider layout="vertical" />
                <div class="flex items-center gap-4 mb-8">
                    <span class="text-success">Ordem de serviço</span>
                    <div>Tipo: Reparo</div>
                    <div>Data Entrega: 01/01/2026</div>
                    <div>Status: Em Andamento</div>
                    <div>Preço: R$ 400</div>
                </div>
            </div>
            <div class="flex justify-end gap-2">
                <Button type="button" label="Cancelar Ordem" severity="danger"/>
                <Button class="ms-1" type="button" label="Fechar" severity="warn" @click="handleModal"></Button>
                <Button class="ms-1" type="button" label="Editar" @click="handleModal"></Button>
            </div>
        </Dialog>
    </div>
</template>

<script setup lang="ts">
import { Button, Dialog, Divider } from 'primevue';

interface Props {
    isVisible: boolean
    handleModal(): void
}

const props = defineProps<Props>()

</script>