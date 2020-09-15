<template>
	<div>
		<!--查询表单-->
		<el-form :inline="true" :model="resource" class="demo-form-inline" >
			<el-form-item label="编号">
				<el-input v-model="resource.resourceCode" placeholder="编号"></el-input>
			</el-form-item>
			<el-form-item label="名称">
				<el-input v-model="resource.resourceName" placeholder="名称"></el-input>
			</el-form-item>
			<el-form-item label="类型">
		     <el-select v-model="resource.type" placeholder="类型">
		     	<el-option label="请选择" value=""></el-option>
		     	<el-option label="接口" value="2"></el-option>
		      	<el-option label="页面" value="1"></el-option>
		    </el-select>
		  </el-form-item>
			<el-form-item>
		    	<el-button type="primary" @click="onSubmit">查询</el-button>
		    	<el-button type="success" @click="dialogVisible = true">新增</el-button>
		  	</el-form-item>
		</el-form>
		<!--数据表-->
		<el-table :data="tableData" style="width: 100%">
		    <el-table-column  prop="id"  label="主键"  width="80"></el-table-column>
		    <el-table-column  prop="resourceCode"  label="编号"  width="180"></el-table-column>
		    <el-table-column  prop="resourceName"  label="名称"  width="180"></el-table-column>
		    <el-table-column  prop="resourceUrl"  label="资源路径"  width="150"></el-table-column>
		    <el-table-column  prop="parentId"  label="上级主键"  width="80"></el-table-column>
		    <el-table-column  prop="sort"  label="排序"  width="80"></el-table-column>
		    <el-table-column  prop="isLeaf"  label="是否为叶子"  width="100"></el-table-column>
		    <el-table-column  prop="type"  label="类型"  width="180"></el-table-column>
		    <el-table-column label="新增日期" width="180">
		    	<template slot-scope='scope'>
		    		{{scope.row.createTime}} 
		    	</template>
		    </el-table-column>
		    <el-table-column  label="操作" > 
		    	<template slot-scope="scope">
		    		<el-popconfirm  title="确认将该数据删除吗？" @onConfirm="deleteResource(scope.row)" >
			        	<el-button slot="reference" type="text" size="small">删除</el-button>
			        </el-popconfirm>
			    </template>
		    </el-table-column>
		</el-table>
		
		 <!--分页栏-->
		<el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="resource.page"
            :page-sizes="[5, 10, 20, 40]" 
            :page-size="resource.rows"         
            layout="total, sizes, prev, pager, next, jumper"
            :total="tempdata.total">
        </el-pagination>
        
        <!--方法弹窗-->
        <el-dialog  title="新增资源"  :visible.sync="dialogVisible"  :before-close="handleClose">
		  	<el-form :model="insertResource" :inline="true" >
		  		<el-form-item label="编码" :label-width="formLabelWidth">
		  			<el-input v-model="insertResource.resourceCode" placeholder="编码" ></el-input>
		  		</el-form-item>
		  		<el-form-item label="名称" :label-width="formLabelWidth">
		  			<el-input v-model="insertResource.resourceName" placeholder="名称"></el-input>
		  		</el-form-item>
		  		<el-form-item label="资源路径" :label-width="formLabelWidth">
		  			<el-input v-model="insertResource.resourceUrl" placeholder="资源路径" ></el-input>
		  		</el-form-item>
		  		<el-form-item label="排序" :label-width="formLabelWidth">
		  			<el-input v-model.number="insertResource.sort" placeholder="排序"></el-input>
		  		</el-form-item>
		  		<el-form-item label="上级主键" :label-width="formLabelWidth">
		  			<el-input v-model="insertResource.parentId" placeholder="上级主键" ></el-input>
		  		</el-form-item>
		  		<el-form-item label="是否为叶子" :label-width="formLabelWidth">
		  			<el-select v-model="insertResource.isLeaf" placeholder="请选择">
				      	<el-option label="是" value="1"></el-option>
				      	<el-option label="否" value="0"></el-option>
			    	</el-select>
		  		</el-form-item>
		  		<el-form-item label="类型" :label-width="formLabelWidth">
		  			<el-select v-model="insertResource.type" placeholder="请选择">
				      	<el-option label="页面" value="1"></el-option>
				      	<el-option label="接口" value="2"></el-option>
			    	</el-select>
		  		</el-form-item>
		  	</el-form>
		  	<span slot="footer" class="dialog-footer">
		    	<el-button @click="handleClose">取 消</el-button>
		    	<el-button type="primary" @click="addResource">确 定</el-button>
		  	</span>
		</el-dialog>
	</div>
</template>

<script>
	export default{
		name:"resource",
		data(){
			return {
				tableData:[],
				resource:{
					resourceCode:'',
					resourceName:'',
					type:'',
					page:1,
					rows:5
				},
				tempdata:{
					total:0,
				},
				dialogVisible:false,
				insertResource:{
					resourceCode:'',
					resourceName:'',
					resourceUrl:'',
					sort:'',
					parentId:null,
					isLeaf:'',
					type:''
				},
		        formLabelWidth: '180px',
			}
		},
		methods:{
			onSubmit() {
	     		this.resource.page = 1;
	       		this.getData();
	      	},
			getData(){
				var loding = this.$loading({ fullscreen: true });
				this.$axios.get(this.$root.splitUrlByGet('queryResource',this.resource),{
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
	               this.resource.rows = size;
	               this.getData();
	        },
	        handleCurrentChange: function(page){
	                this.resource.page = page;
	                this.getData();
	        },
	        deleteResource(row) {
	       	 	var loding = this.$loading({ fullscreen: true });
	       	 	this.$axios.post(this.$root.baseUrl+"deleteResource",{id:row.id},{
		        	headers:{
						'Content-Type': 'application/json',
						'x-phone-header-id':this.$root.token,
						'X-XSRF-TOKEN':this.$root.getCookie("XSRF-TOKEN")
					}
		        }).then((res) => {
		        	loding.close();
		       		this.handleClose();
		        	this.getData();
		        	this.$notify({
				          message: res.data.message,
				          type: 'success'
				    });
		        }, (res) => {
		        	loding.close();
		        	this.$notify({
				          message: "无权限操作",
				          type: 'error'
				    });
		        })
	      	},
			handleClose(){
				this.dialogVisible = false;
				for (var e in this.insertResource) {
		    		this.insertResource[e] = "";
		    	}
			},
			addResource(){
				var loding = this.$loading({ fullscreen: true });
				this.$axios.post(this.$root.baseUrl+"insertResource",this.insertResource,{
		        	headers:{
						'Content-Type': 'application/json',
						'x-phone-header-id':this.$root.token,
						'X-XSRF-TOKEN':this.$root.getCookie("XSRF-TOKEN")
					}
		        }).then((res) => {
		        	loding.close();
		       		this.handleClose();
		        	this.getData();
		        	this.$notify({
				          message: res.data.message,
				          type: 'success'
				    });
		        }, (res) => {
		        	loding.close();
		        	this.$notify({
				          message: "无权限操作",
				          type: 'error'
				    });
		        })
			}
		},
		created(){
	    	this.getData();
	    }
	}
</script>

<style scoped="scoped">
	  .button-new-tag {
	    margin-left: 10px;
	    height: 32px;
	    line-height: 30px;
	    padding-top: 0;
	    padding-bottom: 0;
	  }
	  .input-new-tag {
	    width: 90px;
	    margin-left: 10px;
	    vertical-align: bottom;
	  }
</style>