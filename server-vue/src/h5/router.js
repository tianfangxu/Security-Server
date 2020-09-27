import urlconstant from '@/common/utils/urlconstant.js'
import home from '@/h5/home'
import index from '@/h5/index'
import edit from '@/h5/edit'
import message from '@/h5/message'
import self from '@/h5/self'


const routes = [
	{
		path: urlconstant.h5_lcoal_url_home,
		name: 'home',
		component: home,
		children:[
		  	{
		  		path:urlconstant.h5_lcoal_url_home_index,
			  	name:"index",
			  	component: index,
		  	},
		  	{
		  		path:urlconstant.h5_lcoal_url_home_edit,
			  	name:"edit",
			  	component: edit,
		  	},
		  	{
		  		path:urlconstant.h5_lcoal_url_home_message,
			  	name:"message",
			  	component: message,
		  	},
		  	{
		  		path:urlconstant.h5_lcoal_url_home_self,
			  	name:"self",
			  	component: self,
		  	},
		]
	}
]

export default routes;