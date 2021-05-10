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
          name: 'MenuMain',
          component:() => import('../view/menu/MenuMain.vue')
        },
        {
          path: '/map/main',
          name: 'MapMain',
          component:() => import('../view/map/MapMain.vue')
        },
        {
          path: '/user/main',
          name: 'UserMain',
          component:() => import('../view/user/UserMain.vue')
        },
        {
          path: '/monitor/main',
          name: 'MonitorMain',
          component:() => import('../view/monitor/MonitorMain.vue')
        },
        {
          path: '/chatroom/main',
          name: 'ChatRoom',
          component:() => import('../view/chatroom/ChatRoom.vue')
        }
      ]
    },
  ]
})
