<template>
    <div style="background-color: #f5f6f7">
      <div class="topBack" >
        <div style="width: 100%;height: 80%;padding-top: 2rem">
          <div class="circel_outside">
            <div class="circel_within">
              <img  src="../../assets/logo.jpg" style="width: 5rem;height: 5rem;border-radius:50%">
            </div>
          </div>
          <div style="padding:1rem">某某某</div>
        </div>
       </div>
      <div class="infoBack">
        <div style="padding-top: 1.5rem">
          <div style="padding: 5px">
            <van-cell  style="border-bottom: 1px #ececec solid">
                <van-row style="display: flex;align-items: center;margin: 0 auto;">
                  <van-col span="4" style="display: flex;"><img src="../../assets/imgs/card.png" alt=""></van-col>
                  <van-col span="10"><div data-v-1807e2f6="" class="van-cell__title"><span data-v-1807e2f6="">个人信息</span></div></van-col>
                  <van-col span="10"><div data-v-1807e2f6="" class="van-cell__value"><i class="van-icon van-icon-arrow van-cell__right-icon"></i></div></van-col>
                </van-row>
            </van-cell>
          </div>
          <div style="padding: 5px">
            <van-cell  style="border-bottom: 1px #ececec solid">
              <van-row>
                <van-col span="4"><i  class="van-icon van-icon-setting-o van-cell__left-icon"><!----></i></van-col>
                <van-col span="10"><div data-v-1807e2f6="" class="van-cell__title"><span data-v-1807e2f6="">通知信息</span></div></van-col>
                <van-col span="10"><div data-v-1807e2f6="" class="van-cell__value"><i class="van-icon van-icon-arrow van-cell__right-icon"></i></div></van-col>
              </van-row>
            </van-cell>
          </div>
          <div style="padding: 5px" >
            <van-cell style="border-bottom: 1px #ececec solid">
              <van-row>
                <van-col span="4"><i  class="van-icon van-icon-setting-o van-cell__left-icon"><!----></i></van-col>
                <van-col span="10"><div data-v-1807e2f6="" class="van-cell__title"><span data-v-1807e2f6="">修改密码</span></div></van-col>
                <van-col span="10"><div data-v-1807e2f6="" class="van-cell__value"><i class="van-icon van-icon-arrow van-cell__right-icon"></i></div></van-col>
              </van-row>
            </van-cell>
          </div>
          <div style="padding: 5px" >
            <van-cell  style="border-bottom: 1px #ececec solid">
              <van-row>
                <van-col span="4"><i  class="van-icon van-icon-setting-o van-cell__left-icon"><!----></i></van-col>
                <van-col span="10"><div data-v-1807e2f6="" class="van-cell__title"><span data-v-1807e2f6="">设置</span></div></van-col>
                <van-col span="10"><div data-v-1807e2f6="" class="van-cell__value"><i class="van-icon van-icon-arrow van-cell__right-icon"></i></div></van-col>
              </van-row>
            </van-cell>
          </div>
          <div style="padding: 5px" >
            <van-cell style="border-bottom: 1px #ececec solid">
              <van-row>
                <van-col span="4"><i  class="van-icon van-icon-setting-o van-cell__left-icon"><!----></i></van-col>
                <van-col span="10"><div data-v-1807e2f6="" class="van-cell__title"><span data-v-1807e2f6="">注销登录</span></div></van-col>
                <van-col span="10"><div data-v-1807e2f6="" class="van-cell__value"><i class="van-icon van-icon-arrow van-cell__right-icon"></i></div></van-col>
              </van-row>
            </van-cell>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "Person",
      data(){
          return {
            showShare: false,

          }
      },
      methods: {
        onSelect(option) {
          this.$toast(option.name);
          this.showShare = false;
        },
      },
    }
</script>

<style scoped>
  .topBack {
    height: 40%;
    background-color: rgb(66, 185, 131);
    border-radius: 3%;
    margin-top: -1rem;
  }

  .infoBack{
    width: 92%;
    height: 65%;
    background-color: #ffffff;
    border-radius: 0.6rem;
    margin-left: 4%;
    margin-top: -1.5rem;
  }

 .card{
   background-image: url("../../assets/imgs/card.png");
 }

</style>
