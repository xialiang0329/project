<!--

  聊天室


-->

<template>
  <div>
    <el-row :gutter="20" style="height: 100%">
      <el-col :span="6" style="height: 95%">
        <div style="background-color: #FFF;padding: 2px;">
          <!-- 精确查找 -->
          <el-input placeholder="输入关键字进行过滤" v-model="condition.filterText"></el-input>
        </div>
        <div class="outer-container " style="background-color: #FFF;">
          <div class="inner-container">
            <el-tabs v-model="condition.type" stretch>
              <el-tab-pane label="消息" name="message">
                消息列表
              </el-tab-pane>
              <el-tab-pane label="联系人" name="contacts">
                <div v-for="contact in data.contactsList" style="height: 80px;" @click="socketToContact(contact)">
                  <el-row :gutter="20" style="margin-left: 0;margin-right: 0" >
                    <el-col :span="5">
                      <!-- 头像 是否在线 -->
                      <img style="width: 50px; height: 50px;border-radius:50%;margin-top: 5px"
                           :src="contact.photo" :class="contact.account.status == 0 ? 'not_on_line' : ''">
                    </el-col>
                    <el-col :span="15">
                      <!-- 备注 --><!-- 名称 -->
                      <div class="colInfo"><span style="font-weight: bold">{{contact.name}}</span></div>
                      <!-- 个性签名 -->
                      <div class="colInfo" style="font-size: 0.6rem">这个人很懒，什么都没有留下</div>
                    </el-col>
                  </el-row>
                </div>
              </el-tab-pane>
              <el-tab-pane label="空间" name="space">
                动态
              </el-tab-pane>
            </el-tabs>
          </div>
        </div>
      </el-col>
      <el-col :span="18" style="height: 100%">
        <div style="background-color: #FFF;height: 100%;" >
          <img v-if="!toSocket" src="../../assets/imgs/chatRoomGround.jpg"  style="width: 100%;height: 100%">
          <div v-if="toSocket" style="height: 100%">
            <div style="height: 10%;border-bottom: 1px #ababab solid">
              <div style="text-align: left;padding: 40px 0 0 50px;font-size: 25px;">{{toSocket.name}}</div>
            </div>
            <div style="height: 60%;border-bottom: 1px #dfdddd solid;overflow: auto">
              <ul style="list-style: none;text-align: left">
                <li v-for="data in contentList" :class="data.toUser != loginUserInfo.pkid ? 'li_left':'li_right'" >
                  <el-row :gutter="20" >
                    <el-col :span="2" v-if="data.toUser != loginUserInfo.pkid">
                      <el-image class="image" src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" fit="cover">
                      </el-image>
                    </el-col>
                    <el-col :span="22" style="white-space: pre-wrap;">
                      <pre class="content" :style="{float:data.toUser == loginUserInfo.pkid ? 'right' : 'left'}" >{{data.message}}</pre>
                    </el-col>
                    <el-col :span="2" v-if="data.toUser == loginUserInfo.pkid">
                      <el-image class="image" src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" fit="cover">
                      </el-image>
                    </el-col>
                  </el-row>
                </li>
              </ul>
            </div>
            <div style="height: 30%">
              <div  style="height: 75%" >
                <el-input class="textarea" type="textarea" id="textarea"
                          @keydown.native.enter="listenerKeyDown"
                          v-model="sendContent" ></el-input>
              </div>
              <div style="height: 10%;text-align: right;margin: 5px 40px;position: relative;">
                <div class="popover" style="right:-10px;bottom: 35px;min-width: 115px;" v-if="popover">不能发送空白内容</div>
                <el-button slot="reference" type="success" size="mini" plain @click="sendMessage()" id="send">发送</el-button>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {searchContacts} from "../../api/user";
  import dateFormat from "../../common/util";


  export default {
    name: "ChatRoom",
    data() {
      return {
        sendContent: '',//要发送的内容
        condition: {
          filterText: '',
          type: 'message'
        },
        popover: false,//是否显示空白内容提示
        data: {
          messageList: [],
          contactsList: [],
          space: []
        },
        contentList: [],
        date: dateFormat("YYYY-mm-dd HH:MM:SS", new Date()),
        loginUserInfo: JSON.parse(localStorage.getItem("loginUserInfo")),
        websocket:null,
        toSocket:null,//选择通信的对象
        sendEvent:"ServerReceive",
        receiveEvent:"ClientReceive",
      }
    },
    created() {
      this.queryContacts();
      this.socketIoLoad();
    },
    methods: {
      queryContacts: function () {
        let $this = this;
        //当前用户Id
        searchContacts($this.loginUserInfo.pkid).then(res => {
          $this.data.contactsList = res.data;//用户好友联系人
          $("#textarea").focus();
        }).catch(error => {
          $this.$message({
            message: error.response.data.message,
            type: 'warning'
          });
        });
      },
      sendMessage: function () {
        let $this = this;
        $("#send").blur();//失去按钮焦点
        $("#textarea").focus();//文本框获取焦点
        if (!$this.sendContent) {//不能发送空的内容
          $this.popover = true;
          setTimeout(function () {
            $this.popover = false;
          }, 3000);
          return;
        }
        let data = {
          to:"1",
          messageType:'text',
          fromPkid:$this.loginUserInfo.pkid,
          from:$this.loginUserInfo.name,
          toUser:$this.toSocket.pkid,
          message:$this.sendContent
        }
        $this.contentList.push(data);
        //信息发送
        $this.send(data,$this.sendEvent);
        $this.sendContent = '';//文本框内容清空
      },
      listenerKeyDown: function (event) {
        event.preventDefault();
        if (event.shiftKey) {//按下shift键 进行换行操作
          this.sendContent += '\n';
        } else {//发送操作
          this.sendMessage();
        }
      },
      /**
       * webSocket
       */
      websocketLoad: function () {
        let $this = this;
        // 指定websocket路径
        if ('WebSocket' in window) {
          $this.websocket = new WebSocket("ws://127.0.0.1:9002/webSocket/" + $this.loginUserInfo.pkid);
        }
        if (!$this.websocket) {
          return;
        }
        $this.websocket.onmessage = function (event) {
          let data = JSON.parse(event.data);
          console.log(data);
          if (data.to == 0) {//上线消息

          } else if (data.to == -2) {//下线消息

          } else {
            // 普通消息
          }
        }
      },
      /**
       * socketIo
       */
      socketIoLoad:function () {
        let $this = this;
        $this.websocket = $this.$store.state.websocket;
        $this.websocket.on($this.receiveEvent, function (data) {
          console.log("receive",data);
        });
        $this.websocket.on('disconnect', function () {
          console.log("socket连接断开");
        });
      },
      send:function(data,event){
        let $this = this;
        $this.websocket.emit(event,data);
      },
      socketToContact:function (user) {
        let $this = this;
        $this.toSocket = user;
      }
    }
  }
