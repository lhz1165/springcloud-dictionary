<template>
  <div>
    <el-form ref="ruleForm" :model="form" label-width="80px" class="login-box" :rules="rules" >
      <h3 class="login-title">欢迎登录</h3>
      <el-form-item label="账号"  prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input  type="password" v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <!-- 对ruleForm 这个表单做验证-->
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button type="text" @click="dialogFormVisible = true">注册</el-button>
      </el-form-item>

      <el-dialog title="注册用户" :visible.sync="dialogFormVisible">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="账号" prop="pass">
            <el-input  v-model="ruleForm.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input  v-model="ruleForm.password" autocomplete="off"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="registerForm">确认</el-button>
          <el-button @click="dialogFormVisible = false">取 消</el-button>
        </div>
      </el-dialog>
    </el-form>

  </div>
</template>

<script>
import {postRequest} from "@/utils/apis";
import axios from "axios";
export default {
  name: "Login",
  data(){
    return {
      ruleForm:{
        username:'',
        password:'',
      },
      form:{
        username:'',
        password:'',
        type:'1',
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ]
      },
      dialogFormVisible: false,
    }
  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //登录拦截
          // axios.post('http://localhost:8089/login',{username: this.form.username, password: this.form.password,type:this.form.type})
          postRequest('/user/login',{userName: this.form.username, passWord: this.form.password})
              .then(successResponse => {
                if (successResponse.data.code === 200) {
                  this.$message.success('登录成功!')
                  //跳转
                  window.localStorage.clear();
                  window.localStorage.setItem('isLogin','true')
                  window.localStorage.setItem('token',successResponse.data.data)
                  // this.$store.dispatch('asyncUpdateUser',{
                  //   name:this.form.username
                  // })
                  //验证
                  this.$router.push({name:"main"})
                } else {
                  this.$message.error('账号或者密码错误!')
                  console.log("dk")
                }
              }).catch(failResponse => {
            console.log(failResponse)
            this.$message.warning('登录失败！')
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    registerForm(){
      postRequest('/user/register',{userName: this.ruleForm.username, passWord: this.ruleForm.password})
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$message.success('注册成功!')
              //跳转
             this.dialogFormVisible=false
            } else {
              this.$message.error('注册失败!')
              console.log("dk")
            }
          }).catch(failResponse => {
        console.log(failResponse)
        this.$message.warning('登录失败！')
      })
    },
  }
}
</script>

<style scoped>
.login-box{
  width: 400px;
  margin: 200px auto;
  border: 1px solid #DCDFE6;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 0 30px #DCDFE6;
}
.login-title{
  text-align: center;

}

</style>