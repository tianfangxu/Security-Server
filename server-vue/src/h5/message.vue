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
					<i style="color: #1E90FF;" class="el-icon-thumb"></i>
					我的点赞
					<div style="float: right;"><i class="el-icon-arrow-right"></i></div>
				</div>
			</div>
			
			<!--从这里开始事聊天会话-->
			<div class="message-contant-ietm-class">
				<div style="padding-top: 0px;">
					<el-avatar shape="square" size="small" src="../../static/img/example/55.jpg" style="vertical-align: middle;"></el-avatar>
					<span class="card-item-header-user-name-class">张三</span>
					<div style="float: right;font-size: 12px;padding-top: 10px;padding-right: 20px;">
						2020-09-27
					</div>
				</div>
			</div>
			<div class="message-contant-ietm-class">
				<div style="padding-top: 0px;">
					<el-avatar shape="square" size="small" src="../../static/img/example/33.jpg" style="vertical-align: middle;"></el-avatar>
					<span class="card-item-header-user-name-class">田方旭</span>
					<div style="float: right;font-size: 12px;padding-top: 10px;padding-right: 20px;">
						2020-08-11
					</div>
				</div>
			</div>
			
			<div class="message-contant-ietm-class">
				<div style="padding-top: 0px;">
					<el-avatar shape="square" size="small" src="../../static/img/example/22.jpg" style="vertical-align: middle;"></el-avatar>
					<span class="card-item-header-user-name-class">李四</span>
					<div style="float: right;font-size: 12px;padding-top: 10px;padding-right: 20px;">
						2020-08-17
					</div>
				</div>
			</div>
			
		</div>
	</div>
</template>

<script>
	export default {
	  	name: 'message',
	  	data(){
	  		return{
	  			searchName:'',
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