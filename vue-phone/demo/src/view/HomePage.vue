<!--
    首页
-->

<template>
  <div >
    <router-view  style="height: 92%;overflow-y: auto"/>
    <div style="position: absolute;bottom: 0;width: 100%;border-top: 1px #dcd7d7 solid;background-color: #f9f9f9;height: 50px">
      <cube-tab-bar
        v-model="selectedLabelDefault"
        @click="clickHandler"
        style="height: 50px"
        >
          <cube-tab v-for="(item, index) in tabs" :label="item.label" :key="item.label" style="margin-top: 5px">
              <!-- name为icon的插槽 -->
              <i slot="icon" :class="item.icon" style="font-size: 20px"></i>
              <br>
              <!-- 默认插槽 -->
              {{item.label}}
          </cube-tab>
      </cube-tab-bar>
    </div>

  </div>
</template>

<script>

    export default {
      name: "HomePage",
      watch: {
        '$route.path': function (newVal, oldVal) {
          let selectedLabelDefault = "首页";
          if (newVal === '/homePage/message') {
            selectedLabelDefault = '信息';
          }else if (newVal === '/homePage/tag') {
            selectedLabelDefault = '主题';
          }else if (newVal === '/homePage/person') {
            selectedLabelDefault = '我的';
          }
          this.$nextTick(function () {
            this.selectedLabelDefault = selectedLabelDefault;
          });
        }
      },
      data(){
          return {
              wxJSSDK:{
                appid:'',
                secret:'',
                accessToken:'',
                ticket:'',
             },
            selectedLabelDefault: '首页',
            tabs: [{
              label: '首页',
              icon: 'cubeic-home',
            }, {
              label: '信息',
              icon: 'cubeic-message',
            }, {
              label: '主题',
              icon: 'cubeic-tag',
            }, {
              label: '我的',
              icon: 'cubeic-person',
            }],
            urlList:{
                '首页':'/homePage/home',
                '信息':'/homePage/message',
                '主题':'/homePage/tag',
                '我的':'/homePage/person',
            }
          }
      },
      created() {
        let $this = this;
          //获取当前路径
        let curWwwPath=window.document.location.href;
        let indexOf = curWwwPath.indexOf("?");
        let route = curWwwPath.substring(curWwwPath.indexOf("#/") + 1,indexOf == -1 ? curWwwPath.length: indexOf);
          let selectedLabelDefault = "首页";
          if (route === '/homePage/message') {
            selectedLabelDefault = '信息';
          }else if (route === '/homePage/tag') {
            selectedLabelDefault = '主题';
          }else if (route === '/homePage/person') {
            selectedLabelDefault = '我的';
          }
        this.selectedLabelDefault = selectedLabelDefault;

        //先获取config
        /*wxJSSDKConifg().then(response =>{
          let data = response.data;
          console.log(data)
          wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: data.appId, // 必填，公众号的唯一标识
            timestamp: data.timestamp, // 必填，生成签名的时间戳    <%= Html.Encode(ViewData["timestamp" ]) %>
            nonceStr: data.nonceStr, // 必填，生成签名的随机串
            signature:data.signature, // 必填，签名
            jsApiList: ['scanQRCode'] // 必填，需要使用的JS接口列表, 这里只需要调用扫一扫
          });
        });*/
      },
      methods:{
        scanQR(){
          var ua = navigator.userAgent.toLowerCase();
          var isWeixin = ua.indexOf('micromessenger') !== -1;
          if (!isWeixin)
          {
            alert('请用微信打开');
          }
          wx.scanQRCode({
            needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
            scanType: ["qrCode"], // 可以指定扫二维码还是一维码，默认二者都有
            success: function (res) {
              var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
              console.log(result)

            },
            error: function (res) {
              if (res.errMsg.indexOf('function_not_exist') > 0) {
                alert('当前版本过低，请进行升级');
              }
            }
          });
        },
        clickHandler (label) {
          let $this = this;
          $this.$router.push({
            path:$this.urlList[label],
          });
        },
      }
    }
</script>

<style scoped>
  .cube-tab-bar {
    background-color: rgba(236, 236, 236, 0.5);
    font-size: 12px;
  }
</style>
