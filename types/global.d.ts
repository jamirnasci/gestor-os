import type Ordem from "../src/models/Ordem"
import type Produto from "../src/models/Produto"

export {}

interface ElectronResult{
    success: boolean
    msg: string
}

declare global{
    interface Window{
        electronAPI:{
            hello: ()=> Promise<string>,
            createOrdem: (cliente, produto, ordem) => Promise<ElectronResult>,
            findAllClientes: () => Promise<[]>,
            findOneCliente: (idcliente: number) => Promise<any>,
            updateCliente: (cliente: any) => Promise<any>,
            findOneProduto: (idProduto: number) => Promise<any>,
            findAllProdutos: () => Promise<any>,
            updateProduto: (produto: Produto) => Promise<any>,
            findAllOrdens: ()=> Promise<any>,
            findOneOrdem: (idordem: number) => Promise<Ordem>,
            updateOrdem: (idordem, status) => Promise<any>,
            deleteOrdem: (idordem) => Promise<ElectronResult>
        }
    }
}
