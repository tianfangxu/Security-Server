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
				    	<div style="text-align: center;">
				    		
				    	</div>
				    </el-tab-pane>
				    <el-tab-pane label="热门" name="second">
				    	<div>
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
				    		热门 /n<br />
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
	.main-class{
		padding: 0px;
		height:auto!important;
	}
	.content-class{
		padding: 10px;
	}
	.el-menu-item{
		font-size: 20px;
	}
	.el-card{
		height: 150px;
		padding: 0px;
	}
</style>