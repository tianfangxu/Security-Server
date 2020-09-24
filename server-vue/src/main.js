// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import StringFilter from './common/filter/StringFilter';

//全局过滤器
Object.keys(StringFilter).forEach(key => {
	Vue.filter(key, StringFilter[key])
})


Vue.use(ElementUI);
Vue.prototype.$axios = axios
axios.defaults.withCredentials = true
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  data:{
  	baseUrl:"http://localhost:32001/",
  	token:'',
  	info:{}
  },
  methods:{
  	islogin(){
  		if(this.$data.token == null || this.$data.token == ''){
  			window.location.href="#/login"
  		}
  	},
  	randomstr(){	//生产随机字符串
  		return Math.random().toString(36).substr(7);
  	},
  	splitUrlByGet(url,data){
  		if(url.startsWith("/")){
  			url = url.substring(1,url.length)
  		}
  		var param = "";
  		for(var d in data){
  			param += "&"+d+"="+data[d];
  		}
  		param = param.substring(1,param.length);
  		return this.$data.baseUrl+url+"?"+param;
  	},
  	getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
          var c = ca[i];
          while (c.charAt(0) == ' ') c = c.substring(1);
          if (c.indexOf(name) != -1){
            return c.substring(name.length, c.length);
          }
        }
        return "";
      }
  }
})
