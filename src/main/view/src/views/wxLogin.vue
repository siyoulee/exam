<template>
  <div class="login">
  </div>
</template>

<script>
import { WxLogin } from '@/api/login';
export default {
  name: 'login',
  data() {
    return {
      form: {},
      rules: {
        userName: [
          { required: true, message: "请输入账号", trigger: "change" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "change" }
        ]
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      let code = ''
      if (location.href.indexOf('?code=') > -1) {
        code = location.href.split('?code=')[1];
        if (code.indexOf('&') !== -1) code = code.substring(0, code.indexOf('&'))
        history.pushState({}, '', '/#' + this.$route.path);
      }
      if (code) {
        WxLogin({
          types: 2,
          code: code
        }).then(res => {
          if (res.token) {
            this.$store.dispatch('Login', res);
            this.msgSuccess('登录成功！');
            this.$router.push('/personal/index');
          } else {
            this.msgError('登录失败！');
            this.$router.push('/login');
          }
        }).catch(() => {
          this.$router.push('/login');
        })
      } else {
        this.msgError('code不存在！');
        this.$router.push('/login');
      }
    }
  }
}
</script>

<style lang="less">

</style>
