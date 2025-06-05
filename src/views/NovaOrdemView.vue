<template>
    <div class="container">
        <h2>Nova Ordem</h2>
        <form v-on:submit="criarOrdem" action="">
            <div class="form-box d-flex">
                <div>
                    <h3 class="form-subtitle">Cliente</h3>
                    <div class="d-flex">
                        <div>
                            <label for="clienteNome">Nome</label><br>
                            <InputText v-model="clienteNome" />
                        </div>
                        <div class="ms-1">
                            <label for="clienteCpf">CPF/CNPJ</label><br>
                            <InputText v-model="clienteCpf" />
                        </div>
                    </div>
                    <div class="d-flex">
                        <div>
                            <label for="clienteTelefone">Telfone</label><br>
                            <InputText v-model="clienteTelefone" />
                        </div>
                        <div class="ms-1">
                            <label for="clienteEmail">E-mail</label><br>
                            <InputText type="email" v-model="clienteEmail" />
                        </div>
                    </div>
                    <Button v-on:click="handleModal" style="margin-top: 5px;" label="Cliente Existente" />
                </div>
                <Divider layout="vertical" />
                <div class="ms-1">
                    <h3 class="form-subtitle">Produto</h3>
                    <div class="d-flex">
                        <div>
                            <label for="">Marca</label><br>
                            <InputText v-model="produtoMarca" />
                        </div>
                        <div class="ms-1">
                            <label for="">Ano</label><br>
                            <InputNumber v-model="produtoAno" input-id="withoutgrouping" :useGrouping="false" />
                        </div>
                    </div>
                    <div class="d-flex">
                        <div>
                            <label for="">Situação</label><br>
                            <InputText v-model="produtoSituacao" />
                        </div>
                        <div class="ms-1">
                            <label for="">Modelo</label><br>
                            <InputText v-model="produtoModelo" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-box">
                <h3 class="form-subtitle">Serviço</h3>
                <div class="d-flex w-100">
                    <div class="w-25">
                        <label for="servico">Tipo</label><br>
                        <Select id="servico" v-model="servico" :options="servicos" optionLabel="name"
                            placeholder="Selecione o serviço" :maxSelectedLabels="3" class="w-full md:w-80" fluid />
                    </div>
                    <div class="ms-1 w-25">
                        <label for="preco">Preço</label><br>
                        <InputNumber v-model="preco" id="preco" inputId="minmaxfraction" :minFractionDigits="2"
                            :maxFractionDigits="2" fluid></InputNumber>
                    </div>
                </div>
                <div class="d-flex w-100">
                    <div class="w-25">
                        <label for="data_entrega">Data entrega</label><br>
                        <DatePicker v-model="dataEntrega" fluid />
                    </div>
                    <div class="ms-1 w-25">
                        <label for="status">Status</label><br>
                        <Select id="status" v-model="status" :options="statusOptions" optionLabel="name"
                            placeholder="Status" :maxSelectedLabels="3" class="w-full md:w-80" fluid />
                    </div>
                </div>
                <label for="descricao">Descrição</label><br>
                <Textarea class="w-50" v-model="descricao">

                </Textarea><br>
                <Button type="submit" severity="success" label="Criar Ordem" />
            </div>
        </form>
    </div>
    <ClienteModal :handleModal="handleModal" :is-visible="modalVisible" />
    <div class="card flex justify-center">
        <Toast position="top-right" />
    </div>
</template>

<script setup lang="ts">
import { Select, InputNumber, DatePicker, InputText, Button, Textarea, Divider, type ToastServiceMethods, Toast } from 'primevue';
import { useToast } from 'primevue/usetoast'
import { ref, type Ref } from 'vue';
import ClienteModal from '../components/modal/ClienteExistenteModal.vue';

import '../../types/global.d.ts'

const clienteNome: Ref<string> = ref('')
const clienteCpf: Ref<string> = ref('')
const clienteTelefone: Ref<string> = ref('')
const clienteEmail: Ref<string> = ref('')

const servico: Ref<string> = ref('')
const dataEntrega = ref()
const preco: Ref<number> = ref(0.00)
const status: Ref<string> = ref('')
const descricao: Ref<string> = ref('')

const produtoMarca: Ref<string> = ref('')
const produtoAno: Ref<number> = ref(0)
const produtoModelo: Ref<string> = ref('')
const produtoSituacao: Ref<string> = ref('')

const modalVisible: Ref<boolean> = ref(false)

const toast: ToastServiceMethods = useToast()

const servicos: Ref = ref([
    { name: 'Reparo' },
    { name: 'Troca de peça' },
    { name: 'Limpeza' }
])

const statusOptions: Ref = ref([
    { name: 'Aberta' },
    { name: 'Em andamento' },
    { name: 'Concluída' },
    { name: 'Cancelada' }
])

function handleModal() {
    modalVisible.value = modalVisible.value ? false : true
}

async function criarOrdem(e: any) {
    e.preventDefault()
    const today = new Date().toISOString()
    const cliente = {
        nome: clienteNome.value,
        cpf: clienteCpf.value,
        email: clienteEmail.value,
        data_entrada: today,
        telefone: clienteTelefone.value,
    }
    const produto = {
        modelo: produtoModelo.value,
        marca: produtoMarca.value,
        data_entrada: today,
        ano: produtoAno.value,
        situacao: produtoSituacao.value
    }
    const ordem = {
        tipo: (servico.value as any).name,
        preco: preco.value,
        data_entrega: new Date(dataEntrega.value).toISOString(),
        status: (status.value as any).name,
        descricao: descricao.value
    }
    console.log(cliente, produto, ordem)
    const result = await window.electronAPI.createOrdem(cliente, produto, ordem)
    if (result.success) {
        toast.add({
            severity: 'success',
            summary: 'Info',
            detail: result.msg,
            life: 3000
        })
    } else {
        toast.add({
            severity: 'error',
            summary: 'Error',
            detail: result.msg,
            life: 3000
        })
    }

}

</script>

<style lang="css" scoped>
.form-box {
    background-color: whitesmoke;
    border-radius: 10px;
    box-shadow: 0px 0px 5px gray;
    padding: 10px;
    margin: 5px;
}

.form-subtitle {
    color: seagreen;
}
</style>