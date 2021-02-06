import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component:() => import('../view/Login.vue')
    },
    {
      path: '/homePage',
      name: 'HomePage',
      component:() => import('../view/HomePage.vue'),
      children:[
        {
          path: '/menu/main',
          name: 'Main',
          component:() => import('../view/menu/Main.vue')
        }
      ]
    },
  ]
})
