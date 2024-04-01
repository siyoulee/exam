<template>
  <div class="header-wrap mb20">
    <div class="container clearfix">
      <div class="header">
        <h1 style="font-family: MicrosoftYaHei-Bold;font-size: 32px;">
          <span>CTBOK</span>
          <i class="iconfont icon-zhankai collapse" :class="!isCollapse && 'active'" @click="handleCollapse"></i>
        </h1>
        <div class="header-user clearfix">
          <div class="header-message fl">
            <img src="../../../assets/personal_images/user.png" alt="">
            <!--          <span class="header-message-text">2</span>-->
          </div>
          <span class="header-user-text fl">你好！{{ form.username }}</span>
          <i @click="logout" style="margin-left: 5px;cursor: pointer;font-size: 20px;" class="iconfont icon-tuichu"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import {GetProfile} from "../../../api/login";
import {mapState} from "vuex";

export default {
  data() {
    return {
      form: {}
    }
  },
  components: {

  },
  computed: {
    path() {
      return this.$route.path;
    },
    ...mapState({
      isCollapse: state => state.user.isCollapse
    })
  },
  created() {
    this.init();
  },
  methods: {
    handleCollapse() {
      this.$store.commit('SET_COLLAPSE', !this.isCollapse);
    },
    init() {
      GetProfile({
        userId: this.$store.state.user.userId
      }).then(res => {
        this.form = res;
        if (!this.form.menusLv1 || this.form.menusLv1.length === 0) {
          this.$store.commit('SET_MENUS', JSON.parse(localStorage.getItem('menus')));
        } else {
          this.$store.commit('SET_MENUS', this.form.menusLv1);
        }
      })
    },
    logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then((path) => {
          this.$router.push(path);
        })
      }).catch(() => {});
    }
  }
}
</script>

<style lang="less" scoped>
.header-wrap {
  background: #409EFF;
  color: #fff;
}
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 80px;
  h1 {
    display: flex;
    align-items: center;
    .collapse {
      font-size: 22px;
      margin-left: 20px;
      cursor: pointer;
      &.active {
        transform: rotate(180deg);
      }
    }
  }
  .header-user {
    display: flex;
    align-items: center;
  }
  .header-message {
    display: inline-block;
    position: relative;
    img {
      height: 40px;
    }
    .header-message-text {
      position: absolute;
      top: -4px;
      right: -4px;
      display: inline-block;
      line-height: 15px;
      background: #FFFFFF;
      border-radius: 50%;
      font-weight: bold;
      color: #409EFF;
      font-size: 12px;
      padding: 0 4px;
    }
  }
  .header-user-text {
    font-size: 16px;
    margin-right: 20px;
    line-height: 22px;
    margin-left: 10px;
  }
}
</style>
