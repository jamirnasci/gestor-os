<template>
    <div class="card flex justify-center">
        <Dialog v-model:visible="props.isVisible" modal header="Cliente" :style="{ width: '25rem' }" :closable="false">
            <span class="text-success">Informações do cliente</span>
            <div class="flex items-center gap-4 mb-4">
                <div>
                    <label for="">Nome</label><br>
                    <InputText v-model="nome" type="text" />
                </div>
                <div>
                    <label for="">Email</label><br>
                    <InputText v-model="email" type="email" />
                </div>
                <div>
                    <label for="">Telefone</label><br>
                    <InputText v-model="telefone" type="tel" />
                </div>
                <div>
                    <label for="">CPF/CNPJ</label><br>
                    <InputText v-model="cpf" type="text" />
                </div>
            </div>
            <div class="flex justify-end gap-2">
                <Button type="button" label="Editar" v-on:click="updateCliente"></Button>
                <Button class="ms-1" type="button" label="Fechar" severity="danger" @click="handleModal"></Button>
            </div>
        </Dialog>
    </div>
</template>

<script setup lang="ts">
import { Button, Dialog, InputText } from 'primevue';
import { ref, watch, type Ref } from 'vue';

interface Props {
    idCliente: number
    isVisible: boolean
    handleModal(): void
}
const props = defineProps<Props>()

console.log(props)
const nome: Ref<string> = ref('')
const cpf: Ref<string> = ref('')
const email: Ref<string> = ref('')
const telefone: Ref<string> = ref('')

watch(
    () => props.isVisible,
    async () => {
    const result = await window.electronAPI.findOneCliente(props.idCliente)
    
    if (result) {
        nome.value = result.nome
        email.value = result.email
        telefone.value = result.telefone
        cpf.value = result.cpf
    }
})

async function updateCliente(){
    const result = await window.electronAPI.updateCliente({
        idcliente: props.idCliente,
        nome: nome.value,
        email: email.value,
        telefone: telefone.value,
        cpf: cpf.value
    })
    if(result){
        alert(result.msg)
    }
}



</script>