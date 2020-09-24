<template>
<div>
	<div class="login">
		<div class="contentEx">
			<el-tabs type="border-card" v-model="active">
			    <el-tab-pane label="登录" name="first">
			    	<el-form :model="loginform" status-icon :rules="loginrule" ref="loginform" label-width="100px" class="demo-ruleForm">
						  <el-form-item label="登录账号" prop="username">
						    <el-input type="text" v-model="loginform.username" autocomplete="off"></el-input>
						  </el-form-item>
						  <el-form-item label="登录密码" prop="password">
						    <el-input type="password" v-model="loginform.password" autocomplete="off"></el-input>
						  </el-form-item>
						  <el-form-item style="margin-right: 15%;">
						    <el-button type="primary" @click="submitForm()">登录</el-button>
						  </el-form-item>
					</el-form>
			    </el-tab-pane>
			    <el-tab-pane label="注册" name="second">
			    	<div style="margin-top: 40px;margin-bottom: 40px;color: firebrick;">
			    		注册暂时未开放，请联系管理员（田方旭vx：tianfangxu1993）
			    	</div>
			    	
			    </el-tab-pane>
			</el-tabs>
		</div>
	</div>
</div>
</template>

<script>
	import urlconstant from '@/common/utils/urlconstant.js'
	
	export default{
		data() {
	      var isnull = (rule, value, callback) => {
	        if (!value) {
	          callback(new Error('用户或密码不能为空'));
	        }else {
	          callback();
	        }
	      };
	      return {
	        loginform: {
	          username: '',
	          password: ''
	        },
	        loginrule: {
	          username: [
	            { validator: isnull, trigger: 'blur' }
	          ],
	          password: [
	            { validator: isnull, trigger: 'blur' }
	          ]
	        },
	        active:"first",
	      };
	    },
	    methods: {
	      submitForm() {
	      	if(!this.loginform.username || !this.loginform.password){
	      		return;
	      	}
	        var loding = this.$loading({ fullscreen: true });
	        this.$axios.post(this.$root.baseUrl+"login",this.loginform,{
	        	headers:{
					'Content-Type': 'application/json'
				}
	        }).then((res) => {
	        	res = res.data;
	        	console.log(res)
	        	if(res.state == 200){
	        		this.$root.token = res.result.token;
	        		this.$root.info = res.result.info;
			        loding.close();
			        this.$message({
				          message: res.message,
				          type: 'success'
				    });
				    window.location.href="#"+urlconstant.base_local_url_system_home;
	        	}else{
	        		loding.close();
			        this.$message({
				          message: res.message,
				          type: 'error'
				    });
	        	}
	        }, (res) => {
	        	loding.close();
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
	.login{
		background-color: lightblue;
		background-image: url(../../static/img/backg.png);
		height: 700px;
		padding-top: 10%;
	}
	.contentEx{
		width: 30%;
		margin-left: 35%;
		background-color: white;
		text-align: center;
	}
</style>