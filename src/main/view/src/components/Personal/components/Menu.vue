<template>
  <div class="menu" :style="isCollapse && 'width: 68px;'">
    <el-menu
        :collapse="isCollapse"
        :default-active="activeMenu"
        class="el-menu-vertical"
        @open="handleOpen"
        @close="handleClose"
        background-color="#fff"
        text-color="#666666"
        active-text-color="#409EFF">
      <template v-for="menu in menuList">
        <el-submenu v-if="menu.menusLv2" :index="menu.url">
          <template slot="title">
            <i class="iconfont" :class="menu.icon"></i>
            <span>{{ menu.menuName }}</span>
          </template>
          <template v-for="submenu in menu.menusLv2">
            <router-link :to="submenu.url">
              <el-menu-item :index="submenu.url">{{ submenu.menuName }}</el-menu-item>
            </router-link>
          </template>
        </el-submenu>
        <router-link v-else :to="menu.url">
          <el-menu-item :index="menu.url">{{ menu.menuName }}</el-menu-item>
        </router-link>
      </template>
    </el-menu>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import { getIsAdmin } from '@/utils/auth';
export default {
  name: "Menu",
  data() {
    return {
      /*menuList: [
        {
          index: '0',
          label: '工作台',
          icon: 'icon-gongzuotai',
          submenu: [
            {
              index: '/personal/index',
              label: '首页',
              link: ''
            }
          ]
        },
        {
          index: '1',
          label: '我的课程',
          icon: 'icon-wodekecheng',
          submenu: [
            {
              index: '/personal/exercise',
              label: '题目练习',
              link: ''
            },
            {
              index: '/personal/mistakes',
              label: '错题复习',
              link: ''
            },
            {
              index: '/personal/collect',
              label: '题目收藏夹',
              link: ''
            },
            {
              index: '/personal/order',
              label: '顺序练题'
            },
            {
              index: '/examination',
              label: '模拟考试'
            },
            {
              index: '/search',
              label: '题目搜索'
            },
            {
              index: '/video',
              label: '培训视频'
            }
          ]
        },
        {
          index: '2',
          label: '个人中心',
          icon: 'icon-geren',
          submenu: [
            {
              index: '/personal/passwd',
              label: '修改密码'
            },
            {
              index: '/personal/material',
              label: '修改个人资料',
              link: ''
            }
          ]
        }
      ]*/
      menuList: []
    }
  },
  computed: {
    activeMenu() {
      const route = this.$route;
      const { meta, path } = route;
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu;
      }
      return path;
    },
    ...mapState({
      isCollapse: state => state.user.isCollapse,
      menus: state => state.user.menus
    })
  },
  watch: {
    menus: {
      handler(val) {
        if (val && val.length) {
          this.menuList = val
          this.menuList.map((item, index) => {
            item.submenu = item.menusLv2
            if (item.menusLv2 && item.menusLv2.length) {
              item.url = index+''
            }
            if (item.menuName === '工作台') {
              item.icon = 'icon-gongzuotai'
            } else if (item.menuName === '我的课程') {
              item.icon = 'icon-wodekecheng'
            } else if (item.menuName === '个人中心') {
              item.icon = 'icon-geren'
            }
          })
        }
      },
      deep: true,
      immediate: true
    }
  },
  created() {
    // if (getIsAdmin()) {
    //   this.menuList[0].submenu = [
    //     {
    //       index: '/personal/index',
    //       label: '首页',
    //       link: ''
    //     },
    //     {
    //       index: '/personal/topic',
    //       label: '题目列表',
    //       link: ''
    //     }
    //   ]
    // }
    // console.log(this.menus)
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped lang="less">
.menu {
  width: 220px;
  min-height: 180px;
  background: #fff;
  border-radius: 5px;
  transition: .5s;
  .el-menu-vertical {
    width: 100%;
    border-right: none;
    padding: 10px;
    box-sizing: border-box;
    .el-submenu {
      margin-bottom: 10px;
    }
    /deep/ .el-submenu__title {
      background: #FFFFFF!important;
      border-radius: 5px;
      height: 43px;
      line-height: 42px;
      padding: 0 15px!important;
      font-weight: bold;
      color: #333333!important;
      .el-submenu__icon-arrow {
        right: 10px;
      }
      .iconfont {
        color: #333333;
        margin-right: 8px;
      }
    }
    .el-menu-item {
      background: #fff!important;
      height: 45px;
      line-height: 45px;
      margin-top: 4px;
      transition: none!important;
      padding: 0 15px;
      border-radius: 3px;
      color: #777!important;
    }
    .el-menu-item.is-active {
      background: #409eff1c!important;
      border-radius: 3px;
      //border-right: 3px solid #409EFF;
    }
  }
  .menu-icon {
    margin-right: 6px;
  }
}
</style>