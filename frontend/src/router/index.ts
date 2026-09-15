import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProcedureView from '../views/ProcedureView.vue'
import ToolsView from '../views/ToolsView.vue'
import AdminView from '../views/AdminView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/home' },
    { path: '/home', name: 'home', component: HomeView },
    { path: '/procedure', name: 'procedure', component: ProcedureView },
    { path: '/tools', name: 'tools', component: ToolsView },
    { path: '/admin', name: 'admin', component: AdminView },
  ],
})

export default router
