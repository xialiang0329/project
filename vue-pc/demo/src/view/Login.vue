<!--  登录/注册  -->

<template>
      <div class="home">
          <div class="login_border">
            <div style="position: absolute;right: 0;" @click="isQrCodeChange()">
              <img v-show="!isQrCode" src="../assets/imgs/qrCode.png"  style="width: 60px;height: 60px" class="cursorPointer" alt="">
              <img v-show="isQrCode" src="../assets/imgs/pc.png"  style="width: 60px;height: 60px" class="cursorPointer" alt="">
            </div>
            <div style="height: 100px;line-height: 100px">
              <h2> ***测试服务平台 </h2>
            </div>
            <div v-show="!isQrCode" >
              <div style="color:#409eff;margin-bottom: 20px " class="cursorPointer">
                <span @click="loginOrAdd = !loginOrAdd" :class="!loginOrAdd?'fontSize25':'fontSize20'">登录</span>
                <span class="fontSize25">/</span>
                <span @click="loginOrAdd = !loginOrAdd" :class="loginOrAdd?'fontSize25':'fontSize20'">注册</span>
              </div>
              <div v-show="!loginOrAdd">
                <div class="inputDiv">
                  <i class="el-icon-user"></i>
                  <input  type="text" placeholder="请输入账号" class="inputClass" v-model="input1"></input>
                </div>
                <div class="inputDiv">
                  <i class="el-icon-lock"></i>
                  <input type="password" placeholder="请输入密码" class="inputClass" v-model="input2"></input>
                </div>
                <div class="inputDiv">
                  <i class="el-icon-picture-outline"></i>
                  <input type="text" placeholder="请输入验证码" class="inputClass" v-model="input2"></input>
                </div>
                <div>
                  <div class="row">
                    <el-checkbox v-model="checked">记住我</el-checkbox>
                  </div>
                  <div class="row">
                    <i class="el-icon-mobile "><el-link type="primary">短信登录</el-link></i>
                  </div>
                </div>
                <el-button type="primary" round style="width: 65%;font-size: 18px;margin-bottom: 3%">点 击 登 录</el-button>
                <div style="width:49%;float:right;">
                  <el-link type="primary">忘记密码？</el-link>
                </div>
              </div>
              <div v-show="loginOrAdd">
                <div class="inputDiv">
                  <i class="el-icon-user"></i>
                  <input  type="text" placeholder="请输入账号" class="inputClass" v-model="input1"></input>
                </div>
                <div class="inputDiv">
                  <i class="el-icon-lock"></i>
                  <input type="password" placeholder="请输入密码" class="inputClass" v-model="input2"></input>
                </div>
                <div class="inputDiv">
                  <i class="el-icon-lock"></i>
                  <input type="password" placeholder="确认密码" class="inputClass" v-model="input2"></input>
                </div>
                <el-button type="primary" round style="width: 65%;font-size: 18px;margin-bottom: 3%">点 击 注 册</el-button>
              </div>
            </div>
            <div v-show="isQrCode" id="login_container"></div>
          </div>
      </div>
</template>

<script>
    export default {
        name: "Login",
      data (){
          return {
            isQrCode:false,//登录方式
            loginOrAdd:false,//登录还是添加
            input1:"",
            input2:"",
            checked:false
          }
      },
      created() {

      },
      mounted() {
      },
      methods:{
        isQrCodeChange:function () {
              let $this = this;
              $this.isQrCode = !$this.isQrCode;
              if ($this.isQrCode) {
                $this.wwLogin();
              }
        },
        wwLogin:function () {
          window.WwLogin({
            "id" : "login_container",
            "appid" : "ww87cb2dc52371604e",//appid
            "agentid" : "1000004",//agentid
            "redirect_uri" :"http://bcead4d3609e.ngrok.io/loginUser/getQrCode",//回调地址，注意回调地址需要进行urlencode
            "state" : "x",//用于保持请求和回调的状态，授权请求后原样带回给企业。该参数可用于防止csrf攻击,参数非必填
            "href" : "",//自定义样式链接，企业可根据实际需求覆盖默认样式，参数非必填
          });
        }
      }
    }
</script>

<style scoped>
    .home {
      background-image: url("./../assets/imgs/pc_background_home.jpg");
      background-repeat:no-repeat ;
      background-size: cover;
      height: 100%;
      position: relative;
    }

  .login_border {
    min-width: 450px;
    min-height: 500px;
    border: 1px #ababab solid;
    position: absolute;
    top: 15%;
    left: 50%;
    filter:progid:DXImageTransform.Microsoft.Shadow(color=#DCD7D1,direction=120,strength=4);/*ie*/
    -moz-box-shadow: 40px 40px 10px rgba(220, 215, 209, .48);/*firefox*/
    -webkit-box-shadow: 40px 40px 10px  rgba(220, 215, 209, .48);/*safari或chrome*/
    box-shadow:40px 40px 10px  rgba(220, 215, 209, .48);/*opera或ie9*/
    border-radius: 5px;
    background: #FFF;
  }

    .inputClass {
      width: 65%;
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
    }

    .inputClass:hover{
      outline: none;
      border-color: #409eff;
    }

  .inputDiv{
    position: relative;
    margin-bottom: 10px;
  }
  .inputDiv i{
    position: absolute;
    left: 20%;
    font-size: 20px;
    line-height: 50px;
  }

  .row {
    margin-top: 20px;
    margin-bottom: 20px;
    text-align: center;
    width: 49%;
    display: inline-block
  }



</style>
