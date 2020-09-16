<template>
	<el-container>
	  <el-header height="100px" >
			
	  </el-header>
	  <el-main class="main-class">
	  	<div>
	  		<el-menu
			  class="el-menu-demo"
			  mode="horizontal"
			  @select="handleSelect"
			  background-color="dodgerblue"
			  text-color="#fff"
			  active-text-color="#ffd04b"
			  >
			  <el-menu-item index="1">首页</el-menu-item>
			  <el-menu-item index="2">消息中心</el-menu-item>
			  <el-menu-item index="3" >惊喜</el-menu-item>
			  <el-menu-item index="4">空白</el-menu-item>
			</el-menu>
	  	</div>
	  	<div class="content-class">
	  		<router-view />
	  	</div>
	  		
	  </el-main>
	</el-container>
</template>

<script>
	export default {
	  	name: 'index',
	  	data(){
	  		return {
	  		
	  		}
	  	},
	   	methods: {
	      handleSelect(index,indexPath){
	      	switch (index){
	      		case '1':
	      			window.location.href="#/index/home";
	      			break;
	      		case '2':
	      			window.location.href="#/index/message";
	      			break;
	      		case '3':
	      			window.location.href="#/index/test1";
	      			break;
	      	}
	      	
	      }	
	    },
	    created(){
	    	
	    }
	}
</script>
	
<style>
	.el-header{
		color: cadetblue;
		text-align: center;
	}
	.main-class{
		padding: 0px;
	}
	.content-class{
		padding: 10px;
	}
	.el-menu-item{
		font-size: 20px;
		
	}
</style>