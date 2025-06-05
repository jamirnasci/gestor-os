<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Cliente" :style="{ width: '25rem' }" :closable="false">
            <span class="text-success">Informações do cliente</span>
            <div class="flex items-center gap-4 mb-4">
                <div>
                    <label for="">Nome</label><br>
                    <InputText type="text" />
                </div>
                <div>
                    <label for="">Email</label><br>
                    <InputText type="email" />
                </div>
                <div>
                    <label for="">Telefone</label><br>
                    <InputText type="tel" />
                </div>
                <div>
                    <label for="">CPF/CNPJ</label><br>
                    <InputText type="text" />
                </div>
            </div>
            <div class="flex justify-end gap-2">
                <Button type="button" label="Editar" @click="handleModal"></Button>
                <Button class="ms-1" type="button" label="Fechar" severity="danger" @click="handleModal"></Button>
            </div>
        </Dialog>
    </div>
</template>

<script setup lang="ts">
import { Button, Dialog, InputText } from 'primevue';

interface Props {
    isVisible: boolean
    handleModal(): void
}

const props = defineProps<Props>()

</script>