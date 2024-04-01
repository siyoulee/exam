import router from './router'
import store from './store'
import { getToken } from '@/utils/auth'

const whiteList = ['/login', '/loginAdmin', '/wxLogin', '/register', '/forget/verify']
router.beforeEach((to, from, next) => {
  if (getToken()) {
    if (to.path === '/login'  || to.path === '/loginAdmin') {
      next({ path: '/' })
    } else {
      store.dispatch('GetInfo').then(() => {
        next()
      })
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      let code = ''
      if (location.href.indexOf('?code=') > -1) {
        code = location.href.split('?code=')[1];
        if (code.indexOf('&') !== -1) code = code.substring(0, code.indexOf('&'))
      }
      if (code) {
        next(`/wxLogin`)
      } else {
        next(`/login`) // 否则全部重定向到登录页
      }
    }
  }
})

router.afterEach(() => {

})
