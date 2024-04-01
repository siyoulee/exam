<template>
  <div class="examination-wrap bg-f5">
    <div class="examination pt20 pb20 clearfix">
      <div class="examination-left fl">
        <div class="search-wrap">
          <el-select v-model="examId" placeholder="请选择课程" style="width: 160px;margin-right: 10px;">
            <el-option v-for="(item, index) in examList" :key="index" :value="item.examId" :label="item.examName" />
          </el-select>
          <div class="search">
            <i class="iconfont icon-sousuo" style="color: #c8c9cc;margin: 0 10px 0 16px;"></i>
            <el-input type="text" placeholder="输入题目/关键词" v-model="inputValue" @keyup.enter.native="handleSearch" clearable @clear="handleSearch" />
            <span class="search-btn" @click="handleSearch">搜索</span>
          </div>
          <el-button type="primary" @click="$router.go(-1)" size="small" icon="el-icon-back" style="margin-left: 15px;">返 回</el-button>
          <el-checkbox v-model="isShowNo" style="margin-left: 15px;" label="显示序号" />
        </div>
        <div class="bg-white pad15 mb20" v-show="(checked && errorQuestions.length > 0) || (!checked && questionList.length > 0)">
          <div class="bor-bottom-eee pb20 pt10 pl10">
            <span class="title-2">单选题</span>
          </div>
          <!--答题列表-->
          <div class="topic">
            <div class="topic-item clearfix" v-for="(item, index) in questionList" :key="index" :id="'ids-'+item.questionId">
              <div class="topic-item-label fl">
                <div class="inlineb tc">
                  <span style="height: 18px;background-color: #2771ea;border-radius: 3px;line-height: 18px;color: #fff;padding: 0 6px;">{{ index+1 }}</span>
                </div>
              </div>
              <div class="topic-item-block fl">
                <div class="clearfix">
                  <pre class="fl" style="width: calc(100% - 60px)" v-html="item.questionNameEn"></pre>
                  <div class="fr">
                    <span class="topic-item-score mr10">{{ item.questionPoint }}分</span>
<!--                    <i v-if="item.favorite === '1'" class="iconfont icon-shoucang_shixin" style="color: #ff5930;cursor: pointer;" @click="handleSign(item)"></i>-->
<!--                    <i v-else class="iconfont icon-shoucang" style="color: #999;cursor: pointer;" @click="handleSign(item)"></i>-->
                  </div>
                </div>
                <p class="color-999 mt5" v-html="item.questionNameCn"></p>
                <div v-for="(answer, answerId) in item.answerList" :key="answerId" style="line-height: 26px;" class="mt10">
                  <p class="color-666" :class="item.correctAnswer === answer.answerId ? 'green-bg' : ''" v-html="isShowNo ? answer.answerContentEn : getAnswerContent(answer.answerContentEn)"></p>
                  <p class="color-999" v-html="answer.answerContentCn"></p>
                </div>
                <div class="mt10" :class="isShowNo && 'bor-top-eee'">
                  <div style="display: flex;justify-content: space-between;align-items: center;">
                    <p v-if="isShowNo" class="color-999 pt15 pb15">正确答案：<span class="theme-color">{{ getAnswerKey(item) }}</span></p>
                    <div v-if="item.appId && item.fileId && item.psign" style="color: #2771ea;">
                      <i class="iconfont icon-shipinbofang mr5"></i>
                      <span style="margin-top: 5px;display: inline-block;color: #2771ea;cursor: pointer;" @click="handleViewVideo(item)">查看讲解视频</span>
                    </div>
                  </div>
                  <div class="pt10 pb10 pl15 pr20 ck-content" style="background-color: #f5f7fb;display: flex;">
                    <div style="width: 55px;">解析：</div>
                    <div v-if="item.clarify" class="color-999" :class="!item.isSpread && 'clarify-spread'" v-html="decode(item.clarify)" style="flex: 1;"></div>
                    <div v-else class="color-999">暂无解析</div>
                  </div>
                  <div v-if="item.clarify" class="tr pr20 pb10" style="background-color: #f5f7fb;">
                    <span style="color: #2771ea;cursor: pointer;" @click="handleSpread(item)">{{item.isSpread ? '收起' : '查看更多 >'}}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <el-empty v-if="questionList.length === 0 && isSearch" description="未能查询到您需要的试题"></el-empty>
      </div>
    </div>

    <div class="go-answer-sheet" @click="goAnchor('answerSheet')">
      <img src="../../assets/images/answer-sheet.png" alt="">
    </div>
    <el-dialog
        v-if="dialogVisible"
        :visible.sync="dialogVisible"
        title="视频播放"
        width="800px"
        append-to-body
        :before-close="handleClose"
    >
      <div style="width: 100%;height: 400px;overflow: hidden;">
        <video v-if="dialogVisible" :id="'player-container'" preload="auto" width="100%" height="400" playsinline webkit-playsinline x5-playsinline style="width: 100%;height: 100%;"></video>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { SearchTopic, GetExams } from "../../api/course";
