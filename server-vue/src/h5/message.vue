<template>
	<div style="background-color: #F2F6FC;">
		<div class="message-header-class">
			<div style="padding-top: 10px;margin-left: 10px;">
				<span style="font-weight: 400;">消息</span>
				<div style="float: right;padding-right: 10px;">
					<i class="el-icon-circle-plus-outline"></i>
				</div>
			</div>
		</div>
		<div class="search-class">
			<el-input placeholder="请输入内容" prefix-icon="el-icon-search" v-model="searchName"></el-input>
		</div>
		<div class="message-contant-class">
			<div class="message-contant-ietm-class">
				<div style="padding-top: 0px;">
					<i style="color: #1E90FF;" class="el-icon-s-promotion"></i>
					回复我的
					<div style="float: right;"><i class="el-icon-arrow-right"></i></div>
				</div>
			</div>
			<div class="message-contant-ietm-class">
				<div style="padding-top: 0px;">
					<i style="color: #1E90FF;" class="el-icon-edit"></i>
					我的评论
					<div style="float: right;"><i class="el-icon-arrow-right"></i></div>
				</div>
			</div>
			<div class="message-contant-ietm-class">
				<div style="padding-top: 0px;">
					<i style="color: #1E90FF;" class="el-icon-star-on"></i>
					我的点赞
					<div style="float: right;"><i class="el-icon-arrow-right"></i></div>
				</div>
			</div>
			<div class="message-contant-ietm-class">
				<div style="padding-top: 0px;">
					<i style="color: #1E90FF;" class="el-icon-s-custom"></i>
					我的群组
					<div style="float: right;"><i class="el-icon-arrow-right"></i></div>
				</div>
			</div>


			<!--从这里开始事聊天会话-->
			<div v-for="users in userList" class="message-contant-ietm-class">
				<div style="padding-top: 0px;" @click="goSession">
					<el-avatar shape="square" size="small" :src="users.avatar" style="vertical-align: middle;"></el-avatar>
					<span class="card-item-header-user-name-class">{{users.userName}}</span>
					<div style="float: right;font-size: 12px;padding-top: 10px;padding-right: 20px;">
						{{users.lastTime}}
					</div>
				</div>
			</div>

		</div>
	</div>
</template>

<script>
	import urlconstant from '@/common/utils/urlconstant.js'
	export default {
	  	name: 'message',
	  	data(){
	  		return{
	  			searchName:'',
				userList:[
					{
						avatar:"../../static/img/example/55.jpg",
						userName:"聊天室",
						lastTime:"2020-09-27"
					}
				]
	  		}
	  	},
		methods:{
			goSession(){
				window.location.href = '#'+urlconstant.h5_lcoal_url_home_session;
			}
		},
    created() {
      if(this.$root.token == null || this.$root.token == ''){
        window.location.href = '#/loginWeb';
      }
    }
	}
</script>

<style>
	.message-header-class{
		height: 40px;
		background-color: white;
		text-align: center;
	}
	.search-class{
		padding: 5px;
	}
	.message-contant-class{
		padding-top: 5px;
		background-color: white;
	}
	.message-contant-ietm-class{
		height: 30px;
		border-bottom: 1px solid #EBEEF5;
		border-top: 1px solid #EBEEF5;
		padding: 5px 5px 5px 15px;
		font-size: 18px;


	}
</style>
