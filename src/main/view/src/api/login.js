import request from '@/utils/request'

// 登录方法
export function login(data) {
  return request({
    url: '/Login',
    method: 'post',
    data: data
  })
}
export function WxLogin(data) {
  return request({
    method: 'POST',
    url: '/wx/WxLogin',
    data: data
  })
}
// 注册方法
export function register(data) {
  return request({
    url: '/Register',
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/captchaImage',
    method: 'get'
  })
}

// 修改密码
export function ModifyPassword(data) {
  return request({
    url: '/ModifyPassword',
    method: 'post',
    data
  })
}
// 修改个人信息
export function ModifyProfile(data) {
  return request({
    url: '/ModifyProfile',
    method: 'post',
    data
  })
}
// 获取个人信息
export function GetProfile(data) {
  return request({
    url: '/GetProfile',
    method: 'post',
    data
  })
}
// 登录方法
export function AdminLogin(data) {
  return request({
    url: '/admin/AdminLogin',
    method: 'post',
    data: data
  })
}