import { Base64 } from "js-base64";
export default {
  name: 'Search',
  components: {

  },
  data() {
    return {
      inAnswer: true,
      isShowAnswerSheet: false,
      radio: '',
      checked: '',
      tableData: [
        {
          name1: '单选题',
          name11: '100',
          name22: '数学',
          name2: '0',
          name3: '40道',
          name4: '0道',
          name5: '0道',
          name6: '40道',
        }
      ],
      questions: [],
      questionList: [],
      errorQuestions: [],
      fixed: false,
      form: {},
      remainingTime: '',
      remainingTimeS: '',
      timer: null,
      overForm: {},
      inputValue: '',
      dialogVisible: false,
      player: null,
      isSearch: false,
      isShowNo: false,
      examId: '',
      examList: []
    }
  },
  created() {
    this.GetExams()
    window.onscroll = () => {
      const scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      if (scrollTop > 160) {
        this.fixed = true;
      } else {
        this.fixed = false;
      }
    }
  },
  methods: {
    GetExams() {
      GetExams({
        examId : '',
        userId: this.$store.state.user.userId
      }).then(res => {
        if (res.examList && res.examList.length) {
          this.examList = res.examList;
          this.examId = this.examList[0].examId
        }
      })
    },
    isBase64(str){
      if(str === '' || str.trim() === ''){
        return false;
      }
      try{
        return btoa(atob(str)) == str;
      }catch(err){
        return false;
      }
    },
    decode(base64){
      if (!base64) return ''
      if (!this.isBase64(base64)) return decodeURI(base64)
      return Base64.decode(base64)
    },
    getAnswerContent(value) {
      return value.substring(2)
    },
    handleViewVideo(item) {
      if (item.appId && item.fileId && item.psign) {
        this.dialogVisible = true;
        setTimeout(() => {
          this.player = TCPlayer("player-container", {
            fileID: item.fileId,
            appID: item.appId,
            psign: item.psign
          });
        }, 1000)
      } else {
        this.$message.error('视频不存在');
      }
    },
    handleClose() {
      this.dialogVisible = false;
      this.player && this.player.dispose();
      this.player = null;
    },
    handleSpread(item) {
      item.isSpread = !item.isSpread;
      this.$forceUpdate();
    },
    handleSearch() {
      if (!this.examId) {
        this.msgWarning('请选择课程后搜索！')
        return
      }
      SearchTopic({
        userId: this.$store.state.user.userId,
        keyWord: this.inputValue,
        examId: this.examId
      }).then(res => {
        this.isSearch = true;
        if (typeof res === 'object') {
          this.questionList = res.questionList;
        } else {
          this.questionList = []
        }
      })
    },
    getAnswerKey(item) {
      let key = '';
      let id = item.correctAnswer;
      item.answerList.map(itm => {
        if (itm.answerId === id) {
          key = itm.answerContentEn.split('.')[0];
        }
      })
      return key;
    },
    handleSign(item) {
      if (item.favorite === '1') {
        item.favorite = '0'
      } else {
        item.favorite = '1'
      }
      this.$forceUpdate();
    },
    goAnchor(id){
      document.querySelector('#'+id).scrollIntoView({
        behavior: 'smooth'
      })
    }
  }
}
</script>

