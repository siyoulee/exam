import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
const IsAdminKey = 'Is-Admin'
const UserId = 'User-Id'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function getUserId() {
  return Cookies.get(UserId)
}

export function setUserId(userId) {
  return Cookies.set(UserId, userId)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getIsAdmin() {
  return Cookies.get(IsAdminKey)
}

export function setIsAdmin(isAdmin) {
  return Cookies.set(IsAdminKey, isAdmin)
}

export function removeIsAdmin() {
  return Cookies.remove(IsAdminKey)
}