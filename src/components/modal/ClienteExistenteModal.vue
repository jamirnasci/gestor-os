<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Buscar cliente" :style="{ width: '25rem' }" :closable="false">
            <span class="text-surface-500 dark:text-surface-400 block mb-8"></span>
            <div class="flex items-center gap-4 mb-4">
                <label for="username" class="font-semibold w-24">Nome</label><br>
                <InputText id="username" class="flex-auto" autocomplete="off" fluid/>
            </div>
            <Button label="Procurar"/>
            <Button class="ms-1" type="button" label="Cancel" severity="danger" @click="handleModal"></Button>
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