const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld('electronAPI', {
    hello: () => ipcRenderer.invoke('hello'),
    createOrdem: (cliente, produto, ordem) => ipcRenderer.invoke('create-ordem', cliente, produto, ordem),
    findAllClientes: () => ipcRenderer.invoke('find-all-clientes'),
    findOneCliente: (idcliente) => ipcRenderer.invoke('find-one-cliente', idcliente),
    updateCliente: (cliente) => ipcRenderer.invoke('update-cliente', cliente)
})