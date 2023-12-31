import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import css from './assets/css/cssStyle.css';

import md5 from 'js-md5';
import store from './store/store.js';



Vue.use(ElementUI);
Vue.use(css);

Vue.config.productionTip = false;
Vue.prototype.$md5 = md5;
Vue.prototype.$store = store;
Vue.prototype.$socketio = io;//socketio

new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
