import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './components/App.vue'
import UserList from './components/UserList.vue'

const routes = [
    { path: '/user/:username/gameslist/:filter', component: UserList },
    { path: '/user/:username/gameslist', component: UserList },
];

const router = createRouter({
    history: createWebHistory(),
    routes: routes,
});

createApp(App)
    .use(router)
    .mount('#app');
