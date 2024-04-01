import Vue from 'vue'

import App from './App.vue'
import store from './store'
import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import less from 'less'

import './assets/styles/base.css'
import './assets/styles/index.less'

import './permission'

Vue.config.productionTip = false

Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgWarning = function (msg) {
  this.$message({ showClose: true, message: msg, type: "warning" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

import Pagination from "@/components/Pagination"
Vue.component('Pagination', Pagination)

Vue.use(ElementUI)
Vue.use(less)

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
