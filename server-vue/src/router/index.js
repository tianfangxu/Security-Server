import Vue from 'vue'
import Router from 'vue-router'
import login from '@/view/login'
import index from '@/view/index'
import user from '@/view/user'
import resource from '@/view/resource'
import role from '@/view/role'
import dept from '@/view/dept'
import home from '@/view/home'
import logAction from '@/view/logAction'

import H5index from '@/h5/index'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'H5index',
      component: H5index
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/index',
      name: 'index',
      component: index,
      children:[
      	{
      		path:'/index/user',
      		name:"user",
      		component: user,
      	},
      	{
      		path:'/index/dept',
      		name:"dept",
      		component: dept,
      	},
      	{
      		path:'/index/resource',
      		name:"resource",
      		component: resource,
      	},
      	{
      		path:'/index/role',
      		name:"role",
      		component: role,
      	},
      	{
      		path:'/index/home',
      		name:"home",
      		component: home,
      	},
      	{
      		path:'/index/logAction',
      		name:"logAction",
      		component: logAction,
      	}
      ]
    }
  ]
})
