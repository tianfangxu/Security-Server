<template>
	<div>
		<div class="user-info-class">
			<!--'fill', 'contain', 'cover', 'none', 'scale-down'-->
			<el-image class="header-class" :src="url" fit="cover"></el-image>
		</div>
		
		<div class="user-item-class">
			
		</div>
	</div>
</template>

<script>
	export default {
	  	name: 'self',
	  	data(){
	  		return{
	  			url: '../../static/img/example/33.jpg'
	  		}
	  	}
	}
</script>

<style>
	.user-info-class{
		text-align: center;
		height: 200px;
		background-color: #1E90FF;
	}
	.header-class{
		width: 100px; 
		height: 100px;
		margin-top: 30px;
		border-radius: 100%;
	}
	.user-item-class{
		border-top: ;
	}
</style>