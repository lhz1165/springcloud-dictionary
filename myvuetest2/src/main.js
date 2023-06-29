import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'


Vue.use(ElementUI);

Vue.config.productionTip = false
Vue.prototype.axios=axios


router.beforeEach((to,from,next)=>{
  let isLogin = window.localStorage.getItem("isLogin");
  if (to.path==='/logout'){
    window.localStorage.clear();
    next('/login')
  }else if(to.path==='/login'){
    if (isLogin != null) {
      next('/index')
    }
  }else if(isLogin===null){
    next('/login')
  }
  next();
})
new Vue({
  router,
  // store,
  render: h => h(App)
}).$mount('#app')
