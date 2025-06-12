const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld('electronAPI', {
    hello: () => ipcRenderer.invoke('hello'),
    createOrdem: (cliente, produto, ordem) => ipcRenderer.invoke('create-ordem', cliente, produto, ordem),
    findAllClientes: () => ipcRenderer.invoke('find-all-clientes'),
    findOneCliente: (idcliente) => ipcRenderer.invoke('find-one-cliente', idcliente),
    updateCliente: (cliente) => ipcRenderer.invoke('update-cliente', cliente),
    findAllProdutos: () => ipcRenderer.invoke('find-all-produtos'),
    findOneProduto: (idProduto) => ipcRenderer.invoke('find-one-produto', idProduto),
    updateProduto: (produto) => ipcRenderer.invoke('update-produto', produto),
    findAllOrdens: () => ipcRenderer.invoke('find-all-ordens'),
    findOneOrdem: (idordem) => ipcRenderer.invoke('find-one-ordem', idordem),
    updateOrdem: (idordem, status) => ipcRenderer.invoke('update-ordem', idordem, status),
    deleteOrdem: (idordem) => ipcRenderer.invoke('delete-ordem', idordem)
})