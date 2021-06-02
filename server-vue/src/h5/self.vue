<template>
	<div style="color: #E4E7ED;">
		<div class="user-info-class">
			<div style="text-align: right;margin-right: 10px;padding-top: 10px;color: white;"><i class="el-icon-setting"></i></div>
			<!--'fill', 'contain', 'cover', 'none', 'scale-down'-->
			<el-image class="header-class" :src="userInfo.avatar" fit="cover"></el-image>
			<div style="color: white;">{{userInfo.realName}}</div>
		</div>

		<div style="height: 20px;"></div>

		<div class="user-item-class">
			<span>我的关注</span>
			<div style="float: right;">
				<i class="el-icon-arrow-right"></i>
			</div>
		</div>
		<div class="user-item-class">
			<span>我的粉丝</span>
			<div style="float: right;">
				<i class="el-icon-arrow-right"></i>
			</div>
		</div>
		<div class="user-item-class">
			<span>我的博客</span>
			<div style="float: right;">
				<i class="el-icon-arrow-right"></i>
			</div>
		</div>

	</div>
</template>

<script>
	export default {
	  	name: 'self',
	  	data(){
	  		return{
	  			userInfo:{
	  				realName:'',
	  				avatar:'../../static/img/example/33.jpg',

	  			},
	  		}
	  	},
    methods:{

    },
    created() {
      if(this.$root.token == null || this.$root.token == ''){
        window.location.href = '#/loginWeb';
      }
      this.userInfo.realName = this.$root.info.realName;
    }
  }
</script>

<style>
	.user-info-class{
		text-align: center;
		height: 200px;
		background-color: dodgerblue;
	}
	.header-class{
		width: 100px;
		height: 100px;
		margin-top: 10px;
		border-radius: 100%;
	}
	.user-item-class{
		font-size: 20px;
		color: #303133;
		padding-left: 15px;
		border-bottom: 1px solid #EBEEF5;
		padding: 18px 20px;
	}
</style>
