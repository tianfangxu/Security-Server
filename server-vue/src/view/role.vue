<template>
	<div>
		<!--查询表单-->
		<el-form :inline="true" :model="roles" class="demo-form-inline" >
			<el-form-item label="编号">
				<el-input v-model="roles.roleCode" placeholder="编号"></el-input>
			</el-form-item>
			<el-form-item label="名称">
				<el-input v-model="roles.roleName" placeholder="名称"></el-input>
			</el-form-item>
			<el-form-item>
		    	<el-button type="primary" @click="onSubmit">查询</el-button>
		    	<el-button type="success" @click="dialogVisible = true">新增</el-button>
		  	</el-form-item>
		</el-form>
		
		<!--数据列表-->
		<el-table :data="tableData" style="width: 100%">
		    <el-table-column  prop="id"  label="主键"  width="80"></el-table-column>
		    <el-table-column  prop="roleCode"  label="编号"  width="180"></el-table-column>
		    <el-table-column  prop="roleName"  label="名称"  width="180"></el-table-column>
		    <el-table-column  prop="description"  label="描述"  width="400"></el-table-column>
		    <el-table-column label="新增日期" width="180">
		    	<template slot-scope='scope'>
		    		{{scope.row.createTime}} 
		    	</template>
		    </el-table-column>
		    <el-table-column  label="操作" > 
		    	<template slot-scope="scope">
			        <el-button @click="handleClick(scope.row)" type="text" size="small">查看资源</el-button>
			    </template>
		    </el-table-column>
		</el-table>
		 <!--分页栏-->
		<el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="roles.page"
            :page-sizes="[5, 10, 20, 40]" 
            :page-size="roles.rows"         
            layout="total, sizes, prev, pager, next, jumper"
            :total="tempdata.total">
       </el-pagination>
       
		<!--权限弹窗-->
        <el-dialog  title="操作权限"  :visible.sync="dialogVisible"  :before-close="handleClose"  width="600px">
		  	<el-form ref="form" :model="insertRole" label-width="80px">
			  <el-form-item label="编码">
			    <el-input v-model="insertRole.roleCode"></el-input>
			  </el-form-item>
			  <el-form-item label="名称">
			    <el-input v-model="insertRole.roleName"></el-input>
			  </el-form-item>
			  <el-form-item label="描述">
			    <el-input type="textarea" v-model="insertRole.description" :autosize="{ minRows: 4, maxRows: 8}"></el-input>
			  </el-form-item>
			</el-form>
		  	<span slot="footer" class="dialog-footer">
		    	<el-button @click="handleClose">取 消</el-button>
		    	<el-button type="primary" @click="addRole">保存</el-button>
		  	</span>
		</el-dialog>
		
		<!--资源弹窗-->
		<el-dialog  title="资源详情"  :visible.sync="dialogVisible_resource"  :before-close="handle__resource_Close" height = "50%" width="60%">
			<el-transfer 
				v-model="roel_resource_method" 
				:data="roel_resource_method_all"
				:titles="['无权限资源', '已有资源']"
				filterable
				 >
				<span slot-scope="{ option }" :title="option.label">{{ option.label }}</span>
			</el-transfer>
			<span slot="footer" class="dialog-footer">
		    	<el-button @click="handle__resource_Close">取 消</el-button>
		    	<el-button type="primary" @click="updateRoleResource">保存</el-button>
		  	</span>
		</el-dialog>
		
	</div>
</template>

<script>
	export default{
		name:"role",
		data(){
			return {
				tableData:[],
				roles:{
					roleCode:"",
					roleName:"",
					page:1,
					rows:5,
				},
				tempdata:{
					total:0,
					rows:[],
					resMenuid:[]
				},
				insertRole:{
					roleCode:"",
					roleName:"",
					description:"",
				},
				dialogVisible: false,
				dialogVisible_resource:false,
				roel_resource_method:[],
				roel_resource_method_all:[],
				roel_resource_roleId:null,
				roel_resource_old:[]
			}
		},
		methods:{
			onSubmit(){
				this.roles.page = 1;
	       		this.getData();
			},
			getData(){
				var loding = this.$loading({ fullscreen: true });
	        	this.$axios.get(this.$root.splitUrlByGet('queryRole',this.roles),{
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
			addRole(){
				var loding = this.$loading({ fullscreen: true });
				this.$axios.post(this.$root.baseUrl+"insertRole",this.insertRole,{
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
			handleSizeChange: function (size) {
	               this.roles.rows = size;
	        },
	        handleCurrentChange: function(page){
	                this.roles.page = page;
	        },
	        handleClose(){
	        	this.dialogVisible = false;
	        	for (var e in this.insertRole) {
		    		this.insertRole[e] = "";
		    	}
	        },
			handleClick(rows){
				var loding = this.$loading({ fullscreen: true });
				this.$axios.get(this.$root.splitUrlByGet('queryRoleResource',{id:rows.id}),{
		        	headers:{
						'Content-Type': 'application/json',
						'x-phone-header-id':this.$root.token,
						'X-XSRF-TOKEN':this.$root.getCookie("XSRF-TOKEN")
					}
		        }).then((res) => {
		        	loding.close();
		       		this.roel_resource_method = res.data.result.list;
		       		this.roel_resource_method_all = res.data.result.maps;
		       		this.roel_resource_roleId=rows.id;
		       		this.roel_resource_old=res.data.result.list;
		        }, (res) => {
		        	loding.close();
		        	this.$notify({
				          message: "无权限操作",
				          type: 'error'
				    });
		        })
				
				this.dialogVisible_resource = true;
				
			},
	        handle__resource_Close(){
	        	this.dialogVisible_resource = false;
	        },
	        updateRoleResource(){
	        	var data_add = [];
	        	for(var i in this.roel_resource_method){
	        		var index;
	        		if( (index=this.roel_resource_old.indexOf(this.roel_resource_method[i])) == -1){
	        			data_add.push({roleId:this.roel_resource_roleId,resourceId:this.roel_resource_method[i]});
	        			
	        		}else{
	        			this.roel_resource_old.splice(index, 1);
	        		}
	        	}
	        	var data_del = [];
	        	for(var i in this.roel_resource_old){
	        		data_del.push({roleId:this.roel_resource_roleId,resourceId:this.roel_resource_old[i]});
	        	}
	        	
	        	
	        	//修改
	        	var loding = this.$loading({ fullscreen: true });
				this.$axios.post(this.$root.baseUrl+"batchUpdateRoleResource",{add:data_add,del:data_del},{
		        	headers:{
						'Content-Type': 'application/json',
						'x-phone-header-id':this.$root.token,
						'X-XSRF-TOKEN':this.$root.getCookie("XSRF-TOKEN")
					}
		        }).then((res) => {
		        	loding.close();
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
	        	this.dialogVisible_resource = false;
	        }
	        
		},
		created(){
			this.getData();
		}
	}
</script>

<style>
</style>