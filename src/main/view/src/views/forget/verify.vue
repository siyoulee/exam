<template>
  <div class="login-container bg-f9">
    <div class="login-wrap">
      <div class="login-content">
        <!--登录-->
        <el-form ref="form" :model="form" :rules="rules">
          <label class="form-label">手机号码</label>
          <el-form-item prop="userName">
            <el-input v-model="form.userName" placeholder="请输入手机号码" />
          </el-form-item>
          <label class="form-label">验证码</label>
          <el-form-item prop="code">
            <div class="code">
              <el-input v-model="form.code" placeholder="请输入验证码" style="border-radius: 4px 0 0 4px;"/>
              <el-button type="primary" size="small" @click="getCode" :disabled="codeDisabled">{{codeDisabled ? time+'S后重新获取' : '获取验证码'}}</el-button>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm" style="width: 100%;" class="mt15">下一步</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'verify',
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
      },
      time: 60,
      codeDisabled: false
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
          this.$router.push('/forget/reset');
          if (this.form.id != undefined) {
            // updateJob(this.form).then(response => {
            //   this.msgSuccess("修改成功");
            // });
          } else {
            // addJob(this.form).then(response => {
            //   this.msgSuccess("新增成功");
            // });
          }
        }
      });
    }
  }
}
</script>

<style scoped lang="less">
.login-container {
  min-height: 100%;
  padding: 20px;
  box-sizing: border-box;
}
.login-wrap {
  width: 460px;
  margin: 100px auto;
  border: 1px solid #e4ecf3;
  background: #fff;
}
.login-tab {
  .login-tab-item {
    display: inline-block;
    padding: 20px;
    width: 50%;
    font-size: 16px;
    text-align: center;
    color: #666;
    background-color: #f5f5f5;
    box-sizing: border-box;
    cursor: pointer;
    &.active {
      background: #fff;
    }
  }
}
.login-content {
  padding: 40px 45px 30px;
}
.form-label {
  display: block;
  margin-bottom: 5px;
  font-weight: 700;
  color: rgba(0,0,0,.56);
}
.login-btn {
  width: 100%;
  border-radius: 4px;
  height: 40px;
  line-height: 40px;
  background: #FF6B1B;
  color: #fff;
  text-align: center;
  cursor: pointer;
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