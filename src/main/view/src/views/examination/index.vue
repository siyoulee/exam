<template>
  <div class="examination-wrap bg-f5">
<!--    <div v-if="Object.keys(overForm).length && overForm.getPoint < 60" class="examination-status">-->
<!--      <img src="../../assets/images/unpassed.png" alt="">-->
<!--      <div class="examination-status-text">-->
<!--        <h2>很遗憾，您未通过本次考试</h2>-->
<!--        <p>教师评语：-</p>-->
<!--      </div>-->
<!--    </div>-->
    <div class="examination pt20 pb20 clearfix">
      <div class="examination-left fl">
        <div v-if="inAnswer" class="bg-white pad15 mb20">
          <div class="clearfix bor-bottom-eee pb15 pt10">
            <h1 class="fl fz26 fb">{{examName}}: {{ form.paperName }}</h1>
            <span class="tag fr">答题中</span>
          </div>
          <div class="pt10">
            <p class="color-666">{{ form.paperDesc }}</p>
          </div>
        </div>

        <div v-else class="bg-white mb20 pr" style="z-index: 1;">
          <div class="line"></div>
          <div class="clearfix pr pb5" style="background-color: #409eff;border-radius: 6px 6px 0px 0;">
            <div class="fl score-header">
              <div class="clearfix pt10">
                <h1 class="fl fz30 fb">{{ overForm.paperName }}</h1>
                <span class="tag ml15" style="color: #2771ea;background: #fff;line-height: 14px;height: auto;">批阅完成</span>
              </div>
              <div class="pt10">
                <p style="opacity: 0.5;">
                  <span class="mr20">答题人：{{ overForm.userId }}</span>
                  <span>用时：{{ overForm.useTime }}分钟</span>
                </p>
              </div>
            </div>
            <div class="fr br4 tc score-wrap">
              <span>{{ overForm.getPoint }}</span>
              <p>总分：{{ overForm.paperPoint }}</p>
            </div>
          </div>
          <div class="pr" style="border-radius: 6px;overflow: hidden;width: 100%;top: -5px;background: #fff;">
            <el-table
                class="theme-table-01 exam-table"
                :data="tableData">
