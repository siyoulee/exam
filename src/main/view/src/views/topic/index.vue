<template>
  <div style="">
    <div v-show="!isEdit" class="card">
      <h2 class="personal-title" style="display: flex;align-items: center;justify-content: space-between;">
        题目列表
        <el-button type="text" icon="el-icon-circle-plus-outline" @click="handleAdd">新增题目</el-button>
      </h2>
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="80px" style="padding: 15px 20px 0;">
        <el-form-item label="题目名称:" prop="questionName">
          <el-input
              v-model="queryParams.questionName"
              placeholder="请输入题目名称"
              clearable
              @keyup.enter.native="handleQuery"
              style="width: 180px;"
          />
        </el-form-item>
        <el-form-item label="题目来源:" prop="source">
          <el-select v-model="queryParams.source" placeholder="请选择题目来源" clearable style="width: 180px;">
            <el-option
                v-for="(dict, index) in sourceOptions"
                :key="index"
                :label="dict.source"
                :value="dict.source"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="所属课程:" prop="examId">
          <el-select v-model="queryParams.examId" placeholder="请选择所属课程" clearable @change="handleQueryExamChange" style="width: 180px;">
            <el-option
                v-for="dict in examList"
                :key="dict.examId"
                :label="dict.examName"
                :value="dict.examId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="所属考点:" prop="paperId">
          <el-select v-model="queryParams.paperId" placeholder="请选择所属考点" clearable style="width: 180px;">
            <el-option
                v-for="dict in queryPaperList"
                :key="dict.paperId"
                :label="dict.paperName"
                :value="dict.paperId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="问题类别:" prop="questionSubject">
          <el-select v-model="queryParams.questionSubject" placeholder="请选择问题类别" clearable style="width: 180px;">
            <el-option
                v-for="(dict, index) in questionSubjectOptions"
                :key="index"
                :label="dict.subjectName"
                :value="dict.subjectId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="题目ID:" prop="questionId">
          <el-input v-model="queryParams.questionId" placeholder="请输入题目ID" clearable @keyup.enter.native="handleQuery" style="width: 180px;"/>
        </el-form-item>
        <el-form-item label="题目状态:" prop="status">
          <el-radio v-model="queryParams.status" label="1" style="margin-right: 20px;">有效</el-radio>
          <el-radio v-model="queryParams.status" label="0">停用</el-radio>
        </el-form-item>
        <el-form-item style="margin-left: 60px;">
          <el-button type="primary" size="small" icon="el-icon-search" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="small" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div v-show="!isEdit" class="card" style="height: calc(100vh - 352px);margin-bottom: 0;">
      <div style="height: calc(100% - 80px);">
        <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange" height="100%">
          <el-table-column label="ID" align="center" prop="id" width="60px" />
          <el-table-column label="所属课程" align="center" prop="examId" width="120px">
            <template slot-scope="scope">
              <span style="font-size: 16px;">{{ getExamName(scope.row.examId) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="题型" align="center" prop="questionType" width="60px">
            <template slot-scope="scope">
              <span style="font-size: 16px;">{{ getQuestionTypeName(scope.row.questionType) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="考点" align="center" prop="examId" width="140px">
            <template slot-scope="scope">
              <span style="font-size: 16px;">{{ getPaperName(scope.row.paperId) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="类别" align="center" prop="questionSubject" width="80px">
            <template slot-scope="scope">
              <span style="font-size: 16px;">{{ getQuestionSubjectName(scope.row.questionSubject) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="题目名称" align="left" prop="questionNameEn">
            <template slot-scope="scope">
              <span style="font-size: 16px;">{{ scope.row.questionNameEn }}</span>
            </template>
          </el-table-column>
          <el-table-column label="题目来源" align="center" prop="source" width="80px" :show-overflow-tooltip="true" />
          <el-table-column label="状态" align="center" prop="status" width="60px">
            <template slot-scope="scope">
              <span v-if="scope.row.status === '1'">有效</span>
              <span v-if="scope.row.status === '0'">停用</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="120px">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="text"
                  @click="handleUpdate(scope.row)"
                  style="font-size: 16px;"
              >修改</el-button>
              <el-button
                  size="mini"
                  type="text"
                  @click="changeTopicStatus(scope.row)"
                  style="font-size: 16px;"
              >{{scope.row.status === '1' ? '停用' : '启用'}}</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <pagination
        :total="total"
        :page.sync="queryParams.page"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>

    <!--新增-->
    <div v-show="isEdit" class="card" style="margin-bottom: 0;">
      <h2 class="personal-title" style="display: flex;align-items: center;justify-content: space-between;">
        <span>{{ title }}</span>
        <el-button type="primary" @click="cancel" size="small" icon="el-icon-back">返 回</el-button>
      </h2>
      <el-form ref="form" :model="form" :rules="rules" label-width="140px" style="padding: 10px 30px 30px;" class="edit-form">
        <el-tabs v-model="activeName">
          <el-tab-pane label="关键信息" name="1">
            <el-row>
              <el-col :span="12">
                <el-form-item label="所属课程:" prop="examId">
                  <el-select v-model="form.examId" placeholder="请选择所属课程" clearable style="width: 100%;" @change="handleExamChange">
                    <el-option
                        v-for="(dict, index) in examList"
                        :key="index"
                        :label="dict.examName"
                        :value="dict.examId"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="所属考点:" prop="paperId">
                  <el-select v-model="form.paperId" placeholder="请选择所属考点" clearable style="width: 100%;">
                    <el-option
                        v-for="(dict, index) in paperList"
                        :key="index"
                        :label="dict.paperName"
                        :value="dict.paperId"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="题型:" prop="questionType">
                  <el-select v-model="form.questionType" placeholder="请选择题型" clearable style="width: 100%;" @change="questionTypeChange">
                    <el-option
                        v-for="dict in questionTypeOptions"
                        :key="dict.typeId"
                        :label="dict.typeName"
                        :value="dict.typeId"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="问题类别:" prop="questionSubject">
                  <el-select v-model="form.questionSubject" placeholder="请选择问题类别" clearable style="width: 100%;">
                    <el-option
                        v-for="(dict, index) in questionSubjectOptions"
                        :key="index"
                        :label="dict.subjectName"
                        :value="dict.subjectId"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="题目名称（英文）:" prop="questionNameEn">
              <CKEditor v-if="questionNameEnFlag && isEdit" v-model="form.questionNameEn" height="300px" name="questionNameEn" />
              <el-input v-else type="textarea" v-model="form.questionNameEn" placeholder="请输入题目名称（英文）" />
              <el-checkbox v-model="questionNameEnFlag">是否使用富文本</el-checkbox>
            </el-form-item>
            <el-form-item label="题目名称（中文）:" prop="questionNameCn">
              <CKEditor v-if="questionNameCnFlag && isEdit" v-model="form.questionNameCn" height="300px" name="questionNameCn" />
              <el-input v-else type="textarea" v-model="form.questionNameCn" placeholder="请输入题目名称（中文）" />
              <el-checkbox v-model="questionNameCnFlag">是否使用富文本</el-checkbox>
            </el-form-item>
            <template v-for="(item, index) in answerList">
              <el-form-item label="答案（英文）:" prop="answerContentEn">
                <CKEditor v-if="item.answerContentEnFlag && isEdit" v-model="item.answerContentEn" height="300px" :name="'answerContentEn' + index" />
                <el-input v-else type="textarea" v-model="item.answerContentEn" placeholder="请输入答案（英文）" style="width: calc(100% - 185px)">
                  <template slot="append"></template>
                </el-input>
                <el-button v-if="index === 0" type="primary" icon="el-icon-plus" style="float: right;" @click="handleAddAnswer(item, index)">新增答案</el-button>
                <el-button v-if="index > 0" type="warning" plain icon="el-icon-minus" style="float: right;" @click="handleRemoveAnswer(item, index)">删除答案</el-button>
                <div :class="item.correctAnswerFlag == '1' && 'answer-checked'" style="display: inline-block;cursor: pointer;vertical-align: middle;float: right;padding: 0 10px;" @click="handleAnswerClick(item, index)"><i class="el-icon-circle-check"></i> 正确</div>
                <el-checkbox v-model="item.answerContentEnFlag">是否使用富文本</el-checkbox>
              </el-form-item>
              <el-form-item label="答案（中文）:" prop="answerContentCn">
                <CKEditor v-if="item.answerContentCnFlag && isEdit" v-model="item.answerContentCn" height="300px" :name="'answerContentCn' + index" />
                <el-input v-else type="textarea" v-model="item.answerContentCn" placeholder="请输入答案（中文）" style="width: calc(100% - 185px)" />
                <div>
                  <el-checkbox v-model="item.answerContentCnFlag">是否使用富文本</el-checkbox>
                </div>
              </el-form-item>
            </template>
            <el-form-item label="题目解释:" prop="clarify">
              <CKEditor v-if="isEdit" name="clarify" v-model="form.clarify" />
            </el-form-item>
            <el-form-item label="题目状态:" prop="status">
              <el-select v-model="form.status" placeholder="请选择题目状态" style="width: 100%;">
                <el-option
                    v-for="(item, index) in statusOptions"
                    :key="index"
                    :label="item.statusName"
                    :value="item.statusId"
                />
              </el-select>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="其他信息" name="2">
            <el-form-item label="题目难度:" prop="difficulty">
              <el-select v-model="form.difficulty" placeholder="请选择题目难度" clearable style="width: 100%;">
                <el-option
                    label="S+"
                    value="4"
                />
                <el-option
                    label="S"
                    value="3"
                />
                <el-option
                    label="A"
                    value="2"
                />
                <el-option
                    label="B"
                    value="1"
                />
                <el-option
                    label="C"
                    value="0"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="题目置信度:" prop="confidence">
              <el-select v-model="form.confidence" placeholder="请选择题目状态" clearable style="width: 100%;">
                <el-option
                    label="正确"
                    value="3"
                />
                <el-option
                    label="高"
                    value="2"
                />
                <el-option
                    label="中"
                    value="1"
                />
                <el-option
                    label="低"
                    value="0"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="fileId:" prop="fileId">
              <el-input type="text" v-model="form.fileId" placeholder="请输入fileId" show-word-limit />
            </el-form-item>
            <el-form-item label="appId:" prop="appId">
              <el-input type="text" v-model="form.appId" placeholder="请输入appId" show-word-limit />
            </el-form-item>
            <el-form-item label="psign:" prop="psign">
              <el-input type="text" v-model="form.psign" placeholder="请输入psign" show-word-limit />
            </el-form-item>
            <el-form-item label="微信视频地址:" prop="wxVideoUrl">
              <el-input type="textarea" v-model="form.wxVideoUrl" placeholder="请输入微信视频地址" show-word-limit />
            </el-form-item>
            <el-form-item label="问题分数:" prop="questionPoints">
              <el-input-number v-model="form.questionPoints" :min="1" :max="100" label="请输入问题分数"></el-input-number>
            </el-form-item>
            <el-form-item label="题目来源:" prop="source">
              <el-select v-model="form.source" placeholder="请选择题目来源" clearable style="width: 100%;">
                <el-option
                    v-for="(dict, index) in sourceOptions"
                    :key="index"
                    :label="dict.source"
                    :value="dict.source"
                />
              </el-select>
            </el-form-item>
          </el-tab-pane>
        </el-tabs>

        <div class="tc" style="padding-top: 10px;">
          <el-button @click="cancel" style="width: 150px;">返 回</el-button>
          <el-button type="primary" @click="submitForm" style="width: 150px;margin-left: 50px;">提 交</el-button>
        </div>
      </el-form>
    </div>

  </div>
</template>

<script>
import { GetTopicList, GetTopic, AddTopic, UpdateTopic, TopicStatus, GetExams } from '@/api/course';
import CKEditor from "../../components/CKEditor";
import { Base64 } from "js-base64";
export default {
  name: "topic",
  components: {
    CKEditor
  },
  data() {
    return {
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,

      // 查询参数
      queryParams: {
        adminId: '',
        questionName: '',
        source: '',
        paperId: '',
        status: '1', //有效
        page: 1,
        pageSize: 20,
        questionSubject: '',
        questionId: ''
      },
      list: [],
      sourceOptions: [],
      questionSubjectOptions: [],
      questionTypeOptions: [],
      title: '题目新增',
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        examId: [
          { required: true, message: "所属课程不能为空", trigger: "blur" }
        ],
        questionNameEn: [
          { required: true, message: "题目名称（英文）不能为空", trigger: "blur" }
        ],
        paperId: [
          { required: true, message: "所属考点不能为空", trigger: "blur" }
        ],
        questionPoints: [
          { required: true, message: "问题分数不能为空", trigger: "blur" }
        ],
        // difficulty: [
        //   { required: true, message: "题目难度不能为空", trigger: "blur" }
        // ],
        // confidence: [
        //   { required: true, message: "问题置信度不能为空", trigger: "blur" }
        // ],
        // videoUrl: [
        //   { required: true, message: "视频解析地址不能为空", trigger: "blur" }
        // ],
        // clarify: [
        //   { required: true, message: "题目解释不能为空", trigger: "blur" }
        // ],
        questionSubject: [
          { required: true, message: "问题主题不能为空", trigger: "blur" }
        ],
        questionType: [
          { required: true, message: "问题类型不能为空", trigger: "blur" }
        ],
        source: [
          { required: true, message: "题目来源不能为空", trigger: "blur" }
        ]
      },
      isEdit: false,
      answerList: [
        {
          answerContentCn: '',
          answerContentEn: '',
          correctAnswerFlag: '0'
        }
      ],
      activeName: '1',
      examList: [],
      statusOptions: [],
      paperList: [],
      queryPaperList: [],
      questionNameEnFlag: false,
      questionNameCnFlag: false,
      allPaperList: []
    }
  },
  created() {
    this.getList(1);
  },
  methods: {
    getExamName(id) {
      let name = ''
      this.examList.some(item => {
        if (item.examId === id) {
          name = item.examName
          return true
        }
      })
      return name
    },
    getQuestionTypeName(id) {
      let name = ''
      this.questionTypeOptions.some(item => {
        if (item.typeId === id) {
          name = item.typeName
          return true
        }
      })
      return name
    },
    getPaperName(id) {
      let name = ''
      this.allPaperList.some(item => {
        if (item.paperId === id) {
          name = item.paperName
          return true
        }
      })
      return name
    },
    getQuestionSubjectName(id) {
      let name = ''
      this.questionSubjectOptions.some(item => {
        if (item.subjectId === id) {
          name = item.subjectName
          return true
        }
      })
      return name
    },
    handleExamChange() {
      this.form.paperId = undefined
      this.getPaperList()
    },
    handleQueryExamChange() {
      this.queryParams.paperId = undefined
      this.getQueryPaperList()
    },
    getPaperList() {
      if (this.form.examId) {
        this.examList.map(item => {
          if (item.examId === this.form.examId) {
            this.paperList = item.paperList
          }
        })
      } else {
        this.paperList = []
      }
    },
    getQueryPaperList() {
      if (this.queryParams.examId) {
        this.examList.map(item => {
          if (item.examId === this.queryParams.examId) {
            this.queryPaperList = item.paperList
          }
        })
      } else {
        this.queryPaperList = []
      }
    },
    changeTopicStatus(row) {
      const text = row.status === '1' ? '停用' : '启用';
      this.$confirm('是否确认' + text + '?', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        TopicStatus({
          adminId: this.$store.state.user.userId,
          questionId: row.id,
          status: row.status === '1' ? '0' : '1'
        }).then(res => {
          this.msgSuccess("修改成功");
          this.getList();
        })
      });
    },
    questionTypeChange() {
      this.answerList.map(item => {
        item.correctAnswerFlag = false;
      })
    },
    handleAnswerClick(item, index) {
      if (this.form.questionType == '1') {
        this.answerList.map(item => {
          item.correctAnswerFlag = '0';
        })
        item.correctAnswerFlag = '1';
      } else {
        if (item.correctAnswerFlag == '1') {
          item.correctAnswerFlag = '0'
        } else {
          item.correctAnswerFlag = '1'
        }
      }
      this.$forceUpdate();
    },
    /** 查询列表 */
    getList(isFirst) {
      let queryParams = {
        adminId: this.$store.state.user.userId,
        page: this.queryParams.page.toString(),
        pageSize: "20",
        paperId: '',
        questionName: '',
        source: '',
        status: ''
      }
      this.loading = true;
      // queryParams.page = '1',
      queryParams.paperId = this.queryParams.paperId,
      queryParams.questionName = this.queryParams.questionName,
      queryParams.difficulty = this.queryParams.difficulty,
      queryParams.confidence = this.queryParams.confidence,
      queryParams.source = this.queryParams.source,
      queryParams.status = this.queryParams.status
      queryParams.questionId = this.queryParams.questionId
      queryParams.examId = this.queryParams.examId
      queryParams.questionSubject = this.queryParams.questionSubject
      GetTopicList(queryParams).then(response => {
        this.list = response.TopicList || [];
        if (response.totalRecord) {
          this.total = parseInt(response.totalRecord);
        }
        this.loading = false;
        const searchOptions = response.optionList;
        if (searchOptions) {
          this.examList = searchOptions.examList || [];
          if (this.examList.length) {
            this.allPaperList = []
            this.examList.map(item => {
              if (item.paperList && item.paperList.length) {
                this.allPaperList = this.allPaperList.concat(item.paperList)
              }
            })
          }
          this.statusOptions = searchOptions.status || [];
          this.sourceOptions = searchOptions.source || [];
          this.questionSubjectOptions = searchOptions.questionSubject || [];
          this.questionTypeOptions = searchOptions.questionType || [];
        }
      });
    },
    handleAddAnswer() {
      this.answerList.push({
        answerContentCn: '',
        answerContentEn: '',
        correctAnswerFlag: '0'
      })
    },
    handleRemoveAnswer(item, index) {
      this.answerList.splice(index, 1);
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isEdit = true;
      this.title = '题目新增';
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.jobId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.page = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      if (this.$refs['queryForm']) {
        this.$refs['queryForm'].resetFields();
      }
      this.queryParams.status = '1';
      this.handleQuery();
    },
    isBase64(str){
      if (str === '' || str.trim() === '') {
        return false;
      }
      try {
        return btoa(atob(str)) == str;
      } catch (err) {
        return false;
      }
    },
    decode(base64){
      if (!base64) return ''
      if (!this.isBase64(base64)) return decodeURI(base64)
      return Base64.decode(base64)
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      GetTopic({
        questionId: id,
        adminId: this.$store.state.user.userId
      }).then(response => {
        if (typeof response === 'string') {
          this.$message.error('数据解析失败')
          return;
        }
        response.clarify = this.decode(response.clarify)
        this.form = response;
        this.answerList = this.form.answerList;
        this.isEdit = true;
        this.title = '题目编辑';
        this.getPaperList()
      });
    },
    cancel() {
      this.isEdit = false;
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const correct = this.answerList.filter(item => item.correctAnswerFlag == '1');
          if (correct.length === 0 && this.form.questionType != '3') {
            this.$message.error('请设置正确答案');
            return false;
          }
          if (this.form.questionId != undefined) {
            let paperName = '试卷';
            this.paperList.map(item => {
              if (item.paperName === this.form.paperId) {
                paperName = item.paperName;
              }
            })
            let data = {
              adminId: this.$store.state.user.userId,
              paperId: this.form.paperId,
              examId: this.form.examId,
              paperName: paperName,
              questionId: this.form.questionId,
              questionNameEn: this.form.questionNameEn,
              questionNameCn: this.form.questionNameCn,
              difficulty: this.form.difficulty,
              confidence: this.form.confidence,
              clarify: Base64.encode(this.form.clarify),
              videoUrl: this.form.videoUrl,
              videoSign: this.form.videoSign,
              questionPoints: this.form.questionPoints.toString(),
              questionSubject: this.form.questionSubject,
              questionType: this.form.questionType,
              source: this.form.source,
              status: this.form.status,
              fileId: this.form.fileId,
              appId: this.form.appId,
              psign: this.form.psign,
              wxVideoUrl: this.form.wxVideoUrl,
              answerList: this.answerList
            }
            UpdateTopic(data).then(response => {
              this.msgSuccess("修改成功");
              this.isEdit = false;
              this.getList();
            });
          } else {
            let paperName = '试卷';
            this.paperList.map(item => {
              if (item.paperName === this.form.paperId) {
                paperName = item.paperName;
              }
            })
            let data = {
              adminId: this.$store.state.user.userId,
              paperId: this.form.paperId,
              examId: this.form.examId,
              paperName: paperName,
              questionId: this.form.questionId,
              questionNameEn: this.form.questionNameEn,
              questionNameCn: this.form.questionNameCn,
              difficulty: this.form.difficulty,
              confidence: this.form.confidence,
              clarify: Base64.encode(this.form.clarify),
              videoUrl: this.form.videoUrl,
              videoSign: this.form.videoSign,
              questionPoints: this.form.questionPoints.toString(),
              questionSubject: this.form.questionSubject,
              questionType: this.form.questionType,
              source: this.form.source,
              status: this.form.status,
              fileId: this.form.fileId,
              appId: this.form.appId,
              psign: this.form.psign,
              wxVideoUrl: this.form.wxVideoUrl,
              answerList: this.answerList
            }
            console.log(JSON.stringify(data))
            AddTopic(data).then(response => {
              this.msgSuccess("新增成功");
              this.isEdit = false;
              this.getList();
            });
          }
        } else {
          this.$nextTick(() => {
            this.getParentNode(document.querySelector('.el-form-item__error'))
          })
        }
      });
    },
    getParentNode(el) {
      if (el.getAttribute && el.getAttribute('role') === 'tabpanel') {
        let active = el.getAttribute('aria-labelledby').replace('tab-', '')
        this.activeName = active
      }
      if (el.parentNode) {
        this.getParentNode(el.parentNode)
      }
    },
    encode(str){
      if (!str) return ''
      // 对字符串进行编码
      let encode = encodeURI(str);
      // 对编码的字符串转化base64
      let base64 = btoa(encode);
      return base64;
    },
    // 表单重置
    reset() {
      this.form = {
        questionNameEn: undefined,
        questionNameCn: undefined,
        paperId: undefined,
        paperName: undefined,
        difficulty: undefined,
        confidence: undefined,
        clarify: undefined,
        videoUrl: undefined,
        videoSign: undefined,
        source: 'OS',
        questionType: '1',
        questionSubject: '1',
        questionPoints: 1,
        status: "1"
      };
      this.answerList = [
        {
          answerContentCn: '',
          answerContentEn: '',
          correctAnswerFlag: '0'
        }
      ]
      if (this.$refs['form']) {
        this.$refs['form'].resetFields();
      }
    },
  }
}
</script>

<style scoped lang="less">
.edit-form {
  /deep/ .el-input__inner, /deep/ .el-textarea__inner {
    font-size: 16px;
  }
}
.card {
  background-color: #ffffff;
  box-shadow: 0px 10px 10px 0px rgba(184, 190, 204, 0.08);
  border-radius: 6px;
  margin-bottom: 20px;
}
/deep/ .el-input-group__append {
  background: transparent;
  cursor: pointer;
}
.answer-checked {
  color: #409EFF;
}

</style>
