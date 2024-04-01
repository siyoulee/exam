<template>
  <div class="code">
    <span>课程：</span>
    <el-select v-model="examId" placeholder="请选择课程">
      <el-option v-for="(item, index) in examList" :key="index" :value="item.examId" :label="item.examName" />
    </el-select>
    <el-button @click="GetExchangeCode('1')">获取兑换码</el-button>
    <el-button @click="GetExchangeCode('2')">兑换码+短语</el-button>
  </div>
</template>

<script>
import { GetExams, GetExchangeCode } from '@/api/course';
export default {
  name: "index",
  data() {
    return {
      mistakesNum: 0,
      collectNum: 0,
      code: '',
      dialogVisible: false,
      isLoading: false,
      examId: '',
      examList: []
    }
  },
  created() {
    this.getData();
  },
  methods: {
    getData() {
      //错题列表
      GetExams({
        examId : '',
        userId: this.$store.state.user.userId
      }).then(res => {
        if (res.examList && res.examList.length) {
          this.examList = res.examList;
        }
      })
    },
    GetExchangeCode(types) {
      if (!this.examId) {
        this.msgWarning('请选择课程！')
        return false
      }
      this.isLoading = true
      GetExchangeCode({
        types: types,
        examId: this.examId,
        adminId: this.$store.state.user.userId
      }).then(res => {
        this.isLoading = false
        if (res.status == 1) {
          this.copyText(res.code)
          this.msgSuccess('复制成功！')
        } else {
          this.msgError(res.msg)
        }
      })
    },
    copyText(text) {
      let aux = document.createElement('input');
      aux.setAttribute('value', text);
      document.body.appendChild(aux);
      aux.select();
      document.execCommand('copy');
      document.body.removeChild(aux);
    }
  }
}
</script>

<style scoped lang="less">
.code {
  padding: 40px;
  height: calc(100vh - 130px);
  box-sizing: border-box;
  .el-select {
    margin: 0 10px;
  }
}
</style>