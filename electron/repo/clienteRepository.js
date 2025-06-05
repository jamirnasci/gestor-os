const { pool } = require("../db/mysql")

async function createCliente(cliente) {
    const sql = 'INSERT INTO cliente(nome, cpf, email, data_entrada, telefone) VALUES (?,?,?,?,?)'
    let conn
    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        cliente.data_entrada = new Date(cliente.data_entrada)
        const [result] = await stmt.execute([cliente.nome, cliente.cpf, cliente.email, cliente.data_entrada, cliente.telefone])
        if (result.affectedRows > 0) {
            return {
                success: true,
                clienteId: result.insertId
            }
        }
    } catch (error) {
        return {
            success: false,
            msg: error.message
        }
    } finally {
        if (conn) conn.release()
    }

}
async function findAllClientes() {
    const sql = "SELECT * FROM cliente"
    let conn

    try {
        conn = await pool.getConnection()
        const [result] = await conn.execute(sql)

        return {
            success: true,
            values: result
        }

    } catch (error) {
        return {
            success: false,
            msg: error.message
        }
    } finally {
        if (conn) conn.release()
    }
}

async function findOneCliente(idcliente) {
    const sql = "SELECT * FROM cliente WHERE idcliente = ?"
    let conn

    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        const [result] = await stmt.execute([idcliente])
        
        return {
            success: true,
            values: result
        }

    } catch (error) {
        return {
            success: false,
            msg: error.message
        }
    } finally {
        if (conn) conn.release()
    }
}

async function updateCliente(cliente) {
    const sql = `UPDATE cliente SET
        nome = ?,
        cpf = ?,
        email = ?,
        telefone = ?
    WHERE idcliente = ?
    `
    let conn
    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        cliente.data_entrada = new Date(cliente.data_entrada)
        const [result] = await stmt.execute([cliente.nome, cliente.cpf, cliente.email, cliente.telefone, cliente.idcliente])
        if (result.affectedRows > 0) {
            return {
                success: true,
                clienteId: result.insertId
            }
        }
    } catch (error) {
        return {
            success: false,
            msg: error.message
        }
    } finally {
        if (conn) conn.release()
    }

}

module.exports = {
    createCliente,
    findAllClientes,
    findOneCliente,
    updateCliente
}