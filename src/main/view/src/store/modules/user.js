import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, getUserId, setUserId, removeToken, removeIsAdmin, getIsAdmin } from '@/utils/auth'
import de from "element-ui/src/locale/lang/de";

const user = {
  state: {
    userInfo: {},
    userId: '',
    isAdmin: false,
    isCollapse: false,
    menus: []
  },

  mutations: {
    SET_USERID: (state, userId) => {
      state.userId = userId
    },
    SET_MENUS: (state, menus) => {
      state.menus = menus
      localStorage.setItem('menus', JSON.stringify(menus))
    },
    SET_ISADMIN: (state, isAdmin) => {
      state.isAdmin = isAdmin
    },
    SET_COLLAPSE: (state, isCollapse) => {
      state.isCollapse = isCollapse
    }
  },

  actions: {
    // 登录
    Login({ commit }, data) {
      if (getIsAdmin()) {
        setToken(data.token)
        setUserId(data.adminId)
        commit('SET_USERID', data.adminId)
        commit('SET_MENUS', data.menusLv1)
      } else {
        setToken(data.token)
        setUserId(data.userId)
        commit('SET_USERID', data.userId)
        commit('SET_MENUS', data.menusLv1)
      }
    },
    
    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        commit('SET_USERID', getUserId())
        resolve()
      })
    },
  
    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        commit('SET_USERID', '')
        let path = '/login'
        if (getIsAdmin()) {
          path = '/loginAdmin'
        }
        removeToken()
        removeIsAdmin()
        localStorage.removeItem('menus')
        resolve(path)
      })
    },
  }
}

export default user
