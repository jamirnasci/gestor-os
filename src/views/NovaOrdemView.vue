<template>
    <div class="container">
        <h1>Nova Ordem</h1>
        <form action="">
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
                    <Button style="margin-top: 5px;" label="Cliente Existente" />
                </div>
                <Divider layout="vertical" />
                <div class="ms-1">
                    <h3 class="form-subtitle">Produto</h3>
                    <div class="d-flex">
                        <div>
                            <label for="">Marca</label><br>
                            <InputText />
                        </div>
                        <div class="ms-1">
                            <label for="">Ano</label><br>
                            <InputNumber input-id="withoutgrouping" :useGrouping="false" />
                        </div>
                    </div>
                    <div class="d-flex">
                        <div>
                            <label for="">Situação</label><br>
                            <InputText />
                        </div>
                        <div class="ms-1">
                            <label for="">Modelo</label><br>
                            <InputText />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-box">
                <h3 class="form-subtitle">Serviço</h3>
                <div class="d-flex w-100">
                    <div class="w-25">
                        <label for="servico">Tipo</label><br>
                        <Select id="servico" v-model="selectedServico" :options="servicos" optionLabel="name"
                            placeholder="Selecione o serviço" :maxSelectedLabels="3" class="w-full md:w-80" fluid />
                    </div>
                    <div class="ms-1 w-25">
                        <label for="preco">Preço</label><br>
                        <InputNumber v-model="selectedPreco" id="preco" inputId="minmaxfraction" :minFractionDigits="2"
                            :maxFractionDigits="2" fluid></InputNumber>
                    </div>
                </div>
                <div class="d-flex w-100">
                    <div class="w-25">
                        <label for="data_entrega">Data entrega</label><br>
                        <DatePicker v-model="selectedDataEntrega" fluid />
                    </div>
                    <div class="ms-1 w-25">
                        <label for="status">Status</label><br>
                        <Select id="status" v-model="selectedStatus" :options="status" optionLabel="name"
                            placeholder="Status" :maxSelectedLabels="3" class="w-full md:w-80" fluid />
                    </div>
                </div>
                <label for="descricao">Descrição</label><br>
                <Textarea class="w-50">

                </Textarea>
            </div>
        </form>
    </div>
</template>

<script setup lang="ts">
import { Select, InputNumber, DatePicker, InputText, Button, Textarea, Divider } from 'primevue';
import { ref, type Ref } from 'vue';

const selectedServico: Ref<string> = ref('')
const selectedDataEntrega = ref()
const selectedPreco: Ref<number> = ref(0.00)
const selectedStatus: Ref<string> = ref('')

const clienteNome: Ref<string> = ref('')
const clienteCpf: Ref<string> = ref('')
const clienteTelefone: Ref<string> = ref('')
const clienteEmail: Ref<string> = ref('')

const servicos: Ref = ref([
    { name: 'Reparo' },
    { name: 'Troca de peça' },
    { name: 'Limpeza' }
])

const status: Ref = ref([
    { name: 'Aberta' },
    { name: 'Em andamento' },
    { name: 'Concluída' },
    { name: 'Cancelada' }
])

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