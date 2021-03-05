<template>
    <div style=" height: 100%;">
      <el-container style="height: 100%;">
        <el-header>
          <div style="width: 30%;float: left;text-align: center">
            <div style="font-size: 20px;font-family: 微软雅黑;font-weight: bold;color: #FFF">
              测试服务平台
            </div>
          </div>
          <div class="headImg cursorPointer">
            <div >
              <i class="el-icon-bell"   style="vertical-align:middle;font-size: 25px;color: #FFF"></i>
              <el-badge is-dot class="item" style="position: relative;right: 20px;top: -5px"></el-badge>
            </div>
            <div style="margin-right: 20px">
              <el-dropdown trigger="click" style="height: 50px">
                <div >
                  <img style="width: 40px;height: 40px;border-radius: 50%;" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" alt="">
                </div>
                <el-dropdown-menu slot="dropdown" style="top: 50px">
                  <el-dropdown-item icon="el-icon-lock">修改密码</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-user">个人中心</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-switch-button">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </div>
        </el-header>
        <el-container>
          <el-aside width="200px" style="height: 100%" >
            <div class="outer-container">
              <div class="inner-container">
                <el-menu :default-active="menuList[0] ? menuList[0].url : ''" router class="el-menu-vertical-demo"
                         background-color="#545c64"
                         text-color="#fff"
                         active-text-color="#ffd04b"
                         style="width: 100%;border: none;" :collapse="isCollapse">

                  <div v-for="(menu,index) in menuList ">
                    <el-submenu v-if="menu.children && menu.children.length > 0" :key="index" :index="menu.url">
                      <template slot="title">
                        <i :class="menu.icon"></i>
                        <span slot="title">{{menu.name}}</span>
                      </template>
                      <el-menu-item  v-for="(menuChildren,indexChildren) in menu.children " :key="indexChildren" :index="menuChildren.url">
                        <template slot="title">
                          <i :class="menuChildren.icon"></i>
                          <span slot="title">{{menuChildren.name}}</span>
                        </template>
                      </el-menu-item>
                    </el-submenu>
                    <el-menu-item v-else :key="index" :index="menu.url">
                      <template slot="title">
                        <i :class="menu.icon"></i>
                        <span slot="title">{{menu.name}}</span>
                      </template>
                    </el-menu-item>
                  </div>
                </el-menu>
              </div>
            </div>
          </el-aside>
          <el-main >
            <router-view style="height: 100%"/>
          </el-main>
        </el-container>
      </el-container>
    </div>
</template>

<script>

   import {queryMenuList} from "../api/menu";

   export default {
     name: "HomePage",
     data(){
       return {
         menuList:[],
         isCollapse: false,
       }
     },
     created() {
       this.initHomePage();
     },
     methods: {
       initHomePage(){
         let $this = this;
         if ($this.$store.getters.getMenuList.length>0) {
           $this.menuList = $this.$store.getters.getMenuList;
         } else {
           //查询菜单集合
           queryMenuList().then(res =>{
             $this.menuList = res.data;
             $this.$store.commit('setMenuList',$this.menuList)
           }).catch(error =>{
             $this.$message({
               message: '菜单查询出错!',
               type: 'error'
             });
           })
         }
       },
     }
   }
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #545c64;
    color: #333;
    text-align: center;
    line-height: 60px;
    height: 60px;
  }

  .el-aside {
    color: #333;
    text-align: left;
    line-height: 200px;
    border: solid 1px #e6e6e6;
    background-color: #545c64;
    margin-left: -1px;
    height: 100%;
  }

  .el-main {
    color: #333;
    text-align: center;
    background: #f1eeef;
    height: 100%;
    overflow: hidden;
  }
  .el-container {
    height: 100%;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }

  .nowrap{
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  .grid-content {
    font-size: 20px;
    font-family: 微软雅黑;
    font-weight: bold;
    color: #FFF;
    vertical-align: top;
  }

  .headImg{
    text-align: right;
    width: 50%;
    float: right;
    height: 60px;
    font-size: 60px
  }
  .headImg >div{
    display: inline-block;
  }
</style>