<!--              <el-table-column-->
<!--                  min-width="60px"-->
<!--                  align="center"-->
<!--                  prop="questionType"-->
<!--                  label="题型">-->
<!--              </el-table-column>-->
              <el-table-column
                  min-width="50px"
                  align="center"
                  prop="paperPoint"
                  label="总分">
              </el-table-column>
              <el-table-column
                  min-width="50px"
                  align="center"
                  prop="getPoint"
                  label="得分">
              </el-table-column>
              <el-table-column
                  min-width="50px"
                  align="center"
                  prop="totalQuestions"
                  label="总共"/>
              <el-table-column
                  min-width="50px"
                  align="center"
                  prop="totalCorrect"
                  label="答对"/>
              <el-table-column
                  min-width="50px"
                  align="center"
                  prop="totalError"
                  label="答错"/>
              <el-table-column
                  min-width="50px"
                  align="center"
                  prop="unanswered"
                  label="未答"/>
            </el-table>
          </div>
        </div>
        <div class="bg-white pad15 mb20" v-show="(checked && errorQuestions.length > 0) || (!checked && questionList.length > 0)">
          <el-checkbox v-if="form.examLanguage === 'en'" v-model="isShowCn" label="显示中文" style="float: right;white-space: nowrap;"></el-checkbox>
          <template v-for="(question, questionIndex) in questionList">
            <div v-if="question.list.length" class="bor-bottom-eee pb20 pt10 pl10">
              <span class="title-2">{{ question.name }}</span>
              <span class="color-999 ml30">共{{ question.list.length }}题/共{{ question.paperPoint }}分</span>
            </div>
            <!--答题列表-->
            <div class="topic">
              <div class="topic-item clearfix" v-for="(item, index) in question.list" :key="index" :id="'ids-'+item.questionId" :hidden="isShowError && getAnswerKey(item, item['correctAnswer']) == getAnswerKey(item, item['selectAnswer'])">
                <div class="topic-item-label fl">
                  <div class="inlineb tc">
                    <span style="height: 18px;background-color: #409eff;border-radius: 3px;line-height: 18px;color: #fff;padding: 0 6px;">{{ item.index+1 }}</span>
                  </div>
                </div>
                <div class="topic-item-block fl">
                  <div class="clearfix">
                    <pre class="fl" style="width: calc(100% - 60px);" v-html="item.questionNameEn"></pre>
                    <div class="fr">
                      <span class="topic-item-score mr10">{{ item.questionPoint }}分</span>
                      <i v-if="item.favorite == '1'" class="iconfont icon-shoucang_shixin" style="color: #ff5930;cursor: pointer;" @click="handleSign(item)"></i>
                      <i v-else class="iconfont icon-shoucang" style="color: #999;cursor: pointer;" @click="handleSign(item)"></i>
                    </div>
                  </div>
                  <p v-if="isShowCn" class="color-999 mt5" v-html="item.questionNameCn"></p>
                  <template v-if="item.questionType == '1'">
                    <el-radio-group v-if="inAnswer" v-model="item.selectAnswer" class="mt10">
                      <el-radio v-for="(answer, index) in item.answerList" :key="index" :label="answer.answerId" class="mb5" :disabled="!inAnswer">
                        <div style="display: inline-block;vertical-align: top;">
                          <p class="color-666" v-html="answer.answerContentEn" style="white-space: pre-line;"></p>
                          <p class="color-999" v-if="isShowCn" v-html="answer.answerContentCn" style="white-space: pre-line;"></p>
                        </div>
                      </el-radio>
                    </el-radio-group>
                    <div v-else style="padding: 10px 0;">
                      <div v-for="(answer, index) in item.answerList" :key="index" style="line-height: 24px;margin-bottom: 5px;display: flex;padding: 5px;" :style="item.selectAnswer && item.selectAnswer === answer.answerId && 'background-image: linear-gradient(to right, rgba(39, 113, 234, 0.1), rgba(255, 255, 255, 1));'">
                        <div class="mr10">
                          <template v-if="item.selectAnswer === answer.answerId || answer.correctFlag === '1'">
                            <img v-if="answer.correctFlag === '1'" src="../../assets/images/radio-check.png" alt="">
                            <img v-if="item.selectAnswer === answer.answerId && answer.correctFlag === '0'" src="../../assets/images/radio-error.png" alt="">
                          </template>
                          <img v-else src="../../assets/images/radio-error.png" alt="">
                        </div>
                        <div style="display: inline-block;vertical-align: top;flex: 1;">
                          <p class="color-666" v-html="answer.answerContentEn" style="white-space: pre-line;"></p>
                          <p class="color-999" v-if="isShowCn" v-html="answer.answerContentCn" style="white-space: pre-line;"></p>
                        </div>
                      </div>
                    </div>
                  </template>
                  <template v-else-if="item.questionType == '2'">
                    <el-checkbox-group v-if="inAnswer" v-model="item.selectAnswer" class="mt10">
                      <div v-for="(answer, index) in item.answerList" :key="index">
                        <el-checkbox :label="answer.answerId" class="mb5" :disabled="!inAnswer" style="display: block;">
                          <div style="display: inline-block;vertical-align: top;">
                            <p class="color-666" v-html="answer.answerContentEn" style="white-space: pre-line;"></p>
                            <p class="color-999" v-if="isShowCn" v-html="answer.answerContentCn" style="white-space: pre-line;"></p>
                          </div>
                        </el-checkbox>
                      </div>
                    </el-checkbox-group>
                    <div v-else style="padding: 10px 0;">
                      <div v-for="(answer, index) in item.answerList" :key="index" style="line-height: 24px;margin-bottom: 5px;display: flex;padding: 5px;" :style="item.selectAnswer && item.selectAnswer.includes(answer.answerId) && 'background-image: linear-gradient(to right, rgba(39, 113, 234, 0.1), rgba(255, 255, 255, 1));'">
                        <div class="mr10">
                          <template v-if="item.selectAnswer.includes(answer.answerId) || answer.correctFlag === '1'">
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
                  </template>
                  <div v-else style="padding-top: 20px;">
                    <el-input type="textarea" v-model="item.selectAnswer" placeholder="请输入" :disabled="!inAnswer" />
                  </div>
                  <div v-if="!inAnswer" class="bor-top-eee mt10">
                    <div style="display: flex;justify-content: space-between;align-items: center;">
                      <p v-if="item.questionType == '3'" class="color-999 pt15 pb15">
                        <span v-if="item['selectAnswer']">你的答案：{{item['selectAnswer']}}</span>
                      </p>
                      <p v-else class="color-999 pt15 pb15">
                        <span>答题结果：</span>
                        <template v-if="getStatus(item) !== '4'">
                          <span v-if="getAnswerKey(item, item['correctAnswer']) === getAnswerKey(item, item['selectAnswer'])" style="color: green;font-weight: 700;">正确</span>
                          <span v-else style="color: red;font-weight: 700;">错误</span>
                        </template>
                        <span v-else>未作答</span>
                        <!--                        正确答案：<span class="theme-color mr15">{{ getAnswerKey(item, item['correctAnswer']) }}</span>你的答案： <span style="color: #ff5c2f;">{{ getAnswerKey(item, item['selectAnswer']) ? getAnswerKey(item, item['selectAnswer']) : '未作答'}}</span>-->
                      </p>
                      <div v-if="item.appId && item.fileId && item.psign" style="color: #2771ea;">
                        <i class="iconfont icon-shipinbofang mr5"></i>
                        <span style="margin-top: 5px;display: inline-block;color: #2771ea;cursor: pointer;" @click="handleViewVideo(item)">查看讲解视频</span>
                      </div>
                    </div>

                    <div class="pt10 pb10 pl15 pr20" style="background-color: #f5f7fb;display: flex;">
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
          </template>
        </div>
      </div>
      <div class="examination-right fr">
        <div class="examination-right-fixed">
          <div v-if="inAnswer && remainingTime" class="pt5 pb5 pl15 pr15 color-999 bg-white mb20">倒计时：{{ remainingTime }}</div>
