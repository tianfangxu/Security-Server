<template>
	<div>
		<!--查询表单-->
		<el-form :inline="true" :model="logs" class="demo-form-inline" >
			<el-form-item label="服务名称">
				<el-input v-model="logs.server" placeholder="服务名称"></el-input>
			</el-form-item>
			<el-form-item label="访问地址">
				<el-input v-model="logs.url" placeholder="访问地址"></el-input>
			</el-form-item>
			<el-form-item label="用户名称">
				<el-input v-model="logs.createUserName" placeholder="用户名称"></el-input>
			</el-form-item>
			<el-form-item label="创建日期">
			  	<el-date-picker
			      v-model="logs.startTime" value-format="yyyy-MM-dd HH:mm:ss" 
			      type="datetime" placeholder="选择开始时间">
			   	</el-date-picker>
			  
			    <el-date-picker
			      v-model="logs.endTime" value-format="yyyy-MM-dd HH:mm:ss" 
			      type="datetime" placeholder="选择结束时间">
			    </el-date-picker>
		  </el-form-item>
			<el-form-item>
		    	<el-button type="primary" @click="onSubmit">查询</el-button>
		  	</el-form-item>
		</el-form>
		
		<!--数据列表-->
		<el-table :data="tableData" style="width: 100%">
			<el-table-column prop="id"  label="主键"  width="80"></el-table-column>
			<el-table-column prop="server" label="服务名称" width="180"> </el-table-column>
		    <el-table-column prop="url"  label="访问地址"  width="180"></el-table-column>
		    <el-table-column prop="request"  label="请求参数"  width="180">
		    	<template title="1111" slot-scope='scope'>
		    		<span :title="scope.row.request">{{scope.row.request | stringSubLenfth }}</span>	
		    	</template>
		    	
		    </el-table-column>
		    <el-table-column prop="response" label="返回参数" width="180">
		    	<template slot-scope='scope'>
		    		<span :title="scope.row.response">{{scope.row.response | stringSubLenfth }}</span>
		    	</template>
		    </el-table-column>
		    <el-table-column prop="createTime" label="操作时间" width="180"> </el-table-column>
		    <el-table-column prop="createUserId" label="操作人" width="80"> </el-table-column>
		    <el-table-column prop="createUserName" label="操作人姓名" width="180"> </el-table-column>
		    <el-table-column prop="createUserIp" label="操作地址" width="180"> </el-table-column>
		    <el-table-column prop="createUserClient" label="操作客户端" width="180">
		    	<template slot-scope='scope'>
		    		<span :title="scope.row.createUserClient">{{scope.row.createUserClient | stringSubLenfth }}</span>
		    	</template>
		    </el-table-column>
		</el-table>
		
		<!--分页栏-->
		<el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="logs.page"
            :page-sizes="[5, 10, 20, 40]" 
            :page-size="logs.rows"         
            layout="total, sizes, prev, pager, next, jumper"
            :total="tempdata.total">
        </el-pagination>
        
	</div>
</template>

<script>
	export default {
	  	name: 'logAction',
	  	data() {
		    return {
		    	logs:{
		    		server:"",
		    		url:"",
		    		createUserName:"",
		    		startTime:"",
		    		endTime:"",
		          	page:1, //初始页
	            	rows:5, //每页的数据
		    		
		    	},
		    	tableData: [],
		    	tempdata:{
		        	total:0,
		        },
		    }
		},
		methods:{
			onSubmit() {
	     		this.logs.page = 1;
	       		this.getData();
	      	},
			getData(){
				var loding = this.$loading({ fullscreen: true });
	        	this.$axios.get(this.$root.splitUrlByGet('queryLogAction',this.logs),{
		        	headers:{
						'Content-Type': 'application/json',
						'x-phone-header-id':this.$root.token,
						'X-XSRF-TOKEN':this.$root.getCookie("XSRF-TOKEN")
						
					}
		        }).then((res) => {
		        	loding.close();
		        	if(res.data.state != 200){
				        this.$message({
					          message: res.data.message,
					          type: 'error'
					    });
					    return;
		        	}
		        	this.tableData = res.data.result.rows;
		        	this.tempdata.total = res.data.result.total;
		        }, (res) => {
		        	loding.close();
		        	this.$message({
				          message: "服务维护中...",
				          type: 'error'
				    });
		        })
			},
	        handleSizeChange: function (size) {
	               this.logs.rows = size;
	               this.getData();
	        },
	        handleCurrentChange: function(page){
	                this.logs.page = page;
	                this.getData();
	        },
		},
	    created(){
	    	this.getData();
	    }
	}
</script>

<style>
</style>