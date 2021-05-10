import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
const store = new Vuex.Store({

  //初始数据
  state:{
    menuList:[],
    websocket:null
  },

  //计算属性
  getters:{
    getMenuList(){
      return store.state.menuList;
    },
    getWebsocket(){
      return store.state.websocket;
    }
  },
  //存放异步方法
  actions:{
    setMenuList(state,menuList) {
      state.commit("setMenuList",menuList);
    },
    setWebsocket(state,websocket) {
      state.commit("setWebsocket",websocket);
    },
  },
  //同步方法
  mutations:{
    setMenuList(state, menuList){
      state.menuList = menuList;
    },
    setWebsocket(state, websocket){
      state.websocket = websocket;
    },
  }
});

export default store;