<style lang="less" scoped>
.search-wrap {
  padding: 12px 30px;
  background: #fff;
  margin-bottom: 20px;
  border-radius: 6px;
  display: flex;
  align-items: center;
  .search {
    flex: 1;
    display: flex;
    background-color: #f5f7fb;
    border-radius: 21px;
    align-items: center;
    /deep/ .el-input__inner {
      border: none!important;
      background: transparent;
      flex: 1;
      height: 42px;
    }
    .search-btn {
      display: inline-block;
      width: 80px;
      height: 38px;
      background-color: #2771ea;
      border-radius: 0px 19px 19px 0px;
      text-align: center;
      line-height: 38px;
      font-size: 16px;
      color: #fff;
      cursor: pointer;
      &:hover {
        opacity: 0.9;
      }
    }
  }
}
.exam-table {
  thead th {
    background-color: #fff!important;
    color: #999999;
  }
}
  .line {
    display: block;
    width: 130px;
    height: 20px;
    border-radius: 10px;
    background-color: #0a85c6;
    position: absolute;
    top: -5px;
    right: 15px;
    z-index: 0;
  }

  .examination-status {
    width: 85%;
    margin: 0 auto;
    background: #fff;
    text-align: center;
    padding: 40px 0;
    .examination-status-text {
      display: inline-block;
      vertical-align: middle;
      text-align: left;
    }
    img {
      width: 132.5px;
      margin-right: 64.5px;
    }
    h2 {
      color: #ff8836;
      margin-top: 4px;
      margin-bottom: 16px;
      line-height: 32px;
      font-size: 24px;
      font-weight: 500;
    }
    p {
      font-size: 16px;
      line-height: 24px;
      color: #999;
    }
  }
  .examination-wrap {
    width: 100%;
    min-height: 100vh;
    .examination {
      width: 80%;
      margin: 0 auto;
      .examination-left {
        width: 100% !important;
        .score-header {
          width: 70%;
          padding: 10px 20px 10px;
          color: #fff;
        }
        .topic {
          font-size: 16px;
          .topic-item {
            padding: 20px 0;
            border-bottom: 1px solid #eee;
            &:last-child {
              border-bottom: none;
            }
            .topic-item-label {
              width: 60px;
              text-align: center;
              position: relative;
            }
            .topic-item-tag {
              position: absolute;
              left: -16px;
              top: 60px;
              border-radius: 0 4px 4px 0;
              font-size: 12px;
              color: #409EFF;
              border: 1px solid #409EFF;
              box-shadow: 0 2px 0 rgb(0 0 0 / 5%);
              padding: 0 7px;
              cursor: pointer;
              &.active {
                background: #409EFF;
                color: #fff;
              }
            }
            .topic-item-score {
              padding: 4px;
              background-color: #f5f5f5;
              border-radius: 4px;
              font-size: 12px;
              line-height: 16px;
              color: #999;
            }
            .topic-item-block {
              width: calc(100% - 60px);
              .el-radio {
                display: block;
              }
            }
            pre {
              font-family: "Microsoft YaHei";
              white-space: pre-wrap;
              word-wrap: break-word;
              user-select: none;
            }
          }
        }
      }
      .examination-right {
        width: 280px;
        .answer-sheet-wrap {
          max-height: 280px;
          overflow: auto;
        }
        .answer-sheet {
          a {
            position: relative;
            display: inline-block;
            margin-right: 8px;
            margin-bottom: 8px;
            border-radius: 4px;
            width: 28px;
            height: 28px;
            text-align: center;
            color: #666;
            padding: 0;
            font-size: 12px;
            line-height: 28px;
            border: 1px solid #e1e1e1;
            cursor: pointer;
            position: relative;
            &.active {
              background-color: #409EFF;
              border: 1px solid #409EFF;
              color: #fff;
            }
            i {
              position: absolute;
              top: 4px;
            }
          }
        }
      }
      .title {
        padding-top: 10px;
        padding-bottom: 15px;
        border-bottom: 1px solid #eee;
      }
      .title-2 {
        display: inline-block;
        padding-left: 15px;
        font-size: 16px;
        line-height: 16px;
        position: relative;
        &:before {
          content: '';
          display: inline-block;
          width: 4px;
          height: 16px;
          background: #409EFF;
          position: absolute;
          top: 50%;
          left: 0;
          margin-top: -8px;
        }
      }
      .score-wrap {
        width: 120px;
        padding-top: 8px;
        padding-bottom: 8px;
        text-align: center;
        background-color: #27a7ea;
        color: #fff;
        box-shadow: 0px 4px 4px 0px
        rgba(15, 78, 181, 0.38);
        border-radius: 0px 0px 6px 6px;
        position: relative;
        top: -5px;
        left: -20px;
        z-index: 2;

        span {
          font-size: 32px;
          font-weight: 600;
          line-height: 40px;
        }
        p {
          line-height: 20px;
          font-size: 14px;
          font-weight: 400;
        }
      }
    }
  }
  .ibs-success-bg {
    background-color: #46c37b;
    border-color: #46c37b!important;
    color: #fff!important;
  }
  .ibs-danger-bg {
    background-color: #ff5c3b;
    border-color: #ff5c3b!important;
    color: #fff!important;
  }
  .ibs-warning-bg {
    background-color: #ff8836;
    border-color: #ff8836!important;
    color: #fff!important;
  }
  .ibs-tip-bg {
    background-color: #ebebeb;
    border-color: #ebebeb!important;
    color: #fff!important;
  }
  .ibs-card-body__explain {
    span {
      display: inline-block;
      width: 10px;
      height: 10px;
      border-radius: 4px;
    }
    small {
      margin-left: 4px;
      margin-right: 8px;
      font-size: 12px;
      color: #919191;
    }
  }

  .answer-sheet-fixed {
    position: fixed;
    width: 280px;
    top: 20px;
    box-sizing: border-box;
  }
  .go-answer-sheet {
    display: none;
  }
  @media screen and (max-width: 700px){
    .go-answer-sheet {
      display: block;
      position: fixed;
      right: 10px;
      bottom: 80px;
      img {
        width: 28px;
        height: 28px;
      }
    }
    .answer-sheet-fixed {
      position: initial;
      width: 100%;
    }
    .examination-status {
      padding: 20px;
      box-sizing: border-box;
      width: 92%;
      h2 {
        font-size: 20px;
        margin-bottom: 5px;
      }
      img {
        margin-right: 0;
      }
    }

    .examination-wrap {
      .examination {
        width: 92%;
        .examination-left {
          float: initial;
          width: 100%;
          .score-header {
            width: 100%;
            float: initial;
          }
          .score-wrap {
            float: initial;
            margin: 10px auto 0;
          }
        }
        .examination-right {
          width: 100%;
          float: initial;
          margin-top: 20px;
        }
      }
    }
    .topic-item-label {
      width: 50px!important;
    }
    .el-message-box {
      width: 90%;
    }
  }

.clarify-spread {
  max-height: 42px;
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
}

.green-bg {
  background-color: rgba(70, 195, 123, 0.2);
}
</style>
