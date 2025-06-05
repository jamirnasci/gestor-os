export {}

declare global{
    interface Window{
        electronAPI:{
            hello: ()=> Promise<string>,
            createOrdem: (cliente, produto, ordem) => Promise<{success: boolean, msg: string}>,
            findAllClientes: () => Promise<[]>,
            findOneCliente: (idcliente: number) => Promise<any>,
            updateCliente: (cliente: any) => Promise<any>
        }
    }
}
