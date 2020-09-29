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
		<div style="padding-right: 10px;padding-bottom: 60px;">
			<div v-for="i in imgsNumber.length">
				<el-input
				  type="textarea"
				  placeholder="正文内容"
				  :autosize="{ minRows: 5, maxRows: 999}" 
				  class='edit-content-class'>
				</el-input>
				<div style="float: right;margin-top: -36px;position: sticky;font-size: 24px;">
					<el-upload 
						:show-file-list="false"
						action="https://jsonplaceholder.typicode.com/posts/" 
						:on-success="handleAvatarSuccess"
						:before-upload="beforeAvatarUpload"
						>
						<i class="el-icon-picture-outline" style="color: #1E90FF;"></i>
					</el-upload>
				</div>
				
				<div style="padding-left: 10px;" v-for="j in imgsNumber[i]" :key="j">
					<el-divider v-if='j == 1'><div style="color: #909399;">插入图片</div></el-divider>
					<el-image style="width: 100%;" :src="imgsList[j+imgsNumber[i-1]-1]" fit="cover" ></el-image>
					<el-divider v-if='j == imgsNumber[i]'><div style="color: #909399;">插入正文</div></el-divider>
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
		        contentList:[
		        	{
		        		content:'',
		        		imgs:['../../static/img/example/44.jpg','../../static/img/example/44.jpg']
		        	}
		        ],
		        imgsNumber:[0],	//这个数组的初始值位0，数组的长度表示上述正文的个数，每个下标对应的值。表示图片的个数。图片的位置为该位置前面元素的总和，例如：[0,3,5],表示：共有2个正文8涨图片，第一个正文有3张，为imgsList中的1-3；第二个正文有5张，为imgsList中的（0+3） - （0+3+5）
		        imgsList:['../../static/img/example/44.jpg','../../static/img/example/44.jpg','../../static/img/example/44.jpg','../../static/img/example/44.jpg','../../static/img/example/44.jpg']
		      }
		},
		methods:{
			handleAvatarSuccess(res, file){
				imgs.push(URL.createObjectURL(file.raw));
			},
			beforeAvatarUpload(){
				
			},
			handlePictureCardPreview(file) {
		        this.dialogImageUrl = file.url;
		        this.dialogVisible = true;
		    }
		}
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