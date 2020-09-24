<template>
	<el-container>
		  <el-header height="110px">
		  	<div class="user-info">
		  		<a @click="logout" style="padding-right: 20px;">退出</a>
		  		<el-avatar :src='this.$root.info.avatar' style="vertical-align: middle;" icon="el-icon-user-solid"></el-avatar>
		  		<span style="padding-left: 5px;" @click="getUserInfo">{{nike}}</span>
		  	</div>
		  	
		  	<h1>欢迎来到后台管理系统,{{nike}}</h1>
		  		
		  </el-header>
		  <el-container>
			    <el-aside width="300px">
			    	<el-menu
				      default-active="1"
				      class="el-menu-vertical-demo"
				      @open="handleOpen"
				      @close="handleClose">
				      	<el-menu-item  @click="linkHref(1)">
				      		<i class="el-icon-s-home"></i>
					        <span>首页</span>
				      	</el-menu-item>
				       	<el-submenu index="1">
					       	<template slot="title">
					        	<i class="el-icon-setting"></i>
					        	<span>系统管理</span>
					        </template>
					        <el-menu-item index="1-1" @click="linkHref(2)">
					        	<span>用户管理</span>
					        </el-menu-item>
					        <el-menu-item index="1-2" @click="linkHref(3)">
					        	<span>角色管理</span>
					        </el-menu-item>
					        <el-menu-item index="1-4" @click="linkHref(4)">
					        	<span>资源管理</span>
					        </el-menu-item>
					        <el-menu-item index="1-3" @click="linkHref(5)">
					        	<span>部门管理</span>
					        </el-menu-item>
				      	</el-submenu>
				      	<el-submenu index="2">
				      		<template slot="title">
					        	<i class="el-icon-menu"></i>
					        	<span slot="title">日志管理</span>
				        	</template>
				        	<el-menu-item index="2-1" @click="linkHref(6)">
					        	<span>操作日志</span>
					        </el-menu-item>
				      	</el-submenu>
				    </el-menu>
			    </el-aside>
			    <el-main>
			    	<router-view/>
			    </el-main>
		  </el-container>
	</el-container>
</template>

<script>
	import urlconstant from '@/common/utils/urlconstant.js'
	
	export default {
	  	name: 'index',
	  	data(){
	  		return {
	  			nike:'',
	  		}
	  	},
	   	methods: {
	      handleOpen(key, keyPath) {},
	      handleClose(key, keyPath) {},
	      linkHref(type){
	      	var uri = '';
	      	switch(type){
	      		case 1:
	      			uri = urlconstant.base_local_url_system_home;
	      			break;
	      		case 2:
	      			uri = urlconstant.base_local_url_system_user;
	      			break;
	      		case 3:
	      			uri = urlconstant.base_local_url_system_role;
	      			break;
	      		case 4:
	      			uri = urlconstant.base_local_url_system_resource;
	      			break;
	      		case 5:
	      			uri = urlconstant.base_local_url_system_dept;
	      			break;
	      		case 6:
	      			uri = urlconstant.base_local_url_system_logAction;
	      			break;
	      	}
	      	window.location.href="#"+uri;
	      },
	      logout(){
	      	this.$root.token = "";
	      	window.location.href="#"+urlconstant.base_local_url_login;
	      },
	      getUserInfo(){
	      	console.log(this.$root.info)
	      }
	      
	    },
	    created(){
	    	this.$root.islogin();
	    	this.nike = this.$root.info.realName;
	    }
	}
</script>

<style scoped>
	.el-header{
		background-color: dodgerblue;
		color: white;
		text-align: center;
	}
	.user-info{
		float: right;
		color: white;
		padding-top: 10px;
	}
</style>