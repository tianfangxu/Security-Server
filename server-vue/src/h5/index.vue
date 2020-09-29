<template>
	<el-container>
		<el-header height="160px">
			<div class="block">
				<el-carousel trigger="click" height="160px">
					<el-carousel-item v-for="item in heardImg" :key="item">
						<img :src="item" />
					</el-carousel-item>
				</el-carousel>
			</div>
		</el-header>
		<el-main class="main-class">
		  	<div>
		  		<el-tabs v-model="activeName" :stretch=true  @tab-click="handleClick">
				    <el-tab-pane label="关注" name="first">
				    	<div style="padding-bottom: 60px;background-color: #F2F6FC;">
				    		<div class="card-item-class" v-for="i in 10">
				    			<div class="card-item-header-class">
				    				<el-avatar shape="square" size="small" src="../../static/img/example/33.jpg" style="vertical-align: middle;"></el-avatar>
				    				<span class="card-item-header-user-name-class">田方旭</span>
				    				<div class="card-item-header-user-edit-class"><i class="el-icon-arrow-down"></i></div>
				    			</div>
				    			<div class="card-item-contant-class">
				    				这里面一段文字描述，内容可以多一点，但是不建议写的非常长，因为有可能一个屏幕装不下,差不多就可以了，最好不要超过两行,太长了就会很难看
				    			</div>
				    			<el-image fit="cover" class='card-item-img-class' src='../../static/img/example/33.jpg'>
				    				<div slot="error" class="image-slot">
        								<i class="el-icon-picture-outline"></i>
      								</div>
				    			</el-image>
				    			<div class="card-item-footer-class">
				    				<el-row>
									  	<el-col :span="8">
									  		<i class="el-icon-share"></i>
									  	</el-col>
									  	<el-col :span="8">
									  		<i class="el-icon-chat-dot-square"></i>
									  	</el-col>
									  	<el-col :span="8">
									  		<i class="el-icon-star-off"></i>
									  	</el-col>
									</el-row>
				    			</div>
				    		</div>
				    	</div>
				    </el-tab-pane>
				    <el-tab-pane label="热门" name="second">
				    	<div style="padding-bottom: 60px;background-color: #F2F6FC;">
				    		<div class="card-item-class" v-for="i in 10">
				    			<div class="card-item-header-class">
				    				<el-avatar shape="square" size="small" src="../../static/img/example/55.jpg" style="vertical-align: middle;"></el-avatar>
				    				<span class="card-item-header-user-name-class">张三</span>
				    				<div class="card-item-header-user-edit-class"><i class="el-icon-arrow-down"></i></div>
				    			</div>
				    			<div class="card-item-contant-class">
				    				这里面一段文字描述，内容可以多一点，但是不建议写的非常长，因为有可能一个屏幕装不下,差不多就可以了，最好不要超过两行,太长了就会很难看
				    			</div>
				    			<el-image fit="cover" class='card-item-img-class' src='../../static/img/example/55.jpg'>
				    				<div slot="error" class="image-slot">
        								<i class="el-icon-picture-outline"></i>
      								</div>
				    			</el-image>
				    			<div class="card-item-footer-class">
				    				<el-row>
									  	<el-col :span="8">
									  		<i class="el-icon-share"></i>
									  	</el-col>
									  	<el-col :span="8">
									  		<i class="el-icon-chat-dot-square"></i>
									  	</el-col>
									  	<el-col :span="8">
									  		<i class="el-icon-star-off"></i>
									  	</el-col>
									</el-row>
				    			</div>
				    		</div>
				    	</div>
				    </el-tab-pane>
				</el-tabs>
		  	</div>
		</el-main>
	</el-container>
</template>

<script>
	export default {
	  	name: 'index',
	  	data() {
      		return {
	        	activeName: 'first',
	        	windowHeight: '600px',
	        	heardImg:[]
	        };
	    },
	    methods: {
	      	handleClick(tab, event) {
	        	console.log(tab, event);
	      	}
	    },
	    created(){
	    	this.windowHeight = (window.innerHeight-160)+'px';
	    	this.heardImg = ['../../static/img/example/22.jpg',
					    	'../../static/img/example/33.jpg',
					    	'../../static/img/example/44.jpg',
					    	'../../static/img/example/55.jpg']
	    }
	}
</script>
	
<style>
	.el-header{
		color: cadetblue;
		text-align: center;
		padding: 0px;
	}
	.el-tabs__header{
		padding: 0;
	    position: relative;
	    margin: 0 0 0px;
	}
	.main-class{
		padding: 0px;
		height:auto!important;
	}
	.card-item-class{
		height: auto;
		border-bottom: 1px solid #EBEEF5;
		border-top: 1px solid #EBEEF5;
		padding-top: 5px;
		background-color: #FFFFFF;
		margin-bottom: 10px;
	}
	.card-item-img-class{
		padding-top: 5px;
		width: 100%;
		height: 150px;
	}
	.card-item-header-class{
		padding-left: 5px;
	}
	.card-item-header-user-edit-class{
		float: right;
		padding-right: 5px;
	}
	.card-item-header-user-name-class{
		font-size: 14px;
		color: gold;
	}
	.card-item-contant-class{
		padding-left: 5px;
		padding-top: 5px;
		font-size: 14px;
	}
	.card-item-footer-class{
		height: 20px;
		text-align: center;
		padding-bottom: 5px;
		padding-top: 5px;
	}
</style>