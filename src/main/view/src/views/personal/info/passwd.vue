<template>
  <div>
    <h2 class="personal-title">修改密码</h2>
    <el-form ref="form" :model="form" :rules="rules" label-width="150px" style="padding: 40px 20px 40px 0;width: 60%;">
      <el-form-item label="原始密码" prop="oldPassword">
        <el-input type="password" v-model="form.oldPassword" placeholder="请输入原始密码" />
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input type="password" v-model="form.newPassword" placeholder="请输入新密码" />
      </el-form-item>
      <el-form-item label="再次输入新密码" prop="newPassword1">
        <el-input type="password" v-model="form.newPassword1" placeholder="请再次输入新密码" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="submitForm" style="width: 100px;">确认修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ModifyPassword } from "../../../api/login";

export default {
  name: 'passwd',
  data() {
    const checkPassword = (rule, value, callback) => {
      if (this.form.newPassword === value) {
        return callback()
      }
      callback(new Error('两次密码不一致'))
    }
    return {
      form: {},
      rules: {
        oldPassword: [
          { required: true, message: "请输入原始密码", trigger: "blur" }
        ],
        newPassword: [
          { required: true, message: "请输入新密码", trigger: "blur" }
        ],
        newPassword1: [
          { required: true, message: "请输入新密码", trigger: "blur" },
          { validator: checkPassword, trigger: "blur" }
        ]
      }
    }
  },
  methods: {
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          ModifyPassword({
            originPassword: this.form.oldPassword,
            password: this.form.newPassword,
            userId: this.$store.state.user.userId
          }).then(res => {
            if (res.modifyStatus === 'success') {
              this.msgSuccess('密码修改成功，请重新登录！')
              if (this.$refs['form']) {
                this.$refs['form'].resetFields();
              }
              this.$store.dispatch('LogOut').then(() => {
                this.$router.push('/login');
              })
            } else {
              this.msgError(res.errorDsec);
            }
          })
        }
      });
    }
  }
}
</script>

<style scoped lang="less">

</style>