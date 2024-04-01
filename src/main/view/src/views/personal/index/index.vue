<template>
  <div style="height: 400px;">
<!--    <div class="card">
      <h2 class="personal-title">开始门户</h2>
      <ul>
        <router-link to="/personal/mistakes">
          <li>
            <div>
              <img src="../../../assets/images/home-icon01.png" alt="">
            </div>
            <p class="tc">错题数：<span>{{ mistakesNum }}</span></p>
          </li>
        </router-link>
        <router-link to="/personal/collect">
          <li>
            <div>
              <img src="../../../assets/images/home-icon02.png" alt="">
            </div>
            <p class="tc">收藏题数：<span>{{ collectNum }}</span></p>
          </li>
        </router-link>
      </ul>
    </div>-->
    <div class="card">
      <h2 class="personal-title">个人中心 </h2>
      <ul>
        <router-link to="/personal/passwd">
          <li>
            <div>
              <img src="../../../assets/images/home-icon03.png" alt="">
            </div>
            <p class="tc">修改密码</p>
          </li>
        </router-link>
        <router-link to="/personal/material">
          <li>
            <div>
              <img src="../../../assets/images/home-icon04.png" alt="">
            </div>
            <p class="tc">修改个人资料</p>
          </li>
        </router-link>
        <li @click="handleOpen">
          <div>
            <img src="../../../assets/images/home-icon05.png" alt="">
          </div>
          <p class="tc">兑换课程</p>
        </li>
      </ul>
    </div>

    <el-dialog
      title="兑换课程"
      :visible.sync="dialogVisible"
      width="400px"
      :before-close="handleClose"
      :close-on-click-modal="false"
      append-to-body
    >
      <div v-loading="isLoading" element-loading-text="兑换中..." style="padding: 10px 0;">
        <el-input  type="text" v-model="code" placeholder="请输入兑换码"></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="ExchangeCode" size="small" :loading="isLoading">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getPaperList, ExchangeCode } from '@/api/course';
export default {
  name: "index",
  data() {
    return {
      mistakesNum: 0,
      collectNum: 0,
      code: '',
      dialogVisible: false,
      isLoading: false
    }
  },
  created() {
    // this.getData();
  },
  methods: {
    getData() {
      //错题列表
      getPaperList({
        practiseType: '1',
        paperType: 'CDMP',
        userId: this.$store.state.user.userId
      }).then(res => {
        if (res.paperList) {
          if (res.paperList.length > 0) {
            this.mistakesNum = res.paperList[0].errorNum;
          }
        }
      })
      //收藏列表
      getPaperList({
        practiseType: '2',
        paperType: 'CDMP',
        userId: this.$store.state.user.userId
      }).then(res => {
        if (res.paperList) {
          if (res.paperList.length > 0) {
            this.collectNum = res.paperList[0].favoritesNum;
          }
        }
      })
    },
    ExchangeCode() {
      if (!this.code) {
        this.msgWarning('请输入兑换码！')
        return false
      }
      this.isLoading = true
      ExchangeCode({
        code: this.code,
        userId: this.$store.state.user.userId
      }).then(res => {
        this.isLoading = false
        if (res.status == 1) {
          this.msgSuccess('注册成功！')
          this.handleClose()
        } else {
          this.msgError(res.msg)
        }
      })
    },
    handleOpen() {
      this.dialogVisible = true
      this.code = ''
    },
    handleClose() {
      this.dialogVisible = false
    }
  }
}
</script>

<style scoped lang="less">
.card {
  background-color: #ffffff;
  box-shadow: 0px 10px 10px 0px rgba(184, 190, 204, 0.08);
  border-radius: 6px;
  margin-bottom: 20px;
}
ul {
  padding: 30px;
  li {
    width: 100px;
    margin-right: 30px;
    display: inline-block;
    cursor: pointer;
    div {
      width: 100px;
      height: 100px;
      background-color: #f5f7fb;
      border-radius: 6px;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    p {
      margin-top: 10px;
      color: #999999;
      white-space: nowrap;
      span {
        color: #409EFF;
      }
    }
  }
}
</style>