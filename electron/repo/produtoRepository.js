const { pool } = require("../db/mysql")

async function createProduto(produto, idcliente) {
    const sql = 'INSERT INTO produto(marca, ano, data_entrada, modelo, situacao, cliente_idcliente) VALUES(?,?,?,?,?,?)'
    let conn
    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        produto.data_entrada = new Date(produto.data_entrada)
        const [result] = await stmt.execute([produto.marca, produto.ano, produto.data_entrada, produto.modelo, produto.situacao, idcliente])
        if (result.affectedRows > 0) {
            return {
                success: true,
                produtoId: result.insertId
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

async function findAllProdutos() {
    const sql = 'SELECT c.nome, p.* FROM produto p INNER JOIN cliente c ON c.idcliente = p.cliente_idcliente'
    let conn
    try {
        conn = await pool.getConnection()
        const result = await conn.execute(sql)
        
        if (result.length > 0) {
            return {
                success: true,
                values: result
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

async function findOneProduto(idProduto) {
    const sql = "SELECT * FROM produto WHERE idproduto = ?"
    let conn

    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        const [result] = await stmt.execute([idProduto])
        
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

async function updateProduto(produto) {
    const sql = `UPDATE produto SET
        marca = ?,
        ano = ?,
        situacao = ?,
        modelo = ?
    WHERE idproduto = ?
    `
    let conn
    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        const [result] = await stmt.execute([produto.marca, produto.ano, produto.situacao, produto.modelo, produto.idproduto])

        if (result.affectedRows > 0) {
            return {
                success: true
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
    createProduto,
    findAllProdutos,
    findOneProduto,
    updateProduto
}