import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import VueRouter from 'vue-router'
import store from './vuex/store'
import Vuex from 'vuex'
import routes from './routes'
// import Mock from './mock'
// Mock.bootstrap();
import 'font-awesome/css/font-awesome.min.css'
import QRCode from 'qrcodejs2'
Vue.prototype.QRCode=QRCode;

Vue.filter('time',function(value){
  if(value==null){
    return '无'
  }else{
      function timestampToTime(timestamp) {
        var date = new Date(timestamp),
        Y = date.getFullYear() ,
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) ,
        D = date.getDate() ,
        h = date.getHours()<10?'0' +date.getHours():date.getHours(),
        m = date.getMinutes()<10?'0' +date.getMinutes():date.getMinutes(),
        s = date.getSeconds()<10?'0' +date.getSeconds():date.getSeconds();
        return Y+'-'+M+'-'+D+' '+h+':'+m+':'+s;
      }
    // console.log(new Date(value))
    let time=timestampToTime(value)
    return time;
  }
    
})


import axios from 'axios';
axios.interceptors.request.use((config)=>{
  config.headers.Authorization = `Bearer ${sessionStorage.getItem('user')}`;
  // console.log(config)
  // config.responseType='blob'
  return config;
},(error)=>{
  console.log(error)
  return Promise.reject(err)
}

)
axios.interceptors.response.use((res)=>{
  // console.log(res)
  if(!res.data.data){
    return res;
  }else{
    if(res.data.data.code==401){
      sessionStorage.removeItem('user');
      window.location='/'
    }
  }
    
  
  
  return res;
  
},(err)=>{
  console.log(err)
  return Promise.reject(err)
})
Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(Vuex)
let instance = axios.create({
  headers: {'Authorization': `Bearer ${sessionStorage.getItem('user')}`},
  responseType:'blob'
});
Vue.prototype.$ajax=instance;
//NProgress.configure({ showSpinner: false });

const router = new VueRouter({
  routes,
	mode:"history"
})

router.beforeEach((to, from, next) => {
  document.title=to.name
  //NProgress.start();
  if (to.path == '/login') {
    sessionStorage.removeItem('user');
  }
  let user = sessionStorage.getItem('user');
  if (!user && to.path != '/login') {
    next({ path: '/login' })
  } else {
    next()
  }
})

//router.afterEach(transition => {
//NProgress.done();
//});

new Vue({
  //el: '#app',
  //template: '<App/>',
  router,
  store,
  //components: { App }
  render: h => h(App)
}).$mount('#app')

