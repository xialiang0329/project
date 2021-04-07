// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import md5 from 'js-md5';
import Cube from 'cube-ui'
import ElementUI from 'element-ui';
import './assets/css/common.css';
import 'element-ui/lib/theme-chalk/index.css';


import Vant from 'vant';
import 'vant/lib/index.css';
Vue.config.productionTip = false

Vue.prototype.$md5 = md5;
Vue.use(Cube)
Vue.use(ElementUI);
Vue.use(Vant);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

/*router.beforeEach((to, from, next) => {
  /!* 路由发生变化修改页面title *!/
  if (to.meta[0]) {
    document.title = to.meta[0]
  }

  if (to.name == 'Message') {
    document.title = to.meta[0] + "(132)";
  }
  next();
})*/
