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
            <!--注 册-->
            <h2>注册账号</h2>
            <el-form ref="form" :model="form" :rules="rules">
              <el-form-item prop="examType">
                <div class="sex-wrap">
                  <label>课程类型</label>
                  <div>
                    <el-radio v-model="form.examType" label="1">CDMP</el-radio>
                    <el-radio v-model="form.examType" label="9">CDGA</el-radio>
                  </div>
                </div>
              </el-form-item>
              <el-form-item prop="mobile">
                <el-input v-model="form.mobile" placeholder="请输入手机号码" maxlength="11" prefix-icon="el-icon-mobile-phone" />
              </el-form-item>
              <el-form-item prop="username">
                <el-input v-model="form.username" placeholder="请输入用户名" maxlength="20" prefix-icon="el-icon-user" />
              </el-form-item>
              <el-form-item prop="sex">
                <div class="sex-wrap">
                  <label>性别</label>
                  <div>
                    <el-radio v-model="form.sex" label="1">男</el-radio>
                    <el-radio v-model="form.sex" label="0">女</el-radio>
                  </div>
                </div>
              </el-form-item>
              <el-form-item prop="mail">
                <el-input v-model="form.mail" placeholder="请输入邮箱" maxlength="50" prefix-icon="el-icon-message" />
              </el-form-item>
              <el-form-item prop="password">
                <el-input type="password" v-model="form.password" placeholder="请输入密码" maxlength="20" prefix-icon="el-icon-lock" />
              </el-form-item>
              <el-form-item prop="password1">
                <el-input type="password" v-model="form.password1" placeholder="请输入重复密码" maxlength="20" prefix-icon="el-icon-lock" />
              </el-form-item>
              <el-form-item>
                <div style="display: flex;justify-content: space-between;margin-bottom: 20px;">
                  <el-checkbox v-model="isRead">我已阅读并同意《用户服务协议》</el-checkbox>
                  <router-link to="/login" style="cursor: pointer;color: #2771ea;">去登录</router-link>
                </div>
                <el-button type="primary" @click="submitForm" class="login-btn">注 册</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { register } from '@/api/login'
export default {
  name: 'register',
  data() {
    const checkPassword = (rule, value, callback) => {
      if (this.form.password === value) {
        return callback()
      }
      callback(new Error('两次密码不一致'))
    }
    return {
      form: {
        sex: '1',
        examType: '1'
      },
      rules: {
        mobile: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          { pattern: /^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8,6]|8[0-9]|9[1,8,9])\d{8}$/, message: '请输入正确的手机号' }
        ],
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        mail: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { pattern: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/, message: '请输入正确的邮箱' }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" }
        ],
        password1: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { validator: checkPassword, trigger: "blur" }
        ]
      },
      time: 60,
      codeDisabled: false,
      isRead: false
    }
  },
  methods: {
    getCode() {
      if (this.codeDisabled) return;
      this.codeDisabled = true;
      let timer = setInterval(() => {
        this.time --;
        if (this.time <= 0) {
          clearInterval(timer);
          this.codeDisabled = false;
        }
      }, 1000)
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (!this.isRead) {
            this.msgError('请先同意用户服务协议！');
            return false;
          }
          register(this.form).then(res => {
            this.msgSuccess('注册成功！');
            if (this.$refs['form']) {
              this.$refs['form'].resetFields();
            }
          })
        }
      });
    }
  }
}
</script>

<style scoped lang="less">
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
  width: 490px;
  background-color: #ffffff;
  box-shadow: 0px 0px 43px 0px rgba(9, 101, 255, 0.19);
  border-radius: 14px;
  margin-top: -40px;
}
.login-content {
  padding: 40px 45px 30px;
  h2 {
    font-family: PingFang-SC-Bold;
    font-size: 26px;
    line-height: 26px;
    color: #2771ea;
    margin-bottom: 30px;
  }
  .sex-wrap {
    height: 50px;
    background-color: #f5f7fa;
    border-radius: 8px;
    display: flex;
    label {
      line-height: 50px;
    }
    &>label {
      font-size: 16px;
      color: #333333;
      padding: 0 18px;
      margin-right: 10px;
    }
    .el-radio {
      margin-right: 20px;
    }
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
</style>