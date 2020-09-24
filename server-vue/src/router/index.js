import Vue from 'vue'
import Router from 'vue-router'
import login from '@/system/login'
import index from '@/system/index'
import user from '@/system/user'
import resource from '@/system/resource'
import role from '@/system/role'
import dept from '@/system/dept'
import home from '@/system/home'
import logAction from '@/system/logAction'

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