</script>

<style scoped>

  .colInfo {
    text-align: left;
    margin-left: 5px;
    height: 30px;
    line-height: 30px
  }

  >>>.el-textarea{
    height: 100% !important;
  }
  .textarea>>>.el-textarea__inner{
    border: none !important;
    resize: none !important;
    height: 100% !important;
    font-size: 18px !important;
    color: black;
  }

  .textarea>>>.el-textarea__inner::-webkit-scrollbar {
    width: 6px;
    height: 6px;
  }
  .textarea>>>.el-textarea__inner::-webkit-scrollbar-thumb {
    border-radius: 3px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    background-color: #c3c3c3;
  }
  .textarea>>>.el-textarea__inner::-webkit-scrollbar-track {
    background-color: transparent;
  }

  .content{
    display: inline-block;
    border: 1px #ababab solid;
    border-radius: 5px;
    margin: 0 auto;
    min-height: 30px;
    line-height: 30px;
    padding: 5px;
    min-width: 20px;
    max-width: 70%;
    word-wrap:break-word;
  }

  .li_left {
    margin-bottom: 15px;
    max-width: 70%;
  }
  .image{
    width: 40px;
    height: 40px;
    display: inline-block;
    border-radius: 5px;
  }
  .li_right {
    margin-bottom: 15px;
    max-width: 70%;
    margin-left: 29%;
  }

  .chatRoomGround{
    background-image: url("../../assets/imgs/chatRoomGround.jpg");
  }

</style>
