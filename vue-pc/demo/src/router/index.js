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
      path: '/',
      name: 'HomePage',
      component:() => import('../view/user/HomePage.vue')
    }
  ]
})
