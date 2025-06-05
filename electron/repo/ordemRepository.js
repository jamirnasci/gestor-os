const { pool } = require("../db/mysql")

async function createOrdem(ordem, idproduto) {
    const sql = 'INSERT INTO ordem(tipo, preco, data_entrega, status, descricao, produto_idproduto) VALUES(?,?,?,?,?,?)'
    let conn
    console.log(ordem, idproduto)
    try {
        conn = await pool.getConnection()
        const clienteStmt = await conn.prepare(sql)
        ordem.data_entrega = new Date(ordem.data_entrega)
        const [result] = await clienteStmt.execute([ordem.tipo, ordem.preco, ordem.data_entrega, ordem.status, ordem.descricao, idproduto])
        
        if(result.affectedRows > 0){
            return {
                success: true
            }
        }
    } catch (error) {
        return {
            success: false,
            msg: error.message
        }
    } finally{
        if(conn) conn.release()
    }

}

module.exports = {
    createOrdem
}