<template>
  <div class="examination-wrap bg-f5">
    <div class="examination pt20 pb20 clearfix">
      <div class="examination-left fl">
        <div class="bg-white pad15 mb20">
          <div class="clearfix bor-bottom-eee pb15 pt10">
            <h1 class="fl fz26 fb">{{examName}}: {{ paperName }}</h1>
            <span class="tag fr">答题中</span>
          </div>
          <div class="pt10">
            <p class="color-666">{{ form.examDesc }}</p>
          </div>
        </div>

        <div class="bg-white pad15 mb20" v-show="(checked && errorQuestions.length > 0) || (!checked && questionList.length > 0)">
          <div class="bor-bottom-eee pb20 pt10 pl10">
            <span class="title-2">{{ form.questionType == '1' ? '单选题' : form.questionType == '2' ? '多选题' : '问答题' }}</span>
            <el-checkbox v-if="form.examLanguage === 'en'" v-model="isShowCn" label="显示中文" style="float: right;white-space: nowrap;"></el-checkbox>
          </div>
          <!--答题列表-->
          <div class="topic">
            <div class="topic-item clearfix">
              <div class="topic-item-label fl">
                <div class="inlineb tc">
                  <span style="height: 18px;background-color: #409eff;border-radius: 3px;line-height: 18px;color: #fff;padding: 0 6px;">{{ currentQuestionIndex + 1 }}</span>
                </div>
              </div>
              <div class="topic-item-block fl">
                <div class="clearfix">
                  <pre class="fl" style="width: calc(100% - 60px);" v-html="form.questionNameEn"></pre>
                  <div class="fr">
                    <span class="topic-item-score mr10">{{ form.questionPoint }}分</span>
                    <i v-if="form.favorites === '1'" class="iconfont icon-shoucang_shixin" style="color: #ff5930;cursor: pointer;" @click="handleSign(form)"></i>
                    <i v-else class="iconfont icon-shoucang" style="color: #999;cursor: pointer;" @click="handleSign(form)"></i>
                  </div>
                </div>
                <p class="color-999 mt5" v-if="isShowCn" v-html="form.questionNameCn"></p>
                <div v-if="form.questionType == '1'">
                  <el-radio-group v-if="!getSubmitFlag()" v-model="form.selectAnswer" class="mt10">
                    <el-radio v-for="(answer, index) in form.answerList" :key="index" :label="answer.id" class="mb5">
                      <div style="display: inline-block;vertical-align: top;">
                        <p class="color-666" v-html="answer.answerContentEn" style="white-space: pre-line;"></p>
                        <p class="color-999" v-if="isShowCn" v-html="answer.answerContentCn" style="white-space: pre-line;"></p>
                      </div>
                    </el-radio>
                  </el-radio-group>
                  <div v-else style="padding: 10px 0;">
                    <div v-for="(answer, index) in form.answerList" :key="index" style="line-height: 24px;margin-bottom: 5px;display: flex;padding: 5px;" :style="form.selectAnswer && form.selectAnswer === answer.id && 'background-image: linear-gradient(to right, rgba(39, 113, 234, 0.1), rgba(255, 255, 255, 1));'">
                      <div class="mr10">
                        <template v-if="form.selectAnswer === answer.id || answer.correctFlag === '1'">
                          <img v-if="answer.correctFlag === '1'" src="../../assets/images/radio-check.png" alt="">
                          <img v-if="form.selectAnswer === answer.id && answer.correctFlag === '0'" src="../../assets/images/radio-error.png" alt="">
                        </template>
                        <img v-else src="../../assets/images/radio-error.png" alt="">
                      </div>
                      <div style="display: inline-block;vertical-align: top;flex: 1;">
                        <p class="color-666" v-html="answer.answerContentEn" style="white-space: pre-line;"></p>
                        <p class="color-999" v-if="isShowCn" v-html="answer.answerContentCn" style="white-space: pre-line;"></p>
                      </div>
                    </div>
                  </div>
                </div>
                <div v-if="form.questionType == '2'">
                  <el-checkbox-group v-if="!getSubmitFlag()" v-model="form.selectAnswer" class="mt10">
                    <el-checkbox v-for="(answer, index) in form.answerList" :key="index" :label="answer.id" class="mb5" :disabled="getSubmitFlag()" style="display: block;">
                      <div style="display: inline-block;vertical-align: top;">
                        <p class="color-666" v-html="answer.answerContentEn" style="white-space: pre-line;"></p>
                        <p class="color-999" v-if="isShowCn" v-html="answer.answerContentCn" style="white-space: pre-line;"></p>
                      </div>
                    </el-checkbox>
                  </el-checkbox-group>
                  <div v-else style="padding: 10px 0;">
                    <div v-for="(answer, index) in form.answerList" :key="index" style="line-height: 24px;margin-bottom: 5px;display: flex;padding: 5px;" :style="form.selectAnswer && form.selectAnswer.includes(answer.id) && 'background-image: linear-gradient(to right, rgba(39, 113, 234, 0.1), rgba(255, 255, 255, 1));'">
                      <div class="mr10">
                        <template v-if="form.selectAnswer.includes(answer.id) || answer.correctFlag === '1'">
                          <img v-if="answer.correctFlag === '1'" src="../../assets/images/radio-check.png" alt="">
                          <img v-else src="../../assets/images/radio-error.png" alt="">
                        </template>
                        <img v-else src="../../assets/images/radio-error.png" alt="">
                      </div>
                      <div style="display: inline-block;vertical-align: top;flex: 1;">
                        <p class="color-666" v-html="answer.answerContentEn" style="white-space: pre-line;"></p>
                        <p class="color-999" v-if="isShowCn" v-html="answer.answerContentCn" style="white-space: pre-line;"></p>
                      </div>
                    </div>
                  </div>
                </div>

                <div v-if="form.questionType == '3' && !getSubmitFlag()" style="padding-top: 20px;">
                  <el-input type="textarea" v-model="form.selectAnswer" placeholder="请输入" :disabled="!inAnswer" />
                </div>
                <div v-if="getSubmitFlag()" class="bor-top-eee mt10">
                  <div style="display: flex;justify-content: space-between;align-items: center;">
                    <p v-if="form.questionType == '3'" class="color-999 pt15 pb15">
                      <span v-if="form['selectAnswer']">你的答案：{{form['selectAnswer']}}</span>
                    </p>
                    <p v-else class="color-999 pt15 pb15">
                      <span>答题结果：</span>
                      <span v-if="getAnswerKey(form, form['correctAnswer']) === getAnswerKey(form, form['selectAnswer'])" style="color: green;font-weight: 700;">正确</span>
                      <span v-else style="color: red;font-weight: 700;">错误</span>
