import Vue from 'vue'
import Router from 'vue-router'
import system_router from './system/router.js'
import h5_router from './h5/router.js'

Vue.use(Router)

/**
 * 统一路由管理
 */
const routes = [
	...system_router,
	...h5_router
]

var router =  new Router({
    routes
});
export default router;