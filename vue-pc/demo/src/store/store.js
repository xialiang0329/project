import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
let store = new Vuex.Store({

  //初始数据
  state:{
    menuList:[]
  },

  //计算属性
  getters:{
    getMenuList(state){
      return state.menuList;
    }
  },
  //存放异步方法
  actions:{
    setMenuList({commit,state},menuList) {
      commit("setMenuList_m", menuList);
    }
  },
  //同步方法
  mutations:{
    setMenuList_m(state,menuList){
      state.menuList = menuList;
    }
  }
});

export default store;