<!--                      正确答案：<span style="color: #46c37b;">{{ getAnswerKey(form, form['correctAnswer']) }}</span>， 你的答案： <span style="color: #ff5c2f;">{{ getAnswerKey(form, form['selectAnswer']) ? getAnswerKey(form, form['selectAnswer']) : '未作答'}}</span>-->
                    </p>
                    <div v-if="form.appId && form.fileId && form.psign" style="color: #2771ea;">
                      <i class="iconfont icon-shipinbofang mr5"></i>
                      <span style="margin-top: 5px;display: inline-block;color: #2771ea;cursor: pointer;" @click="handleViewVideo(form)">查看讲解视频</span>
                    </div>
                  </div>

                  <div class="pt10 pb10 pl15 pr20" style="background-color: #f5f7fb;display: flex;">
                    <div style="width: 55px;">解析：</div>
                    <div v-if="form.clarify" class="color-999" :class="form.isSpread && 'clarify-spread'" v-html="decode(form.clarify)" style="flex: 1;word-break: break-all;"></div>
                    <div v-else class="color-999">暂无解析</div>
                  </div>
                  <div v-if="form.clarify" class="tr pr20 pb10" style="background-color: #f5f7fb;">
                    <span style="color: #2771ea;cursor: pointer;" @click="handleSpread(form)">{{!form.isSpread ? '收起' : '查看更多 >'}}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="examination-right fr">
        <div class="examination-right-fixed">
          <div class="pad15 br4 bg-white" id="answerSheet">
            <div class="bor-bottom-eee pb10" style="display: flex;justify-content: space-between;align-items: center;">
              <span class="title-2 fb">答题卡</span>
              <div>
                <button class="theme-btn" @click="handleRedo">重 做</button>
                <button class="theme-btn" @click="$router.go(-1)" style="margin-left: 10px;">返 回</button>
              </div>
            </div>
            <div class="ibs-card-body__explain pt10">
              <span class="ibs-success-bg"></span>
              <small>正确</small>
              <span class="ibs-danger-bg"></span>
              <small>错误</small>
              <span class="ibs-tip-bg"></span>
              <small>未答</small>
            </div>
            <div v-show="!isShowAnswerSheet" class="answer-sheet-wrap pt10">
              <div class="answer-sheet" v-for="(question, index) in answerSheetList" :key="index">
                <div v-if="question.list.length" style="margin-bottom: 5px;">{{question.name}}</div>
                <a
                  v-for="(item, index) in question.list"
                  :key="index"
                  :class="[getStatusClass(item)]"
                  @click="goAnchor(item)"
                >
                  {{ item.index + 1 }}
                </a>
              </div>
            </div>
            <div class="tc pt20 pb10">
              <button class="mr20" @click="handlePrev" :disabled="currentQuestionIndex === 0" style="background: none;cursor: pointer;">
                <img src="../../assets/images/left-btn.png" alt="">
              </button>
              <button class="theme-btn mr20" @click="handleSubmit" :disabled="getSubmitFlag()" style="height: 40px;padding: 0 30px;">提交</button>
              <button class="mr20" @click="handleNext" :disabled="currentQuestionIndex === questionList.length - 1 || (questionList.length && questionList[currentQuestionIndex].status === '3')" style="background: none;cursor: pointer;">
                <img src="../../assets/images/right-btn.png" alt="">
              </button>
            </div>
          </div>
        </div>
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
import {
  SequenceExams,
  FavTopic,
  RedoSequenceExam
} from "../../api/course";
import { Base64 } from "js-base64";
export default {
  name: 'Examination',
  components: {

  },
  data() {
    return {
      inAnswer: true,
      isShowAnswerSheet: false,
      radio: '',
      checked: '',
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
      isShowCn: false,
      player: null,
      currentQuestionId: '',
      currentAnswerId: undefined,
      currentTypes: '1',
      currentQuestionIndex: 0,
      answerSheetList: []
    }
  },
  computed: {
    examName() {
      return this.$route.query.examName
    },
    paperName() {
      return this.$route.query.paperName
    }
  },
  created() {
    this.GetSequenceExam('first');
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
    decode(base64) {
      if (!base64) return ''
      if (!this.isBase64(base64)) return decodeURI(base64)
      return Base64.decode(base64)
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
    GetSequenceExam(type) {
      const loading = this.$loading({
        lock: true,
        text: '处理中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      let data = {
        userId: this.$store.state.user.userId,
        questionId: this.currentQuestionId,
        examId: this.$route.query.examId,
        paperId: this.$route.query.paperId,
        questionSubject: this.$route.query.questionSubject,
        types: this.currentTypes,
        answerId: this.currentAnswerId
      }
      if (type === 'next') {
        data.nextStatus = '3'
      }
      SequenceExams(data).then(res => {
        if (res.questionType == '2') {
          if (res.selectAnswer && res.selectAnswer.split(',').length) {
            res.selectAnswer = res.selectAnswer.split(',')
            if (res.correctAnswer) {
              res.correctAnswer = res.correctAnswer.split(',')
            }
          } else {
            res.selectAnswer = []
          }
        }
        this.form = res;
        this.questionList = res.questionList || [];
        this.answerSheetList = [
          { name: '单选题', list: [] },
          { name: '多选题', list: [] },
          { name: '简答题', list: [] }
        ]
        this.questionList.map((item, index) => {
          item.index = index
          if (item.questionType == 1) {
            item.selectAnswer = undefined
            this.answerSheetList[0].list.push(item)
          } else if (item.questionType == 2) {
            item.selectAnswer = []
            this.answerSheetList[1].list.push(item)
          } else if (item.questionType == 3) {
            this.answerSheetList[2].list.push(item)
          }
        })
        if (type === 'first') {
          this.questionList.some((item, index) => {
            if (item.status === '3') {
              this.currentQuestionIndex = index
              this.currentQuestionId = item.questionId
              return true
            }
          })
        }
        this.getIndex()
        this.$forceUpdate()
        loading.close()
      })
    },
    getAnswerKey(item, selectAnswer) {
      let keys = [];
      let answerIds = [];
      if (selectAnswer) {
        if (item.questionType === '1') {
          answerIds = [selectAnswer]
        } else if (item.questionType === '2') {
          answerIds = selectAnswer
        }
      } else {
        item.answerList.map(item => {
          if (item.correctFlag === '1') {
            answerIds.push(item.id)
          }
        })
      }
      item.answerList.map(itm => {
        answerIds.map(answerId => {
          if (itm.id === answerId) {
            keys.push(itm.answerContentEn.split('.')[0]);
          }
        })
      })
      return keys.toString();

      // let key = '';
      // if (item && item.answerList) {
      //   item.answerList.map(itm => {
      //     if (itm.id === id) {
      //       key = itm.answerContentEn.split('.')[0];
      //     }
      //   })
      // }
      // return key;
    },
    handleSign(item) {
      let favorite = item.favorites
      if (item.favorites == '1') {
        favorite = '0'
      } else {
        favorite = '1'
      }
      FavTopic({
        questionId: item.questionId,
        favStatus: favorite,
        userId: this.$store.state.user.userId
      }).then(res => {
        if (res.status === 1) {
          item.favorites = favorite
          if (favorite == '1') {
            this.$message.success('收藏成功！')
          } else {
            this.$message.success('取消收藏成功！')
          }
        } else {
          this.$message.error(res.msg)
        }
        this.$forceUpdate();
      })
    },
    getStatusClass(item) {
      //status: 0是错误，1是正确，3是未做”
      let className = '';
      switch (item.status) {
        case '1':
          className = 'ibs-success-bg';
          break;
        case '0':
          className = 'ibs-danger-bg';
          break;
        case '3':
          className = 'ibs-tip-bg';
          break;
      }
      return className;
    },
    goAnchor(item) {
      if (typeof item === 'string') {
        document.querySelector('#'+item).scrollIntoView({
          behavior: 'smooth'
        })
      } else {
        if (item.status === '0' || item.status === '1') {
          this.currentQuestionId = item.questionId
          this.currentAnswerId = item.answerId
          this.GetSequenceExam('index')
        }
      }
    },
    handleAnswer() {
      this.inAnswer = true;
      this.init();
    },
    handleBack() {
      this.$router.push({
        path: '/personal/exercise'
      })
    },
    handleRedo() {
      this.$confirm('本课程下所有顺序做题进度，错题记录，收藏夹均会全部重置，是否继续重置操作？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        RedoSequenceExam({
          userId: this.$store.state.user.userId,
          examId: this.$route.query.examId
        }).then(res => {
          if (res.status == 1) {
            this.$message.success('重置成功！')
            this.currentQuestionId = ''
            this.currentQuestionIndex = 0
            this.GetSequenceExam()
          } else {
            this.$message.error(res.msg)
          }
        })
      })
    },
    handleSubmit() {
      if (this.form.questionType != '3' && !this.form.selectAnswer) {
        this.$alert('请选择答案后在提交', '提示');
        return
      }
      this.$confirm('确定要提交吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let selectAnswer = this.form.selectAnswer
        if (this.form.questionType === '2') {
          selectAnswer = selectAnswer.toString()
        } else if (this.form.questionType === '3') {
          if (!selectAnswer) selectAnswer = ' '
        }
        const loading = this.$loading({
          lock: true,
          text: '处理中...',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        SequenceExams({
          userId: this.$store.state.user.userId,
          questionId: this.form.questionId,
          answerId: selectAnswer,
          answerSequence: this.form.answerSequence,
          examId: this.$route.query.examId,
          paperId: this.$route.query.paperId,
          types: this.currentTypes
        }).then(res => {
          if (res.questionType == '2') {
            if (res.selectAnswer && res.selectAnswer.split(',').length) {
              res.selectAnswer = res.selectAnswer.split(',')
              if (res.correctAnswer) {
                res.correctAnswer = res.correctAnswer.split(',')
              }
            } else {
              res.selectAnswer = []
            }
          }
          this.form = res;
          this.questionList = res.questionList || [];
          this.answerSheetList = [
            { name: '单选题', list: [] },
            { name: '多选题', list: [] },
            { name: '简答题', list: [] }
          ]
          this.questionList.map((item, index) => {
            item.index = index
            if (item.questionType == 1) {
              item.selectAnswer = undefined
              this.answerSheetList[0].list.push(item)
            } else if (item.questionType == 2) {
              item.selectAnswer = []
              this.answerSheetList[1].list.push(item)
            } else if (item.questionType == 3) {
              this.answerSheetList[2].list.push(item)
            }
          })
          this.getIndex()
          this.$forceUpdate()
          loading.close();
          // this.form = res;
          // this.questionList = res.questionList || []
          // this.getIndex()
          // this.GetSequenceExam()
        })
      })
    },
    getIndex() {
      if (this.questionList.length) {
        this.questionList.map((item, index) => {
          if (item.questionId === this.currentQuestionId) {
            this.currentQuestionIndex = index
          }
        })
      }
    },
    handlePrev() {
      this.currentQuestionIndex --
      this.currentQuestionId = this.questionList[this.currentQuestionIndex].questionId
      this.currentAnswerId = this.questionList[this.currentQuestionIndex].answerId
      // if (this.questionList[this.currentQuestionIndex].status === '0') {
      //   this.currentTypes = '0'
      // } else {
      //   this.currentTypes = '1'
      // }
      this.GetSequenceExam('prev')
    },
    handleNext() {
      if (this.questionList[this.currentQuestionIndex].status === '3') {
        this.$message.error('请提交完之后在进行下一题！')
        return
      }
      this.currentQuestionIndex ++
      this.currentQuestionId = this.questionList[this.currentQuestionIndex].questionId
      this.currentAnswerId = this.questionList[this.currentQuestionIndex].answerId
      // if (this.questionList[this.currentQuestionIndex].status === '0') {
      //   this.currentTypes = '0'
      // } else {
      //   this.currentTypes = '1'
      // }
      if (this.questionList[this.currentQuestionIndex].status === '3') {
        this.GetSequenceExam('next')
      } else {
        this.GetSequenceExam()
      }
    },
    handleUnanswer() {
      this.currentQuestionIndex ++
      this.currentQuestionId = this.questionList[this.currentQuestionIndex].questionId
      this.questionList.some((item, index) => {
        if (item.status === '3') {
          this.currentQuestionIndex = index
          this.currentQuestionId = item.questionId
          this.GetSequenceExam('next')
          return true
        }
      })
    },
    getSubmitFlag() {
      return  this.questionList.length &&  this.questionList[ this.currentQuestionIndex] && ( this.questionList[ this.currentQuestionIndex].status === '0' ||  this.questionList[ this.currentQuestionIndex].status === '1')
    }
  }
}
</script>

