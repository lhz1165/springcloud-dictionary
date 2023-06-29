import Vue from 'vue'
import VueRouter from 'vue-router'
import login from "@/view/Login";
import  index from "@/view/Index"

Vue.use(VueRouter)



const routes = [
    {
        path: '/logout',
        name: 'logout',
        redirect:'/login'
    },

    {
        path: '/',
        name: 'default',
        redirect:'/index'
    },

    {
        path: '/login',
        name: 'login',
        component: login
    },

    {
        path: '/index',
        name: 'main',
        component: index,
        // redirect:"/index/welcome",
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})



export default router
