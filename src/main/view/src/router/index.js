import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Personal from '@/components/Personal'
import Manage from '@/components/Manage'

// 公共路由
export const routes = [
  {
    path: '/redirect',
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: (resolve) => require(['@/views/redirect'], resolve)
      }
    ]
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    name: '登录'
  },
  {
    path: '/wxLogin',
    component: (resolve) => require(['@/views/wxLogin'], resolve),
    name: '微信登录'
  },
  {
    path: '/loginAdmin',
    component: (resolve) => require(['@/views/loginAdmin'], resolve),
    name: '管理员登录登录'
  },
  {
    path: '/register',
    component: (resolve) => require(['@/views/register'], resolve),
    name: '注册'
  },
  {
    path: '/forget/verify',
    component: (resolve) => require(['@/views/forget/verify'], resolve),
    name: '忘记密码-验证手机号'
  },
  {
    path: '/forget/reset',
    component: (resolve) => require(['@/views/forget/reset'], resolve),
    name: '忘记密码-重置'
  },
  {
    path: '/forget/success',
    component: (resolve) => require(['@/views/forget/success'], resolve),
    name: '忘记密码-成功'
  },
  {
    path: '/',
    redirect: 'personal/exercise',
    component: (resolve) => require(['@/views/index/index'], resolve),
    name: '首页'
  },
  {
    path: '/examination',
    component: (resolve) => require(['@/views/examination/index'], resolve),
    name: '考试'
  },
  {
    path: '/orderExamination',
    component: (resolve) => require(['@/views/examination/order'], resolve),
    name: '顺序练习'
  },
  {
    path: '/search',
    component: (resolve) => require(['@/views/examination/search'], resolve),
    name: '题目搜索'
  },
  {
    path: '/personal',
    component: Personal,
    meta: {
      keepAlive: true
    },
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/personal/index/index'], resolve),
        name: '工作台',
        meta: {}
      },
      // {
      //   path: 'topic',
      //   component: (resolve) => require(['@/views/topic/index'], resolve),
      //   name: '题目列表',
      //   meta: {}
      // },
      {
        path: 'exercise',
        component: (resolve) => require(['@/views/personal/course/exercise'], resolve),
        name: '题目练习',
        meta: {
          keepAlive: true
        }
      },
      {
        path: 'mistakes',
        component: (resolve) => require(['@/views/personal/course/mistakes'], resolve),
        name: '错题复习',
        meta: {
          keepAlive: true
        }
      },
      {
        path: 'collect',
        component: (resolve) => require(['@/views/personal/course/collect'], resolve),
        name: '题目收藏夹',
        meta: {
          keepAlive: true
        }
      },
      {
        path: 'order',
        component: (resolve) => require(['@/views/personal/course/order'], resolve),
        name: '顺序练题',
        meta: {
          keepAlive: true
        }
      },
      {
        path: 'simulation',
        component: (resolve) => require(['@/views/personal/course/simulation'], resolve),
        name: '模拟考试',
        meta: {
          keepAlive: true
        }
      },
      {
        path: 'passwd',
        component: (resolve) => require(['@/views/personal/info/passwd'], resolve),
        name: '修改密码',
        meta: {}
      },
      {
        path: 'material',
        component: (resolve) => require(['@/views/personal/info/material'], resolve),
        name: '修改个人资料',
        meta: {}
      },
      {
        path: 'video',
        component: (resolve) => require(['@/views/video/index'], resolve),
        name: '培训视频'
      }
    ]
  },
  {
    path: '/manage',
    component: Manage,
    children: [
      {
        path: 'topic',
        component: (resolve) => require(['@/views/topic/index'], resolve),
        name: '题目列表',
        meta: {}
      },
      {
        path: 'code',
        component: (resolve) => require(['@/views/code/index'], resolve),
        name: '兑换码管理',
        meta: {}
      }
    ]
  }
]

export default new Router({
  // mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({y: 0}),
  routes: routes
})
