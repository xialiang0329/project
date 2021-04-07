<template>
    <div style="background-color: #eaeaea;">
      <div style="background-color: #eaeaea;position: fixed;width: 100%;;z-index: 999">
        <div style="position: relative">
          <div style="padding: 1rem;font-size: 18px;font-weight: bold">信息（132）</div>
          <div style="position: absolute;right: 1rem;top: 0.8rem;text-align: left">
            <van-popover
              placement="bottom-end"
              v-model="showPopover"
              theme="dark"
              trigger="click"
              :actions="actions"
              @select="onSelect"
              class="abcd"
            >
              <template #reference>
                <img src="../../assets/imgs/circular_add.png" style="width: 28px;height: 28px" alt="">
              </template>
            </van-popover>
          </div>
        </div>
      </div>
      <div style="height: 3rem;margin-top: 3rem">
        <!-- search -->
        <div style="width: 98%;margin-left: 1%;position: relative">
          <van-icon style="position: absolute;top: 35%;font-size: 20px;left: 38%;" name="search"></van-icon>
          <input type="text" style="padding: 0.5rem;margin-top:5px;border-radius: 0.4rem;width: 95%;text-align: center"  placeholder="搜索" v-model="search">
        </div>
      </div>
      <!-- list -->
      <div style="margin-top: 1px;border-top: 1px #e4e1e1 solid;overflow-y: auto;background-color: #fff;">
        <div v-for="data in messageList" >
          <van-row>
            <van-col span="4" style="height: 4rem;padding: 7px;">
              <!-- 图标 -->
              <img src="../../assets/logo.jpg"  style="width: 60px;height: 60px;border-radius: 5px" alt="">
            </van-col>
            <van-col span="20" style="border-bottom: 1px #e4e1e1 solid;">
              <div style="height: 60px;padding: 7px">
                <div style="height: 50px;padding: 5px">
                  <div>
                    <van-row>
                      <van-col span="16"  style="text-align: left;margin-left: 5px;font-size: 18px;color: black;height: 25px;line-height: 25px; white-space: nowrap;overflow: hidden;text-overflow: ellipsis; ">
                        <!-- 消息体名称 -->
                        消息体名称消息体名称消息体名称消息体名称消息体名称
                      </van-col>
                      <van-col span="7" style="font-size: 14px;color: #d3d3d3;height: 25px;line-height: 25px">
                        <!-- 时间 -->
                        2020/05/07
                      </van-col>
                    </van-row>
                  </div>
                  <!-- 最后一条消息 -->
                  <div>
                    <van-row>
                      <van-col span="22" style="text-align: left;font-size: 12px;height: 25px;line-height: 25px;padding: 5px;color:#ababab; white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
                        <!-- 消息体名称 -->
                        最后一条消息最后一条消息最后一条消息最后一条消息最后一条消息最后一条消息最后一条消息最后一条消息
                      </van-col>
                      <van-col span="1" style="color: #d3d3d3;text-align: left">
                        <!-- 图标 -->
                        <van-icon style="font-size: 14px;padding: 5px" name="search"></van-icon>
                      </van-col>
                    </van-row>
                  </div>
                </div>
              </div>
            </van-col>
          </van-row>
        </div>
        <img src="../../assets/imgs/empty_message.png" v-if="messageList.length == 0" style="margin-top: 50%" alt="">
      </div>
    </div>
</template>

<script>
    export default {
        name: "Message",
      data (){
          return {
            search:"",
            showPopover: false,
            // 通过 actions 属性来定义菜单选项
            actions: [{ text: '发起群聊',icon:'chat-o'}, { text: '添加朋友', icon: 'user-circle-o' }, { text: '扫一扫' ,icon:'scan'}],
            //messageList:[1,2,3,4,5,6,7,8,9]
            messageList:50
          }
      },
      created() {
      },
      mounted() {
      },
      methods:{
        onSelect(action) {
          this.$toast(action.text);
        },
      }
    }
</script>

<style scoped>

  .abcd {
    text-align: left;
  }
</style>
