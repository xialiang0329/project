import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
const store = new Vuex.Store({

  //初始数据
  state:{
    menuList:[]
  },

  //计算属性
  getters:{
    getMenuList(){
      return store.state.menuList;
    }
  },
  //存放异步方法
  actions:{
    setMenuList(state,menuList) {
      state.commit("setMenuList",menuList);
    },
  },
  //同步方法
  mutations:{
    setMenuList(state, menuList){
      state.menuList = menuList;
    }
  }
});

export default store;
