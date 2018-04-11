import axios from 'axios'
// import { Message } from 'element-ui'

// import router from '@/router'

// create an axios instance
const service = axios.create({
  baseURL: '',  // api的base_url
  timeout: 10000        // request timeout
})

service.interceptors.request.use(config => {
  config.headers.Authorization = `Bearer ${sessionStorage.getItem('user')}` // get token from localStorage
  return config
}, error => Promise.reject(error))

service.interceptors.response.use((res) => {
  const body = res.data
  if (body.success === false) {
    // Message({ message: body.data.error, type: 'error', duration: 5 * 1000 })
    if (body.data.code === 401) {
      // 跳去登陆页
      // router.push('/logout')
      sessionStorage.removeItem('user');
      setTimeout(() => {
        window.location.href = '/';
      }, 500);
      return Promise.reject('Session time out!')
    }
    return Promise.reject(res.data)
  }
  return body
}, (error) => {
  const res = error.response
  if (res) {
    const msg = res.statusText
    // Message({ message: msg, type: 'error', duration: 5 * 1000 })
  }
  return Promise.reject(error)  
})

export default service
