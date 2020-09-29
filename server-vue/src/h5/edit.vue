<template>
	<div>
		<div style="padding-top: 10px;padding-right: 10px;">
			<el-input 
				v-model="headertext" 
				type="textarea" 
				:autosize="{ minRows: 2, maxRows: 6}" 
				class="edit-header-class" 
				placeholder="输入标题"
				maxlength="100"
  				show-word-limit>
			</el-input>
		</div>
		<div>
			<el-divider><div style="color: #909399;">下面是正文</div></el-divider>
		</div>
		<div style="padding-right: 10px;">
			<div>
				<el-input
				  type="textarea"
				  placeholder="正文内容"
				  v-model="contenttext" 
				  :autosize="{ minRows: 3, maxRows: 999}" 
				  class='edit-content-class'>
				</el-input>
				<div style="float: right;margin-top: -36px;position: sticky;font-size: 24px;">
					<i class="el-icon-picture-outline" style="color: #1E90FF;"></i>
				</div>
			</div>
			
		</div>
	</div>
</template>

<script>
	export default {
	  	name: 'edit',
	  	data() {
		      return {
		        headertext:'',
		        contenttext:''
		      }
		},
	}
</script>

<style scoped="scoped">
	.edit-header-class{
		width: 100%;
		font-size: 20px;
		border: 0;  
        outline: none; 
        background-color: rgba(0, 0, 0, 0);
        padding: 5px 5px 5px 5px;
	}
	.edit-content-class{
		padding: 5px 5px 5px 5px;
		font-size: 18px;
		width: 100%;
	}
</style>