<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Edit Profile"
            :closable="false">
            <div class="d-flex">
                <div>
                    <span class="text-success">Produto</span>
                    <div>Marca: Motorola</div>
                    <div>Modelo: e7</div>
                    <div>Ano: 2020</div>
                    <div>Situação: Display quebrado</div>
                </div>
                <Divider layout="vertical"/>
                <div>
                    <span class="text-success">Cliente</span>
                    <div>Cliente: Jamir</div>
                    <div>CPF/CNPJ: 12312312300</div>
                    <div>Telefone: 9183293212</div>
                    <div>Email: jamir@gmail.com</div>
                </div>
            </div>
            <div class="mt-1">
                <Button type="button" label="Fechar" severity="danger" @click="handleModal"></Button>
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