<template>
    <div style="background-color: #f5f6f7">
      <!--<router-view style="height: 100%"/>-->
      <!--  绿色背景  -->
      <div class="topBack"></div>
      <!-- 主页视频 -->
      <div class="videoBack"></div>
      <div style="padding: 0.6rem 0rem 0rem 1rem;text-align: left;position: relative;">
        <img src="../../assets/imgs/express_news.png" style="position: absolute;" alt="">
        <span style="margin-left: 4rem;font-size: 13px;color: #666;">  测试服务项目  </span>
      </div>

      <!-- 通知 -->
      <div>
        <img src="../../assets/imgs/green_back.png" style="width: 92%;height: 10%;" alt="">
      </div>

      <!-- 最新功能菜单  前三 -->
      <div v-for="(img, index) in 3" style="display: inline-block;width: 25%;height: auto;padding: 0.6rem">
        <img src="../../assets/imgs/green_little_back.png" style="display:inline-block;width: 100%;height: 25%;" :key="img">
      </div>
    </div>
</template>

<script>
    export default {
        name: "Home",
    }
</script>

<style scoped>

  .topBack {
    height: 20%;
    background-color: rgb(66, 185, 131);
    border-radius: 3%;
    margin-top: -2rem;
  }

  .videoBack {
    width: 92%;
    height: 20%;
    background-color: #ffefef;
    border-radius: 0.6rem;
    margin-left: 4%;
    margin-top: -2rem;

  }

</style>
