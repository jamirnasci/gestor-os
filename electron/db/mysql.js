const {createPool} = require('mysql2/promise')

const pool = createPool({
    host: 'localhost',
    port: 3306,
    database: 'gestor_os',
    user: 'root',
    password:'123456'
})

module.exports = {
    pool
}