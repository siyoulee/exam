<template>
  <div class="app-wrapper bg-f9">
    <div class="wrapper">
      <Header />
      <div class="container clearfix" style="padding-bottom: 30px;">
        <app-main />
      </div>
    </div>
    <Footer class="footer-beian" />
  </div>
</template>

<script>
import { AppMain, Header, Menu, Footer } from './components'

export default {
  name: 'Personal',
  components: {
    AppMain,
    Header,
    Menu,
    Footer
  },
  methods: {

  }
}
</script>

<style lang="less" scoped>
.app-wrapper {
  height: 100vh;
  overflow: auto;
}
.wrapper {
  min-height: 100vh;
}
.footer-beian {
  position: relative;
  margin-top: -35px;
  clear: both;
}
.container {

}
</style>
