<template>
    <div style=" height: 100%;">
      <el-container style="height: 100%;">
        <el-header>
          <div style="width: 30%;float: left;text-align: center">
            <div style="font-size: 20px;font-family: 微软雅黑;font-weight: bold;color: #FFF">
              测试服务平台
            </div>
          </div>
          <div class="headImg">
            <div>
              <i class="el-icon-edit"   style="vertical-align:middle;font-size: 20px;color: #FFF"></i>
              <i class="el-icon-share"  style="vertical-align:middle;font-size: 20px;color: #FFF"></i>
              <i class="el-icon-delete" style="vertical-align:middle;font-size: 20px;color: #FFF"></i>
            </div>
            <div>
              <img style="width: 40px;height: 40px;border-radius: 50%" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" alt="">
              <span class="grid-content">tom</span>
            </div>
            <div>
              <img src="../assets/imgs/closeSystem.png" style="width: 20px;margin-bottom:10px" alt="">
            </div>

          </div>
        </el-header>
        <el-container>
          <el-aside width="200px" style="height: 100%" >
            <div class="outer-container">
              <div class="inner-container">
                <el-menu :default-active="defaultActive" router class="el-menu-vertical-demo"
                         background-color="#545c64"
                         text-color="#fff"
                         active-text-color="#ffd04b"
                         style="width: 100%;border: none;" :collapse="isCollapse">
                  <el-menu-item v-for="(menu,index) in menuList " :key="index" :index="menu.url" >
                    <div class="nowrap">
                      <i :class="menu.icon"></i>
                      {{menu.name}}
                    </div>
                    <span slot="title">{{menu.name}}</span>
                  </el-menu-item>
                </el-menu>
              </div>
            </div>
          </el-aside>
          <el-main>
            <router-view />
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
         defaultActive:"",
         menuList:[],
         isCollapse: true,
       }
     },
     created() {
       this.initHomePage();
     },
     methods: {
       initHomePage(){
         let $this = this;
         //查询菜单集合
         queryMenuList().then(res =>{
           $this.menuList = res.data;
           if (res.data.length > 0) {
             $this.defaultActive = $this.menuList[0].url;
           }
         }).catch(error =>{
           $this.$message({
             message: '菜单查询出错!',
             type: 'error'
           });
         })
       }
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
    margin-left: 10px;
  }
</style>
