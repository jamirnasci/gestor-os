const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld('electronAPI', {
    hello: () => ipcRenderer.invoke('hello'),
    createOrdem: (cliente, produto, ordem) => ipcRenderer.invoke('create-ordem', cliente, produto, ordem)
})