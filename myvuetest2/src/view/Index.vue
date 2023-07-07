<template>
  <div class="homeWrapper">
    <!--  <el-container style=" border: 1px solid #eee">-->
    <el-container class="main_container">
      <!--头-->
      <el-header style=" font-size: 12px" class="homeHeader">
        <div class="title">单词查询</div>
        <el-dropdown class="el-dropdown-link" @command="commandHandler">
        <span >
          {{user.userName}}
<!--          <img  class="info-pic" :src="'http://localhost:8089'+user.avatar" alt="">-->
        </span>
          <el-dropdown-menu slot="dropdown">
<!--            <el-dropdown-item command="profile">个人中心</el-dropdown-item>-->
            <!--          <el-dropdown-item command="setting">设置</el-dropdown-item>-->
            <el-dropdown-item command="logout">
              退出
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>




      <el-container>



        <el-container>
          <el-main>
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="英译汉/汉译英">
                <el-input v-model="form.word"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="onsubmit">查询单词</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>


            <el-descriptions title="翻译信息" :column="1">
              <el-descriptions-item label="翻译" >{{ resultWord.translation }} </el-descriptions-item>
<!--              翻译成功一定存在，需要应用绑定语音合成服务才能正常播放否则返回110错误码-->
<!--              <el-descriptions-item label="发音地址">-->
<!--                <audio controls="controls" autoplay="autoplay">-->
<!--                  <source v-bind:src="resultWord.tspeakUrl" type="audio/mpeg">-->
<!--                </audio>-->
<!--              </el-descriptions-item>-->
              <el-descriptions-item label="其他解释">{{ resultWord.explains }}</el-descriptions-item>
            </el-descriptions>

          </el-main>

        </el-container>
      </el-container>
      <el-footer>
        <div>
          @Copyright 2023
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
import {getRequest, postRequest} from "@/utils/apis";

export default {
  name: "Index",
  data() {
    return {
      user:{},
      form: {},
      resultWord: {
        translation: "",
        tspeakUrl: "",
        explains: "",

      }
    }
  },
  mounted() {
    this.getUserInfo()
  },
  methods:{
    getUserInfo(){
      postRequest('/user/getUser')
          .then(resp=>{
            if (resp.data.code===200){
              this.user=resp.data.data;
              window.localStorage.removeItem("user")
              window.localStorage.setItem("user",JSON.stringify(resp.data.data))
            }
          })
    },
    onsubmit(){
      getRequest('/user/getWord',{word:this.form.word})
          .then(resp=>{
            if (resp.data.code===200){
              this.resultWord=resp.data.data;
            }
          })
    },
    commandHandler(command){
      if (command === "logout") {
        axios.post("http://localhost:12010/user/logout")
            .then(resp=>{
              if (resp.data.code===200){
                this.$message.success('退出成功!')
                window.localStorage.clear()
                this.$router.replace("/login")
              }
            })
      }else if (command==="profile"){
        this.$router.replace({path:'/index/profile',query:{id:this.user.id}})
      }
    },
  },
}
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}



.el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.homeHeader{
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 15px;
  box-sizing: border-box;
}
.homeHeader .title{
  font-size: 30px;
  color: white;
}
.info-pic{
  width: 40px;
  height: 40px;
  border-radius: 10px;
  margin-top: 20px;
}
.homeWrapper {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.main_container {
  height: 100%;
}

</style>