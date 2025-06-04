<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Cliente" :style="{ width: '25rem' }" :closable="false">
            <span class="text-success">Informações do cliente</span>
            <div class="flex items-center gap-4 mb-4">
                <div>Nome: Jamir</div>
                <div>Email: jamir@gmail.com</div>
                <div>Telefone: 91983738322</div>
                <div>CPF: 12312312300</div>
            </div>
            <div class="flex justify-end gap-2">
                <Button type="button" label="Editar" @click="handleModal"></Button>
                <Button class="ms-1" type="button" label="Fechar" severity="danger" @click="handleModal"></Button>
            </div>
        </Dialog>
    </div>
</template>

<script setup lang="ts">
import { Button, Dialog } from 'primevue';

interface Props {
    isVisible: boolean
    handleModal(): void
}

const props = defineProps<Props>()

</script>