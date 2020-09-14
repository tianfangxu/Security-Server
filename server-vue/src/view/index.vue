<template>
	<el-container>
		  <el-header height="110px">
		  		<h1>欢迎来到XX,{{nike}}</h1>
		  		<a class="logout" @click="logout">退出</a>
		  </el-header>
		  <el-container>
			    <el-aside width="300px">
			    	<el-menu
				      default-active="1"
				      class="el-menu-vertical-demo"
				      @open="handleOpen"
				      @close="handleClose">
				       <el-submenu index="1">
				       	<template slot="title">
				        	<i class="el-icon-menu"></i>
				        	<span>系统管理</span>
				        </template>
				        <el-menu-item index="1-1" @click="linkHref('#/index/user')">
				        	<span>用户管理</span>
				        </el-menu-item>
				        <el-menu-item index="1-2" @click="linkHref('#/index/role')">
				        	<span>角色管理</span>
				        </el-menu-item>
				        <el-menu-item index="1-4" @click="linkHref('#/index/resource')">
				        	<span>资源管理</span>
				        </el-menu-item>
				        <el-menu-item index="1-3" @click="linkHref('#/index/dept')">
				        	<span>部门管理</span>
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
	export default {
	  	name: 'index',
	  	data(){
	  		return {
	  			nike:'11',
	  		}
	  	},
	   	methods: {
	      handleOpen(key, keyPath) {},
	      handleClose(key, keyPath) {
	      	window.location.href="#/index/home";
	      },
	      linkHref(uri){window.location.href=uri;},
	      logout(){
	      	this.$root.token = "";
	      	window.location.href="#/";
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
	.logout{
		float: right;
		color: white;
	}
</style>