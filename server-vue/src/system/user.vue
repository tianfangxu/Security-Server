<template>
	<div>
		<!--查询表单-->
		<el-form :inline="true" :model="users" class="demo-form-inline">
		  <el-form-item label="账号">
		    <el-input v-model="users.username" placeholder="用户名"></el-input>
		  </el-form-item>
		  <el-form-item label="姓名">
		    <el-input v-model="users.realName" placeholder="姓名"></el-input>
		  </el-form-item>
		  <el-form-item label="性别">
		     <el-select v-model="users.sex" placeholder="性别">
		     	<el-option label="请选择" value=""></el-option>
		     	<el-option label="男" value="1"></el-option>
		      	<el-option label="女" value="0"></el-option>
		    </el-select>
		  </el-form-item>
		  <el-form-item label="创建日期">
		  	<el-date-picker
		      v-model="users.startTime" value-format="yyyy-MM-dd HH:mm:ss" 
		      type="datetime" placeholder="选择开始时间">
		   </el-date-picker>
		  
		    <el-date-picker
		      v-model="users.endTime" value-format="yyyy-MM-dd HH:mm:ss" 
		      type="datetime" placeholder="选择结束时间">
		    </el-date-picker>
		  </el-form-item>
		  <el-form-item>
		    <el-button type="primary" @click="onSubmit">查询</el-button>
		    <el-button type="success" @click="dialogVisible = true">新增</el-button>
		  </el-form-item>
		</el-form>
		
		<!--数据列表-->
		<el-table :data="tableData" style="width: 100%">
			<el-table-column  prop="id"  label="主键"  width="80"></el-table-column>
			<el-table-column prop="realName" label="姓名" width="180"> </el-table-column>
		    <el-table-column  prop="username"  label="账号"  width="180"></el-table-column>
		    <el-table-column prop="password"  label="密码"  width="180"></el-table-column>
		    <el-table-column prop="avatar" label="头像" width="100"> </el-table-column>
		    <el-table-column prop="phone" label="电话" width="180"> </el-table-column>
		    <el-table-column prop="email" label="邮箱" width="180"> </el-table-column>
		    <el-table-column prop="sex" label="性别" width="80"> </el-table-column>
		    <el-table-column prop="locked" label="是否锁定" width="80"> </el-table-column>
		    <el-table-column label="注册日期" width="180">
		    	<template slot-scope='scope'>
		    		{{scope.row.createTime}} 
		    	</template>
		    </el-table-column>
		    <el-table-column  label="操作" > 
		    	<template slot-scope="scope">
			        <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
			        <el-popconfirm  title="确认是该用户重制密码吗？" @onConfirm= "resetPassword(scope.row.id)">
			        	<el-button slot="reference" type="text" size="small">重制密码</el-button>
			        </el-popconfirm>
			        <!--<el-popconfirm  title="确认将该用户删除吗？">
			        	<el-button slot="reference" type="text" size="small">删除</el-button>
			        </el-popconfirm>-->
			    </template>
		    </el-table-column>
		</el-table>
		 
		 <!--分页栏-->
		<el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="users.page"
            :page-sizes="[5, 10, 20, 40]" 
            :page-size="users.rows"         
            layout="total, sizes, prev, pager, next, jumper"
            :total="tempdata.total">
        </el-pagination>
        
        <!--弹窗-->
        <el-dialog  title="新增用户"  :visible.sync="dialogVisible"  width="600px"  :before-close="handleClose">
		  	<el-form ref="form" :model="insertDatas" label-width="80px">
			  <el-form-item label="用户姓名">
			    <el-input v-model="insertDatas.realName"></el-input>
			  </el-form-item>
			  <el-form-item label="登陆账号">
			    <el-input v-model="insertDatas.username"></el-input>
			  </el-form-item>
			  <el-form-item label="性别">
			    <el-select v-model="insertDatas.sex" placeholder="请选择">
			      <el-option label="男" value="1"></el-option>
			      <el-option label="女" value="0"></el-option>
			    </el-select>
			  </el-form-item>
			  <el-form-item label="电话">
			    <el-input v-model="insertDatas.phone"></el-input>
			  </el-form-item>
			  <el-form-item label="邮箱">
			    <el-input v-model="insertDatas.email"></el-input>
			  </el-form-item>
			  <el-form-item label="是否锁定">
			    <el-switch v-model="insertDatas.locked"></el-switch>
			  </el-form-item>
			</el-form>
		  	<span slot="footer" class="dialog-footer">
		    	<el-button @click="handleClose">取 消</el-button>
		    	<el-button type="primary" @click="addUser">确 定</el-button>
		  	</span>
		</el-dialog>
		
		<!--用户详情-->
		<el-dialog  title="用户详情"  :visible.sync="dialogVisible_userinfo"  width="600px"  :before-close="handle_userinfo_Close">
			<el-form ref="form" :model="updateDatas" label-width="80px">
				<el-form-item label="用户姓名">
			    	<el-input v-model="updateDatas.realName"></el-input>
			 	</el-form-item>
			  	<el-form-item label="登陆账号">
			    	<el-input v-model="updateDatas.username"></el-input>
			  	</el-form-item>
			  	<el-form-item label="性别">
				    <el-select v-model="updateDatas.sex" placeholder="请选择">
				      <el-option label="男" value="1"></el-option>
				      <el-option label="女" value="0"></el-option>
				    </el-select>
			  	</el-form-item>
			  	<el-form-item label="电话">
			    	<el-input v-model="updateDatas.phone"></el-input>
			  	</el-form-item>
			  	<el-form-item label="邮箱">
			    	<el-input v-model="updateDatas.email"></el-input>
			  	</el-form-item>
			  	<el-form-item label="是否锁定">
			    	<el-switch v-model="updateDatas.locked"></el-switch>
			  	</el-form-item>
			  	<el-form-item label="绑定角色">
			    	<el-select v-model="updateDatas.roleIds" multiple placeholder="请选择" size='medium' >
					    <el-option
					      v-for="item in roleList"
					      :key="item.value"
					      :label="item.label"
					      :value="item.value">
					    </el-option>
				  	</el-select>
			  	</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
		    	<el-button @click="handle_userinfo_Close">取 消</el-button>
		    	<el-button type="primary" @click="updateUser">确 定</el-button>
		  	</span>
		</el-dialog>
	</div>
