<template>
  <div class="login-bg">
    <div class="login-container clearfix">
      <div class="login-text fl">
        <h2>CTBOK</h2>
        <p>CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。</p>
        <p>CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。</p>
        <img src="../assets/images/login-bg.png" alt="">
      </div>
      <div class="fr login-right">
        <div class="login-wrap">
          <div class="login-content">
            <!--登录-->
            <div class="tab">
              <h2 :class="tabIndex === 0 && 'active'" @click="handleChange(0)">扫码登录</h2>
              <h2 :class="tabIndex === 1 && 'active'" @click="handleChange(1)">账号登录</h2>
            </div>
            <div v-show="tabIndex === 0">
              <div id="login_container"></div>
            </div>
            <el-form v-show="tabIndex === 1" ref="form" :model="form" :rules="rules" class="mt20">
              <el-form-item prop="userName">
                <el-input v-model="form.userName" placeholder="手机号" prefix-icon="el-icon-user" />
              </el-form-item>
              <el-form-item prop="password">
                <el-input type="password" v-model="form.password" placeholder="请输入密码" prefix-icon="el-icon-lock" @keyup.enter.native="submitForm" />
              </el-form-item>
              <div style="display: flex;justify-content: space-between;margin-bottom: 30px;">
                <div @click="handleForget" style="cursor: pointer;color: #666;">找回密码</div>
<!--                <router-link to="/register" style="cursor: pointer;color: #2771ea;">注册帐号</router-link>-->
              </div>
              <el-form-item>
                <el-button type="primary" @click="submitForm" class="mt15 login-btn" :loading="isLoading">{{isLoading ? '登录中...' : '登录'}}</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
    <p class="filing"><a href="http://beian.miit.gov.cn" target="_blank">粤ICP备2022014929号</a></p>
  </div>
</template>

<script>
import { login } from '@/api/login';
export default {
  name: 'login',
  data() {
    return {
      tabIndex: 0,
      form: {},
      rules: {
        userName: [
          { required: true, message: "请输入账号", trigger: "change" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "change" }
        ]
      },
      isLoading: false
    }
  },
  mounted() {
    this.WxLogin()
  },
  methods: {
    WxLogin() {
      const wxLogin = new WxLogin({
        id: 'login_container',
        appid: 'wx79a0478372a51c08',
        scope: 'snsapi_login',
        redirect_uri: encodeURI('https://ctbok.cn/index.html#/wxLogin')
      });
    },
    handleChange(index) {
      this.tabIndex = index
    },
    handleForget() {
      this.$alert('功能正在实现，请微信联系：CTBOK_SIYOU', '提示', {
        confirmButtonText: '确定'
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.isLoading = true
          login({
            mobile: this.form.userName,
            password: this.form.password
          }).then(res => {
            if (res.loginStatus === '1') {
              this.$store.dispatch('Login', res);
              this.msgSuccess('登录成功！');
              this.$router.push('/personal/index');
            } else {
              this.msgError('登录失败，请检查账号密码是否正确！');
            }
          }).finally(() => {
            this.isLoading = false
          })
        }
      });
    }
  }
}
</script>

<style scoped lang="less">
#login_container {
  width: 300px;
  margin: 0 auto;
}
.tab {
  display: flex;
  h2 {
    margin-right: 40px;
    color: #666;
    cursor: pointer;
    &.active {
      color: #2771ea;
    }
  }
}
.login-bg {
  min-height: 100%;
  box-sizing: border-box;
  background: #f5f7fb;
}
.login-container {
  min-width: 1200px;
  max-width: 1300px;
  margin: 0 auto;
  box-sizing: border-box;
}
.login-text {
  width: 680px;
  margin-top: 80px;
  h2 {
    font-family: PingFang-SC-Bold;
    font-size: 36px;
    color: #333333;
    margin-bottom: 30px;
    line-height: 36px;
  }
  p {
    font-size: 14px;
    line-height: 14px;
    color: #999999;
    margin-bottom: 15px;
  }
  img {
    width: 560px;
    display: block;
    margin-top: 40px;
  }
}
.login-right {
  height: 100vh;
  display: flex;
  align-items: center;
}
.login-wrap {
  width: 460px;
  background-color: #ffffff;
  box-shadow: 0px 0px 43px 0px rgba(9, 101, 255, 0.19);
  border-radius: 14px;
  margin-top: -40px;
}
.login-content {
  padding: 40px 45px 30px;
  h2 {
    font-family: PingFang-SC-Bold;
    font-size: 24px;
    line-height: 26px;
    margin-bottom: 30px;
  }
  .el-form-item {
    margin-bottom: 25px;
  }
  /deep/ .el-input__prefix {
    padding: 0 10px;
  }
  /deep/ .el-input__icon {
    font-size: 18px;
    line-height: 50px;
  }
  /deep/ .el-input__inner {
    height: 50px;
    background-color: #f5f7fa;
    border-radius: 8px;
    border: none;
    font-size: 16px;
    padding-left: 50px;
  }
}
.form-label {
  display: block;
  margin-bottom: 5px;
  font-weight: 700;
  color: rgba(0,0,0,.56);
}
.login-btn {
  width: 100%;
  height: 50px;
  font-size: 16px;
  border-radius: 6px;
}
.code {
  display: flex;
  /deep/ .el-input__inner {
    border-radius: 4px 0px 0px 4px;
  }
  .el-button {
    border-radius: 0px 4px 4px 0px;
  }
}

@media screen and (max-width: 700px){
  .login-text {
    display: none;
  }
  .login-container {
    width: 100%;
    min-width: 100%;
  }
  .login-right {
    float: initial;
    width: 100%;
  }
  .login-wrap {
    width: 90%;
    margin: 20px auto;
  }
  .login-content {
    width: 100%;
    padding: 40px 30px;
    box-sizing: border-box;
  }
}
</style>
