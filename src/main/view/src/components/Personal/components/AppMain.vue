<template>
  <section class="personal-main" :style="{background: (key === '/personal/index' || key === '/personal/topic') ? 'none' : '#FFFFFF', width: isCollapse ? 'calc(100% - 80px)' : 'calc(100% - 240px)'}">
<!--    <keep-alive :include="cachedViews">-->
<!--      <router-view :key="key" />-->
<!--    </keep-alive>-->
    <keep-alive>
      <!-- 需要缓存的视图组件 -->
      <router-view v-if="$route.meta.keepAlive"></router-view>
    </keep-alive>
    <!-- 不需要缓存的视图组件 -->
    <router-view v-if="!$route.meta.keepAlive"></router-view>
  </section>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: 'AppMain',
  computed: {
    cachedViews() {
      return ['题目练习']
    },
    key() {
      return this.$route.path
    },
    ...mapState({
      isCollapse: state => state.user.isCollapse
    })
  }
}
</script>

<style lang="less">
.personal-main {
  width: calc(100% - 240px);
  background: #FFFFFF;
  border-radius: 5px;
  min-height: 100px;
  transition: .5s;
  .personal-title {
    font-size: 16px;
    font-weight: bold;
    color: #333333;
    line-height: 60px;
    border-bottom: 1px solid #f5f7fb;
    padding: 0 40px;
    margin-bottom: 1px;
    position: relative;
    &:before {
      content: '';
      display: inline-block;
      position: absolute;
      background: #409EFF;
      width: 3px;
      height: 16px;
      left: 28px;
      top: 22px;
      border-radius: 2px;
    }
  }
}
</style>
