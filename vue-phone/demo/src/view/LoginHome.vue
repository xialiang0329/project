<!--

    登录页

-->


<template>
    <div>

      <!--  项目图标  -->
      <div style="text-align: center">
          <img src="../assets/logo.jpg" style="width: 6rem;height: 6rem;border-radius: 0.8rem;margin-top: 2rem">
          <div class="title">测试服务平台</div>

        <!-- 微信登录 -->
          <div></div>
        <!-- 账号密码登录 -->
          <div>
            <div class="inputClass" @click="goLogin()">
              <i class="cubeic-person"></i>
              账号登录
            </div>

            <div class="inputClass2" style="position: absolute">
              <img src="../assets/imgs/wechatImg.png" class="weChatImg" style="top: 9px;">
              微信登录
            </div>
          </div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "LoginHome",
      data(){
          return {

          }
      },
      methods:{
        goLogin(){
          let $this = this;
          $this.$router.push({
            path:'/login'
          })
        }
      }
    }
</script>

<style scoped>

  .title {
    font-family: 微软雅黑;
    text-align: center;
    font-size: 1rem;
    margin-top: 0.2rem;
    font-weight: bold;
    letter-spacing: 0.1rem;
    color: #2d2c2c;
  }

  .inputClass {
    width: 70%;
    -webkit-appearance: none;
    background: -webkit-linear-gradient(left,rgb(140, 197, 255),rgb(198, 226, 255));
    border-radius: 4px;
    border: 1px solid #dcdfe6;
    box-sizing: border-box;
    color: #FFFFFF;
    display: inline-block;
    font-size: inherit;
    height: 50px;
    line-height: 50px;
    outline: none;
    padding: 0 40px;
    border-radius: 33px;
    text-align: center;
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    position: absolute;
    left:0;
    right:0;
    top:0;
    bottom:0;
    margin:auto;
  }
  .inputClass2 {
    width: 70%;
    -webkit-appearance: none;
    background: #FFFFFF;
    border-radius: 4px;
    border: 1px solid #dcdfe6;
    box-sizing: border-box;
    color: #666c66;
    display: inline-block;
    font-size: inherit;
    height: 50px;
    line-height: 50px;
    outline: none;
    padding: 0 40px;
    border-radius: 33px;
    text-align: center;
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    position: absolute;
    left:0;
    right:0;
    top:10rem;
    bottom:0;
    margin:auto;
  }

  .weChatImg {
    width: 32px;
    height: 32px;
    position: relative;
  }
</style>
