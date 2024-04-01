<template>
  <div class="login-container bg-f9">
    <div class="login-wrap">
      <div class="login-content">
        <el-form ref="form" :model="form" :rules="rules">
          <label class="form-label">密码</label>
          <el-form-item prop="userName">
            <el-input type="password" v-model="form.userName" placeholder="请输入密码" />
          </el-form-item>
          <label class="form-label">再次输入密码</label>
          <el-form-item prop="password">
            <el-input type="password" v-model="form.password" placeholder="请输入密码" />
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
  name: 'reset',
  data() {
    let checkPassword = (rule, value, callback)=>{
      if (this.form.password && this.form.password && this.form.userName !== this.form.password) {
        return callback(new Error('两次密码不一致'));
      } else {
        callback();
      }
    };
    return {
      form: {},
      rules: {
        userName: [
          { required: true, message: "请输入密码", trigger: "change" },
          { required: true, validator: checkPassword, trigger: 'change' }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "change" },
          { validator: checkPassword, trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$router.push('/forget/success');
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