<template>
  <div>
    <h2 class="personal-title">修改个人资料</h2>
    <el-form ref="form" :model="form" :rules="rules" label-width="150px" style="padding: 40px 20px 40px 0;width: 60%;">
      <el-form-item label="昵称" prop="username">
        <el-input v-model="form.username" placeholder="请输入昵称" />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio v-model="form.sex" label="1">男</el-radio>
        <el-radio v-model="form.sex" label="0">女</el-radio>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="submitForm" style="width: 100px;">确认修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ModifyProfile, GetProfile } from "../../../api/login";

export default {
  name: 'material',
  data() {
    return {
      form: {
        sex: '1'
      },
      rules: {
        username: [
          { required: true, message: "请输入昵称", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      GetProfile({
        userId: this.$store.state.user.userId
      }).then(res => {
        this.form = res;
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          ModifyProfile({
            username: this.form.username,
            sex: this.form.sex,
            userId: this.$store.state.user.userId
          }).then(res => {
            if (res.modifyStatus === 'success') {
              this.msgSuccess('个人资料修改成功！')
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
.form-btn {
  border-radius: 4px;
  width: 80px;
  text-align: center;
  box-sizing: border-box;
  background: #FF6B1B;
}
</style>