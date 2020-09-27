# looport

> looport

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

# 相关文件说明
	1.system文件夹下为系统管理页面。里面所有的接口都需要权限访问，且访问后台接口需要带上token和xss-token
	2.h5文件夹下为手机端访问页面，
	3.本项目所有的路由地址请统一写再/src/utils/urlconstant.js下