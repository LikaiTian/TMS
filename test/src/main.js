// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElemenrUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'
//https://www.iconfont.cn/
import './assets/TestIcon/iconfont.css'
//导入axios 跨域 拦截器
import axios from 'axios'
import echarts from 'echarts'
//时间插件
import { format } from 'date-fns'
//导出excel
import Blob from '@/excel/Blob.js'
import Export2Excel from '@/excel/Export2Excel.js'
//引入moment
import moment from "moment"
//设置访问根路
// 本地api
// axios.defaults.baseURL = "http://localhost:9000/"
// 服务器
axios.defaults.baseURL = "http://1.15.24.31:8082/"
Vue.use(ElemenrUI)
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
//挂载axios
Vue.prototype.$http = axios

// axios.defaults.baseURL = "/apis"

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
