// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui'
import axios from 'axios';
// 使用form-create的组件
import FcDesigner from '@form-create/designer';
import ELEMENT from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 使用 Element UI
Vue.use(ELEMENT);
// 使用 form-create 和 designer
Vue.use(FcDesigner);
Vue.use(FcDesigner.formCreate);
//配置跨域前置
// axios.defaults.baseURL = '/api' // 开发本地代理
// axios.defaults.headers.post['Contenst-Type'] = 'application/json;'
// axios.defaults.headers.common['token'] = store.state.token
// 全局配axios
Vue.prototype.$axios = axios;
Vue.config.productionTip = false
Vue.use(ElementUI);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