<style lang="less" scoped>
/deep/ .el-radio__input, /deep/ .el-checkbox__input {
  background: url("../../assets/images/radio.png") no-repeat;
  height: 16px;
  transition: none;
  &.is-checked {
    background: url("../../assets/images/radio-check.png") no-repeat;
    .el-radio__inner, .el-checkbox__inner {
      visibility: hidden;
    }
  }
  .el-radio__inner, .el-checkbox__inner {
    border: none;
    width: 16px;
    height: 16px;
    background: none;
  }
}
.theme-btn:disabled {
  cursor: not-allowed;
  opacity: 0.8;
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
      width: 85%;
      margin: 0 auto;
      .examination-left {
        width: calc(100% - 310px);
        .score-header {
          width: 70%;
          padding: 10px 20px 10px;
          color: #fff;
        }
        .topic {
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
              font-size: 16px;
              .el-radio {
                display: block;
              }
              p {
                font-size: 15px;
                word-wrap: break-word;
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
        position: relative;
        .answer-sheet-wrap {
          max-height: calc(50vh);
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
    background-color: rgba(235, 235, 235, 0.6);
    border-color: rgba(235, 235, 235, 0.6)!important;
    cursor: not-allowed!important;
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
          .theme-btn {
            margin-right: 10px;
            margin-bottom: 10px;
            padding: 0 15px;
            width: 100px;
          }
        }
        .examination-right {
          width: 100%;
          float: initial;
          margin-top: 20px;
          .answer-sheet-wrap {
            max-height: initial;
          }
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

.examination-right-fixed {
  position: fixed;
  top: 20px;
  width: 280px;
}

@media screen and (max-width: 700px){
  .examination-right-fixed {
    position: initial;
    width: 100%;
  }
}
</style>
