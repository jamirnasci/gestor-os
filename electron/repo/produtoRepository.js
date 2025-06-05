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
    }finally{
        if(conn) conn.release()
    }

}

module.exports = {
    createProduto
}