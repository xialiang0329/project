<template>
    <div style="position: relative">
      <div class="bmap" id="bmap"></div>
      <!--<div class="amap" id="amap"></div>-->
      <div class="drawer" id="box"></div>
      <el-button @click="openBox()" type="primary" style="margin-left: 16px;">
        点我打开
      </el-button>
      <el-button @click="closeBox()" type="primary" style="margin-left: 16px;">
        点我关闭
      </el-button>
    </div>
</template>

<script>
  import BMap from "BMap";
  import AMap from "AMap";
    export default {
        name: "MapMain",
        data (){
            return {
              drawer: false,
              direction: 'rtl',
            }
        },
      created() {
        let $this = this;
        this.$nextTick(() => {
          // 在此处执行你要执行的函数
          $this.mapInit();
        });
      },
      methods:{
        mapInit(){
          let bmap = new BMap.Map("bmap", {enableMapClick:false});
          bmap.centerAndZoom('南京市', 12);/*初始化地图,设置中心点坐标和地图级别*/
          bmap.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放

         /*
          var amap = new AMap.Map('amap', {
            zoom:11,//级别
            center: [116.397428, 39.90923],//中心点坐标
            resizeEnable: true,
          });
          //实时路况图层
          var trafficLayer = new AMap.TileLayer.Traffic({
            zIndex: 10
          });
          amap.add(trafficLayer);//添加图层到地图*/
        },
        openBox() {
          $("#box").animate({width:"29%"},1200);
        },
        closeBox() {
          $("#box").animate({width:0},1200);
        }
      }
    }
</script>

<style scoped>
  .bmap {
    overflow: hidden;
    width: 70%;
    height: 50%;
    margin: 0;
    font-family: "微软雅黑";
  }
  .amap {
    overflow: hidden;
    width: 70%;
    height: 50%;
    margin: 0;
    font-family: "微软雅黑";
  }

  .drawer {
    position: absolute;
    width: 0;
    background-color: #ffffff;
    right: -20px;
    top: -20px;
    bottom: 0;
    z-index: 999;
    height: 100%;
  }
</style>
