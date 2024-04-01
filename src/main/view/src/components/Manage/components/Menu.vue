<template>
  <el-menu
      mode="horizontal"
      :default-active="activeMenu"
      @open="handleOpen"
      @close="handleClose"
      text-color="#eee"
      background-color="#409EFF"
      active-text-color="#fff">
    <template v-for="menu in menuList">
      <el-menu-item :index="menu.url">
        <router-link :to="menu.url">{{ menu.label }}</router-link>
      </el-menu-item>
    </template>
  </el-menu>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: "Menu",
  data() {
    return {
      menuList: [
        {
          index: '0',
          label: '题目管理',
          url: '/manage/topic'
        },
        {
          index: '1',
          label: '兑换码管理',
          url: '/manage/code'
        }
      ]
    }
  },
  computed: {
    activeMenu() {
      const route = this.$route;
      const { meta, path } = route;
      if (meta.activeMenu) {
        return meta.activeMenu;
      }
      return path;
    }
  },
  created() {
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
.el-menu {
  background: transparent;
  width: 100%;
  border-right: none;
  box-sizing: border-box;
  display: flex;
  .el-menu-item {
    height: 80px;
    line-height: 80px;
    padding: 0;
    a {
      display: inline-block;
      padding: 0 20px;
    }
  }
  .el-menu-item.is-active {

  }
}
.menu-icon {
  margin-right: 6px;
}
</style>