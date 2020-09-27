<template>
	<div>
		<el-container>
			<el-main>
				<router-view/>
			</el-main>
			<el-footer>
				<el-menu router @select="handleSelect" mode="horizontal"  :default-active="home_url.index" active-text-color="dodgerblue">
					<el-menu-item :index="home_url.index" style='width: 25%'>
						<i class="el-icon-s-home"></i>
					</el-menu-item>
					<el-menu-item :index="home_url.edit" style='width: 25%'>
						<i class="el-icon-edit-outline"></i>
					</el-menu-item>
					<el-menu-item :index="home_url.message" style='width: 25%'>
						<i class="el-icon-s-comment"></i>
					</el-menu-item>
					<el-menu-item :index="home_url.self" style='width: 25%'>
						<i class="el-icon-user"></i>
					</el-menu-item>
				</el-menu>
			</el-footer>
		</el-container>
	</div>
</template>

<script>
	import urlconstant from '@/common/utils/urlconstant.js'
	export default {
		name :'home',
		data(){
			return{
				home_url:{
					beas:urlconstant.h5_lcoal_url_home,
					index:urlconstant.h5_lcoal_url_home_index,
					edit:urlconstant.h5_lcoal_url_home_edit,
					message:urlconstant.h5_lcoal_url_home_message,
					self:urlconstant.h5_lcoal_url_home_self,
				},
			}
		},
		methods: {
	      	handleSelect(index, item) {
	        	
	      	}
	    },
	    created(){
	    	window.location.href = '#'+this.home_url.index;
	    }
	}
</script>

<style>
	.el-footer{
		position:fixed;
		bottom:0px;
		height:auto;
		width: 100%;
		z-index:2;
		overflow:hidden;
		padding: 0px;
	}
	.el-menu{
		text-align: center;
	}
</style>