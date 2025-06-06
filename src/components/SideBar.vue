<template>
    <div class="side-bar-container">
        <div class="logo-box">
            <h1>Gestor OS</h1>
            <i>Gestor de Ordens de Serviço</i>
        </div>
        <div class="links-box">
            <a href="/">Nova ordem</a>
            <a href="/clientes">Clientes</a>
            <a href="/produtos">Produtos</a>
            <a href="/ordens">Ordens</a>
        </div>
    </div>
</template>

<style lang="css" scoped>
    .side-bar-container{
        height: 100vh;
        width: 250px;
        background-color: seagreen;
    }
    .logo-box{
        padding: 5px;
        margin-top: 10px;
    }
    .logo-box > h1{
        color: white;
    }
    .logo-box > i{
        color: lightgray;
    }
    .links-box{
        margin-top: 100px;
    }
    .links-box > a {
        display: block;
        color: whitesmoke;
        text-decoration: none;
        font-size: 22px;
        padding: 5px;
        transition: ease 0.2s;
    }
    .links-box > a:hover{
        background-color: mediumseagreen;
        font-size: 25px;
    }
</style>