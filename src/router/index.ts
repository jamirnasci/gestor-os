import { createRouter, createWebHistory, type Router } from "vue-router";
import NovaOrdem from "../views/NovaOrdemView.vue";
import OrdensView from "../views/OrdensView.vue";
import ClientesView from "../views/ClientesView.vue";
import ProdutosView from "../views/ProdutosView.vue";

const router: Router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path:'/',
            name:'home',
            component: NovaOrdem
        },
        {
            path:'/ordens',
            name:'ordens',
            component: OrdensView
        },
        {
            path:'/clientes',
            name:'cliente',
            component: ClientesView
        },
        {
            path: '/produtos',
            name: 'produtos',
            component: ProdutosView
        }
    ]
})

export default router