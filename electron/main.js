const { BrowserWindow, ipcMain } = require("electron");
const { app } = require("electron/main");
const path = require("path");
const { createOrdem, findAllOrdens, findOneOrdem, updateOrdem, deleteOrdem } = require("./repo/ordemRepository");
const { createCliente, findAllClientes, findOneCliente, updateCliente } = require("./repo/clienteRepository");
const { createProduto, findAllProdutos, findOneProduto, updateProduto } = require("./repo/produtoRepository");

const preloadPath = path.join(__dirname, 'preload.js')

const createWindow = () => {

    const w = new BrowserWindow({
        width: 1000,
        height: 800,
        webPreferences: {
            preload: preloadPath
        },
        autoHideMenuBar: true
    })
    w.loadURL('http://localhost:5173/')
}


app.whenReady().then(() => {
    createWindow()
    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            createWindow()
        }
    })
    ipcMain.handle('hello', async (ev) => {
        return 'hello'
    })
    ipcMain.handle('create-ordem', async (ev, cliente, produto, ordem) => {
        const clientResult = await createCliente(cliente)
        if (clientResult.success) {
            const produtoResult = await createProduto(produto, clientResult.clienteId)
            if (produtoResult.success) {
                const ordemResult = await createOrdem(ordem, produtoResult.produtoId)
                if (ordemResult.success) {
                    return {
                        success: true,
                        msg: 'Ordem criada com sucesso'
                    }
                }
                console.log(ordemResult.msg)
                return {
                    success: false,
                    msg: 'Falha ao criar ordem'
                }
            } else {
                console.log(produtoResult.msg)
                return {
                    success: false,
                    msg: 'Falha ao cadastrar produto'
                }
            }
        }
        console.log(clientResult.msg)
        return {
            success: false,
            msg: 'Falha ao cadastrar cliente'
        }

    })
    ipcMain.handle('find-all-clientes', async (ev) => {
        const result = await findAllClientes()
        if (result.success) {
            if (result.values.length > 0) {
                return result.values
            }
        }
        return null
    })
    ipcMain.handle('find-one-cliente', async (ev, idcliente) => {
        const result = await findOneCliente(idcliente)
        if (result.success) {
            return result.values[0]
        }
        console.log(result.msg)
        return null
    })
    ipcMain.handle('update-cliente', async (ev, cliente) => {
        const result = await updateCliente(cliente)
        console.log(result.msg)
        if (result.success) {
            return {
                msg: 'Cliente atualizado'
            }
        }
        return {
            msg: 'Falha ao atualizar cliente'
        }
    })
    ipcMain.handle('find-all-produtos', async (ev) => {
        const result = await findAllProdutos()

        if (result.success) {
            return result.values
        }
        console.log(result.msg)
        return null
    })
    ipcMain.handle('find-one-produto', async (ev, idproduto) => {
        const result = await findOneProduto(idproduto)
        if (result.success) {
            return result.values[0]
        }
        console.log(result.msg)
        return null
    })
    ipcMain.handle('update-produto', async (ev, produto) => {
        const result = await updateProduto(produto)
        console.log(result.msg)
        if (result.success) {
            return {
                msg: 'Produto atualizado'
            }
        }
        return {
            msg: 'Falha ao atualizar produto'
        }
    })
    ipcMain.handle('find-all-ordens', async ()=>{
        const result = await findAllOrdens()
        if(result.success){
            return result.values
        }
        console.log(result.msg)
        return null
    })
    ipcMain.handle('find-one-ordem', async (ev, idordem)=>{
        const result = await findOneOrdem(idordem)
        if(result.success){
            return result.values[0]
        }
        console.log(result.msg)
        return null
    })
    ipcMain.handle('update-ordem', async (ev, idordem, status)=>{
        const result = await updateOrdem(idordem, status)
        if(result.success){
            return {
                msg: 'Ordem atualizada !'
            }
        }
        console.log(result.msg)
        return {
            msg: 'Falha ao atualizar ordem'
        }
    })
    ipcMain.handle('delete-ordem', async (ev, idordem)=>{
        const result = await deleteOrdem(idordem)
        if(result.success){
            return {
                msg: result.msg
            }
        }
        console.log(result.msg)
        return {
            msg: 'Falha ao excluir ordem'
        }
    })
})
app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit()
    }
})