<!--           :class="fixed ? 'answer-sheet-fixed' : ''"-->
          <div class="pad15 br4 bg-white" id="answerSheet">
            <div class="bor-bottom-eee pb10">
              <span class="title-2 fb">答题卡</span>
              <span v-if="inAnswer" class="fr theme-color" style="cursor: pointer;" @click="isShowAnswerSheet = !isShowAnswerSheet">收起/展开答题卡>></span>
            </div>
            <div v-if="!inAnswer" class="ibs-card-body__explain pt10">
              <span class="ibs-success-bg"></span>
              <small>正确</small>
              <span class="ibs-danger-bg"></span>
              <small>错误</small>
              <!--            <span class="ibs-warning-bg"></span>-->
              <!--            <small>待批阅</small>-->
              <span class="ibs-tip-bg"></span>
              <small>未答</small>
            </div>
            <div v-show="!isShowAnswerSheet" class="answer-sheet-wrap pt20">
              <template v-if="inAnswer">
                <div class="answer-sheet" v-for="(question, index) in questionList" :key="index">
                  <div v-if="question.list.length" style="margin-bottom: 5px;">{{question.name}}</div>
                  <a
                      v-for="(item, index) in question.list"
                      :key="index"
                      :class="[item.selectAnswer && item.selectAnswer.length ? 'active' : '']"
                      @click="goAnchor('ids-'+item.questionId)"
                  >
                    {{ item.index + 1 }}
                    <i v-if="item.sign" class="el-icon-collection-tag"></i>
                  </a>
                </div>
              </template>
              <template v-else>
                <div class="answer-sheet" v-for="(question, index) in questionList" :key="index">
                  <div v-if="question.list.length" style="margin-bottom: 5px;">{{question.name}}</div>
                  <a
                      v-for="(item, index) in question.list"
                      :key="index"
                      :class="[getStatusClass(item)]"
                      @click="goAnchor('ids-'+item.questionId)"
                  >
                    {{ item.index + 1 }}
                    <i v-if="item.sign" class="el-icon-collection-tag"></i>
                  </a>
                </div>
              </template>
            </div>
            <div v-if="inAnswer" class="pad15 tc" style="padding-bottom: 5px;">
              <!--            <button class="theme-btn mr10" @click="handleSave">保存进度</button>-->
              <button class="theme-btn" @click="handleSubmit">立即提交</button>
              <button class="theme-btn" @click="handleBack" style="margin-left: 15px;">返 回</button>
            </div>
            <template v-else>
              <div class="mt15 pb10" style="border-bottom: 1px solid #eee;">
                <el-checkbox v-model="isShowError">只看错题</el-checkbox>
              </div>
              <div class="pt15 pb15 tc">
                <!--              <button class="theme-btn" @click="handleAnswer">再次答题</button>-->
                <button class="theme-btn" @click="handleBack" style="border-radius: 6px;width: 100%;height: 38px;">返回题目列表</button>
              </div>
            </template>
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
import {GetExamPage, PostExamPage, SearchTopic, FavTopic, examSequence} from "../../api/course";
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
      isShowCn: false,
      player: null,
      answerSheetList: [],
      isShowError: false
    }
  },
  computed: {
    examName() {
      return this.$route.query.examName
    }
  },
  created() {
    this.init();
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
    decode(base64){
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
    init() {
      GetExamPage({
        examId: this.$route.query.examId,
        questionSubject: this.$route.query.questionSubject,
        paperId: this.$route.query.id,
        practiseType: this.$route.query.practiseType || 3,
        userId: this.$store.state.user.userId,
      }).then(res => {
        this.form = res;
        let questionList = [
          { name: '单选题', list: [], paperPoint: 0 },
          { name: '多选题', list: [], paperPoint: 0 },
          { name: '简答题', list: [], paperPoint: 0 }
        ]
        res.questionList.map((item, index) => {
          item.index = index
          if (item.questionType == 1) {
            item.selectAnswer = ''
            questionList[0].list.push(item)
            questionList[0].paperPoint += Number(item.questionPoint)
          } else if (item.questionType == 2) {
            item.selectAnswer = []
            questionList[1].list.push(item)
            questionList[1].paperPoint += Number(item.questionPoint)
          } else if (item.questionType == 3) {
            item.selectAnswer = ''
            questionList[2].list.push(item)
            questionList[2].paperPoint += Number(item.questionPoint)
          }
        })
        this.questionList = JSON.parse(JSON.stringify(questionList))
        // this.questionList = JSON.parse(JSON.stringify(this.answerSheetList));
        if (res.paperTime !== '0') {
          this.countDown();
        }
      })
    },
    getAnswerKey(item, selectAnswer) {
      let keys = [];
      let answerIds = [];
      if (selectAnswer) {
        if (item.questionType == '1') {
          answerIds = [selectAnswer]
        } else if (item.questionType == '2') {
          answerIds = JSON.parse(JSON.stringify(selectAnswer))
        }
      } else {
        item.answerList.map(item => {
          if (item.correctFlag == '1') {
            answerIds.push(item.answerId)
          }
        })
      }
      item.answerList.map(itm => {
        answerIds.map(answerId => {
          if (itm.answerId == answerId) {
            keys.push(itm.answerContentEn.split('.')[0]);
          }
        })
      })
      return keys.toString();
    },
    handleSign(item) {
      let favorite = item.favorite
      if (item.favorite == '1') {
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
          item.favorite = favorite
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
    countDown() {
      let time = parseInt(this.form.paperTime) * 60;
      this.remainingTime = this.formatSeconds(time);
      this.timer = setInterval(() => {
        time --
        this.remainingTime = this.formatSeconds(time);
        this.remainingTimeS = time;
        if (time <= 0) {
          this.remainingTimeS = 0
          // this.PostExamPage();
          clearInterval(this.timer);
        }
      }, 1000)
    },
    formatSeconds(value) {
      var theTime = parseInt(value);// 需要转换的时间秒
      var theTime1 = 0;// 分
      var theTime2 = 0;// 小时
      var theTime3 = 0;// 天
      if(theTime > 60) {
        theTime1 = parseInt(theTime/60);
        theTime = parseInt(theTime%60);
        if(theTime1 > 60) {
          theTime2 = parseInt(theTime1/60);
          theTime1 = parseInt(theTime1%60);
          if(theTime2 > 24){
            //大于24小时
            theTime3 = parseInt(theTime2/24);
            theTime2 = parseInt(theTime2%24);
          }
        }
      }
      var result = '';
      if(theTime > 0){
        result = ""+parseInt(theTime)+"秒";
      }
      if(theTime1 > 0) {
        result = ""+parseInt(theTime1)+"分"+result;
      }
      if(theTime2 > 0) {
        result = ""+parseInt(theTime2)+"小时"+result;
      }
      if(theTime3 > 0) {
        result = ""+parseInt(theTime3)+"天"+result;
      }
      return result;
    },
    getStatus(item) {
      //status: 1 正确  2 错误  3 待批阅  4未做
      let status = '3';
      let correctAnswer = []
      item.answerList.map(item => {
        if (item.correctFlag === '1') {
          correctAnswer.push(item.answerId)
        }
      })
      if (item.questionType !== '2') {
        correctAnswer = correctAnswer.toString()
      }
      //selectAnswer correctAnswer
      if (item['selectAnswer']) {
        if (item['selectAnswer'].toString() == correctAnswer.toString()) {
          status = '1'
        } else {
          status = '2'
        }
      } else {
        status = '4'
      }
      if (item.questionType == '3') {
        status = '1'
      }
      return status;
    },
    getStatusClass(item) {
      //status: 1 正确  2 错误  3 待批阅  4未做
      let status = '3';
      let correctAnswer = []
      item.answerList.map(item => {
        if (item.correctFlag === '1') {
          correctAnswer.push(item.answerId)
        }
      })
      if (item.questionType !== '2') {
        correctAnswer = correctAnswer.toString()
      }
      //selectAnswer correctAnswer
      if (item['selectAnswer']) {
        if (item['selectAnswer'].toString() == correctAnswer.toString()) {
          status = '1'
        } else {
          status = '2'
        }
      } else {
        status = '4'
      }
      if (item.questionType == '3') {
        status = '1'
      }
      let className = 'ibs-success-bg';
      switch (status) {
        case '1':
          className = 'ibs-success-bg';
          break;
        case '2':
          className = 'ibs-danger-bg';
          break;
        case '3':
          className = 'ibs-warning-bg';
          break;
        case '4':
          className = 'ibs-tip-bg';
          break;
      }
      return className;
    },
    goAnchor(id){
      document.querySelector('#'+id).scrollIntoView({
        behavior: 'smooth'
      })
    },
    handleAnswer() {
      this.inAnswer = true;
      this.init();
    },
    handleBack() {
      let practiseType = this.$route.query.practiseType
      let path = '/personal/exercise'
      if (practiseType == 1) {
        path = '/personal/mistakes'
      } else if (practiseType == 2) {
        path = '/personal/collect'
      } else if (practiseType == 4) {
        path = '/personal/simulation'
      }
      this.$router.push({
        path: path
      })
    },
    handleSave() {
      this.$confirm('确认要保存进度，下次继续吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '继续答题',
        type: 'warning'
      }).then(() => {

      });
    },
    handleSubmit() {
      let myFlag=false;
      this.questionList.forEach(items=>{
        items.list.forEach(a=>{
          if(!a.selectAnswer){
            myFlag=true;
          }
        })
      })
      if(myFlag){
        this.$alert('还有未完成的题目，请检查后再提交', '提示');
        return
      }
      let flag = false;
      this.answerSheetList.map(question => {
        question.list.map(item => {
          if (item.questionType != '3' && !item.selectAnswer) {
            flag = true;
          }
        })
      })
      if (flag) {
        this.$alert('还有未完成的题目，请检查后再提交', '提示');
        return
      }
      this.$confirm('确定要提交吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '继续答题',
        type: 'warning'
      }).then(() => {
        this.PostExamPage();
      })
    },
    PostExamPage() {
      let data = {
        examLanguage: this.form.examLanguage,
        paperId: this.form.paperId,
        userId: this.form.userId,
        paperName: this.form.paperName,
        practiseType: this.$route.query.practiseType,
        examId: this.$route.query.examId,
        paperDesc: this.form.paperDesc,
        totalQuestions: this.form.totalQuestions,
        useTime: '0',
        paperTime: this.form.paperTime,
        paperPoint: this.form.paperPoint,
        favoriteIdList: undefined,
        jsonTopicList: this.form.jsonTopicList
      };
      let answerObj = {};
      let favoriteIdList = [];
      let questionIdList = [];
      let questionList = [];
      this.questionList.map(question => {
        question.list.map(item => {
          if (item.selectAnswer || item.questionType == '3') {
            let selectAnswer = typeof item.selectAnswer == 'object' ? item.selectAnswer.toString() : item.selectAnswer
            if (item.questionType == '3') {
              selectAnswer = ''
            }
            questionList.push({
              questionId: item.questionId,
              answerId: selectAnswer,
              answerSequence: item.answerSequence
            })
            answerObj[item.questionId] = selectAnswer
          }
          if (item.favorite == '1') {
            favoriteIdList.push(item.questionId);
          }
          questionIdList.push(item.questionId)
        })
      })
      // if (Object.keys(answerObj).length) data.answerList = [answerObj];
      if (favoriteIdList.length) data.favoriteIdList = favoriteIdList.toString();
      // if (questionIdList.length) data.questionIdList = questionIdList.toString();
      data.questionList = questionList
      data.useTime = Math.ceil(((parseInt(this.form.paperTime) * 60) - this.remainingTimeS) / 60).toString();
      PostExamPage(data).then(res => {
        this.inAnswer = false;
        this.overForm = res;
        let totalCorrect = 0
        let totalError = 0
        let getPoint = 0
        let unanswered = questionIdList.length - res.questionList.length
        res.questionList.map(item => {
          let correctAnswer = []
          if (item.answerList) {
            item.answerList.map(item => {
              if (item.correctFlag === '1') {
                correctAnswer.push(item.answerId)
              }
            })
          }
          if (item.questionType === '1') {
            item.correctAnswer = correctAnswer.toString()
          } else if (item.questionType === '2') {
            item.correctAnswer = correctAnswer
            if (item.selectAnswer) item.selectAnswer = item.selectAnswer.split(',')
          }
          if (item.selectAnswer == item.correctAnswer) {
            totalCorrect ++
            getPoint += Number(item.questionPoint)
          } else {
            totalError ++
          }
        })
        this.overForm.getPoint = getPoint
        // this.questionList = res.questionList;

        let questionList = [
          { name: '单选题', list: [], paperPoint: 0 },
          { name: '多选题', list: [], paperPoint: 0 },
          { name: '简答题', list: [], paperPoint: 0 }
        ]
        res.questionList.map((item, index) => {
          item.index = index
          if (item.questionType == 1) {
            questionList[0].list.push(item)
            questionList[0].paperPoint += Number(item.questionPoint)
          } else if (item.questionType == 2) {
            questionList[1].list.push(item)
            questionList[1].paperPoint += Number(item.questionPoint)
          } else if (item.questionType == 3) {
            questionList[2].list.push(item)
            questionList[2].paperPoint += Number(item.questionPoint)
          }
        })
        this.questionList = questionList
        this.errorQuestions = [];
        // this.questionList.map(item => {
        //   if (item['selectAnswer'] && item['selectAnswer'] !== item['correctAnswer']) {
        //     this.errorQuestions.push(item);
        //   }
        // })
        this.tableData = [
          {
            paperPoint: this.overForm.paperPoint,
            getPoint: getPoint,
            totalQuestions: this.overForm.totalQuestions,
            totalCorrect: totalCorrect,
            totalError: totalError,
            unanswered: unanswered,
          }
        ];
        clearInterval(this.timer);
      })
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
        background-color: #31aeef;
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
