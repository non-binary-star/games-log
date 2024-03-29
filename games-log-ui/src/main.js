import {createApp} from 'vue'
import {createRouter, createWebHistory} from 'vue-router'
import App from './components/App.vue'
import UserList from './components/UserList.vue'
import NotFound from './components/NotFound.vue'

const routes = [
    {path: '/user/:username/gameslist/:filter', name: "userlistfilter", component: UserList},
    {path: '/user/:username/gameslist', name: "userlist", component: UserList},
    {path: '/:anyPath(.*)*', name: 'notfound', component: NotFound}
];

const router = createRouter({
    history: createWebHistory(),
    routes: routes,
});

createApp(App)
    .use(router)
    .mount('#app');
