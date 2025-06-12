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

async function findAllOrdens() {
    const sql = `SELECT * FROM ordem`
    let conn

    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        const [result] = await stmt.execute()

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

async function findOneOrdem(idordem) {
    const sql = "SELECT * FROM ordem WHERE idordem = ?"
    let conn

    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        const [result] = await stmt.execute([idordem])
        
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

async function updateOrdem(idordem, status){
    const sql = 'UPDATE ordem SET status = ? WHERE idordem = ?'
    let conn

    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        const [result] = await stmt.execute([status, idordem])
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

async function deleteOrdem(idordem){
    const sql = 'DELETE FROM ordem WHERE idordem = ?'
    let conn

    try {
        conn = await pool.getConnection()
        const stmt = await conn.prepare(sql)
        const [result] = await stmt.execute([idordem])
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
    createOrdem,
    findAllOrdens,
    findOneOrdem,
    updateOrdem,
    deleteOrdem
}