import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
export default new Router({
  routes: [
    {
      path: '/',
      name: 'LoginHome',
      component:() => import('../view/LoginHome.vue')
    },
    {
      path: '/login',
      name: 'Login',
      component:() => import('../view/Login.vue')
    },
    {
      path: '/homePage',
      name: 'HomePage',
      component:() => import('../view/HomePage.vue'),
      redirect:'/homePage/home',
      children:[
        {
          path: '/homePage/home',
          name: 'Home',
          component:() => import('../view/homePage/Home.vue'),
          meta: ["首页"],
        },
        {
          path: '/homePage/message',
          name: 'Message',
          component:() => import('../view/homePage/Message.vue'),
          meta: ["信息"],
        },
        {
          path: '/homePage/tag',
          name: 'Tag',
          component:() => import('../view/homePage/Tag.vue'),
          meta: ["主题"],
        },
        {
          path: '/homePage/person',
          name: 'Person',
          component:() => import('../view/homePage/Person.vue'),
          meta: ["我的"],
        },
      ]
    },
  ]
})
