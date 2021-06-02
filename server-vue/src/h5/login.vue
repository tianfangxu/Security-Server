<template>
  <div style="padding: 20px;padding-top: 30%">
    <el-form label-position="left" label-width="80px" :model="loginform">
      <el-form-item label="用户名:">
        <el-input v-model="loginform.username"></el-input>
      </el-form-item>
      <el-form-item label="密码:">
        <el-input type="password" v-model="loginform.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">登陆</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      loginform:{
        username: '',
        password: '',
      }
    };
  },
  methods:{
    login(){
      this.$axios.post(this.$root.baseUrl+"login",this.loginform,{
        headers:{
          'Content-Type': 'application/json'
        }
      }).then((res) => {
        res = res.data;
        if(res.state == 200){
          this.$root.token = res.result.token;
          this.$root.info = res.result.info;
          this.$message({
            message: res.message,
            type: 'success'
          });
          window.location.href="#/";
        }else{
          this.$message({
            message: res.message,
            type: 'error'
          });
        }
      }, (res) => {
        this.$message({
          message: "服务维护中...",
          type: 'error'
        });
      })
    }
  }
}
</script>

<style scoped>

</style>