</template>

<script>
	export default{
		name:'user',
		data() {
		    return {
	            dialogVisible:false,
	            dialogVisible_userinfo:false,
		        users: {
		         	username: '',
		         	realName: '',
		         	sex: '',
		         	startTime:'',
		         	endTime:'',
		          	page:1, //初始页
	            	rows:5, //每页的数据
		        },
		        tableData: [],
		        tempdata:{
		        	total:0,
		        },
		        insertDatas: {
		          realName: '',
		          username: '',
		          sex: '',
		          phone: '',
		          email: '',
		          locked: false
		        },
		        updateDatas:{
		        	id:"",
		        	realName: '',
		          	username: '',
		          	sex: '',
		          	phone: '',
		          	email: '',
		          	locked: false,
		          	roleIds:""
		        },
		        roleList:[]
		    }
	    },
	    methods: {
	     	onSubmit() {
	     		this.users.page = 1;
	       		this.getData();
	      	},
	      	handleClick(rows) {
	       	 	this.dialogVisible_userinfo = true;
	       	 	this.updateDatas.id = rows.id;
	       	 	this.updateDatas.realName = rows.realName;
	       	 	this.updateDatas.username = rows.username;
	       	 	this.updateDatas.sex = rows.sex == '男'?'1':'0';
	       	 	this.updateDatas.phone = rows.phone;
	       	 	this.updateDatas.email = rows.email;
	       	 	
	       	 	this.updateDatas.locked = (rows.locked != '否');
	       	 	var loding = this.$loading({ fullscreen: true });
	       	 	this.$axios.get(this.$root.splitUrlByGet('queryRoleByUserId',{id:rows.id}),{
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
		        	this.roleList = res.data.result.roleAllList;
		        	this.updateDatas.roleIds = [];
		        	for (var i in res.data.result.roles) {
		        		this.updateDatas.roleIds.push(res.data.result.roles[i].id);
		        	}
		        }, (res) => {
		        	loding.close();
		        	this.$message({
				          message: "服务维护中...",
				          type: 'error'
				    });
		        })
	       	 	
	      	},
	        handleSizeChange: function (size) {
	               this.users.rows = size;
	               this.getData();
	        },
	        handleCurrentChange: function(page){
	                this.users.page = page;
	                this.getData();
	        },
	        getData(){
	        	var loding = this.$loading({ fullscreen: true });
	        	this.$axios.get(this.$root.splitUrlByGet('queryUser',this.users),{
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
	        handleClose() {
	        	this.clearUserForm();
		       	this.dialogVisible = false;
		    },
		    clearUserForm(){
		    	for (var e in this.insertDatas) {
		    		this.insertDatas[e] = "";
		    	}
		    	this.insertDatas.locked = false;
		    },
		    addUser(){
		    	var loding = this.$loading({ fullscreen: true });
		    	var data = this.insertDatas;
		    	data.locked = data.locked?"1":"0";
		    	//校验字段
		    	for (var e in data) {
		    		if(data[e] == null || data[e] == ''){
		    			var msg = "";
		    			switch(e){
		    				case 'username':
		    					msg = "登陆账号";
		    					break;
		    				case 'realName':
		    					msg = "姓名";
		    					break;
		    				case 'sex':
		    					msg = "性别";
		    					break;
		    				case 'phone':
		    					msg = "手机";
		    					break;
		    				case 'email':
		    					msg = "邮箱";
		    					break;
		    			}
		    			loding.close();
		    			this.$notify({
				          title: '警告',
				          message: msg+'不能为空！！',
				          type: 'warning'
				        });
		    			return;
		    		}
		    	}
	        	this.$axios.post(this.$root.baseUrl+"insertUser",data,{
		        	headers:{
						'Content-Type': 'application/json',
						'x-phone-header-id':this.$root.token,
						'X-XSRF-TOKEN':this.$root.getCookie("XSRF-TOKEN")
					},
					withCredentials: true
		        }).then((res) => {
		        	loding.close();
		        	this.clearUserForm();
		       		this.dialogVisible = false;
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
		    handle_userinfo_Close(){
		    	this.dialogVisible_userinfo = false;
		    },
		    updateUser(){
		    	var loding = this.$loading({ fullscreen: true });
		    	var data = this.updateDatas;
		    	data.locked = data.locked?1:0;
		    	this.$axios.post(this.$root.baseUrl+"updateUser",data,{
		        	headers:{
						'Content-Type': 'application/json',
						'x-phone-header-id':this.$root.token,
						'X-XSRF-TOKEN':this.$root.getCookie("XSRF-TOKEN")
					},
					withCredentials: true
		        }).then((res) => {
		        	loding.close();
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
		        });
		        this.dialogVisible_userinfo = false;
		    },
		    resetPassword(id){
		    	var loding = this.$loading({ fullscreen: true });
		    	this.$axios.post(this.$root.baseUrl+"resetPassword",{id:id},{
		        	headers:{
						'Content-Type': 'application/json',
						'x-phone-header-id':this.$root.token,
						'X-XSRF-TOKEN':this.$root.getCookie("XSRF-TOKEN")
					},
					withCredentials: true
		        }).then((res) => {
		        	loding.close();
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
		        });
		    }
	    },
	    created(){
	    	this.getData();
	    }
	}
</script>

<style scoped="scoped">
	.el-pagination{
		margin-top: 20px;
	}
</style>