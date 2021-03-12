<template>
    <div>
      <div style="margin: 0.5rem 0.5rem;text-align: left;font-size: 1.5rem;"><i class="cubeic-back" @click="goLoginHome()"></i></div>
      <div style="margin: 2rem">
        <div style="text-align: left">
          <div style="font-size: 1.5rem;font-weight: bolder;color: #4f4f4f"> 账号密码登录 </div>
          <div style="font-size: 0.7rem;color: #bfbebe;margin-top: 0.5rem"> 使用你的测试服务账号登录 </div>
        </div>
        <div style="margin-top: 2rem;color: #bfbebe;">
          <input type="text" placeholder="请输入账号" class="inputClass" v-model="account.accountName"></input>
          <input type="password" placeholder="请输入密码" class="inputClass" v-model="account.password" ></input>
        </div>
        <div style="text-align: left;margin-top: 0.8rem;color: #bfbebe;font-size: 0.8rem;">忘记密码?</div>
        <div style="margin-top: 2rem;color: #FFFFFF;">
          <div @click="goLogin()" @keyup.enter="goLogin()" @touchstart="focusSubmitButton()" @touchend="focusSubmitButton()" class="inputClass2"
               :style="{background: is_click?'-webkit-linear-gradient(left, rgb(130 136 142), rgb(226 226 226))':'-webkit-linear-gradient(left,rgb(140, 197, 255),rgb(198, 226, 255))'}"
          >
            立即登录
          </div>
        </div>
      </div>
    </div>
</template>

<script>
  import {login} from "../api/login";
    export default {
        name: "Login",
      data(){
          return {
              submit:false,
              is_click:false,
              account:{
                accountName:"",
                password:"",
              },
          }
      },
      methods:{
        goLoginHome(){
          let $this = this;
          $this.$router.push({
            path:'/'
          })
        },
        goLogin(){
          let $this = this;
          if ($this.submit) return;
          $this.submit = true;
          let data = {
            password:$this.$md5($this.account.password),
            accountName:$this.account.accountName
          }
          login(data).then(res =>{
            $this.submit = false;
            //登录成功 进入首页
            $this.$createToast({
              txt: '登录成功!',
              type: 'right'
            }).show();
            setTimeout(function () {
              $this.$router.push({
                path:'/homePage'
              });
            },800);
          }).catch(error =>{
            //console.log(error.response.data.message);
            $this.submit = false;
            //登录失败
            $this.$createToast({
              txt: error.response.data.message,
              type: 'warn'
            }).show();
          })
        },
        focusSubmitButton(){
          let $this = this;
          if($this.submit)return;
          $this.is_click = !$this.is_click;
        }
      }
    }
</script>

<style scoped>
  .inputClass {
    width: 100%;
    -webkit-appearance: none;
    background-color: #fff;
    background-image: none;
    border-radius: 4px;
    border: 1px solid #dcdfe6;
    box-sizing: border-box;
    color: #606266;
    display: inline-block;
    font-size: inherit;
    height: 50px;
    line-height: 50px;
    outline: none;
    padding: 0 40px;
    border-radius: 33px;
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    margin-top: 1rem;
  }
  .inputClass2 {
    width: 100%;
    -webkit-appearance: none;
    color: #FFFFFF;letter-spacing: 0.2rem;
    border-radius: 4px;
    border: 1px solid #dcdfe6;
    box-sizing: border-box;
    color: #606266;
    display: inline-block;
    font-size: inherit;
    height: 50px;
    line-height: 50px;
    outline: none;
    padding: 0 40px;
    border-radius: 33px;
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    margin-top: 1rem;
  }
</style>
