import urlconstant from '@/common/utils/urlconstant.js'
import login from '@/system/login'
import index from '@/system/index'
import user from '@/system/user'
import resource from '@/system/resource'
import role from '@/system/role'
import dept from '@/system/dept'
import home from '@/system/home'
import logAction from '@/system/logAction'


const routes = [
	{
	  path: urlconstant.base_local_url_login,
	  name: 'login',
	  component: login
	},
	{
	  path: urlconstant.base_local_url_system,
	  name: 'system',
	  component: index,
	  children:[
	  	{
	  		path:urlconstant.base_local_url_system_user,
	  		name:"user",
	  		component: user,
	  	},
	  	{
	  		path:urlconstant.base_local_url_system_dept,
	  		name:"dept",
	  		component: dept,
	  	},
	  	{
	  		path:urlconstant.base_local_url_system_resource,
	  		name:"resource",
	  		component: resource,
	  	},
	  	{
	  		path:urlconstant.base_local_url_system_role,
	  		name:"role",
	  		component: role,
	  	},
	  	{
	  		path:urlconstant.base_local_url_system_home,
	  		name:"home",
	  		component: home,
	  	},
	  	{
	  		path:urlconstant.base_local_url_system_logAction,
	  		name:"logAction",
	  		component: logAction,
	  	}
	  ]
	}
]

export default routes;