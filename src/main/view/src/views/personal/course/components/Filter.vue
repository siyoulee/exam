<template>
  <div class="filter">
    <dl>
      <dt>课程名称</dt>
      <dd>
        <span class="type-item" v-for="(item, index) in examList" :key="index" @click="handleClick(item, index)" :class="examId === item.examId && 'active'">{{ item.examName }}</span>
      </dd>
    </dl>
    <dl v-if="isShowType">
      <dt>题目类别</dt>
      <dd>
        <span class="type-item" v-for="(item, index) in questionSubject" :key="index" @click="handleClick1(item, index)" :class="questionSubjectId === item.id && 'active'">{{ item.name }}</span>
      </dd>
    </dl>
  </div>
</template>

<script>
import { GetFilterList } from '@/api/course'
export default {
  name: "Filter",
  props: {
    isShowType: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      examId: '',
      examList: [],
      questionSubjectId: '',
      questionSubject: []
    }
  },
  mounted() {
    this.GetFilterList()
  },
  methods: {
    GetFilterList() {
      GetFilterList({
        userId: this.$store.state.user.userId
      }).then(res => {
        this.examList = res.examList || []
        this.questionSubject = res.questionSubject || []
        if (this.examList.length) {
          this.examId = this.examList[0].examId
        }
        if (this.questionSubject.length) {
          this.questionSubjectId = this.questionSubject[0].id
        }
        this.$emit('change', {
          examId: this.examId,
          examName: this.examList[0].examName,
          questionSubject: this.questionSubjectId
        })
      })
    },
    handleClick(item, index) {
      this.examId = item.examId
      this.$emit('change', {
        examId: this.examId,
        examName: item.examName,
        questionSubject: this.questionSubjectId
      })
    },
    handleClick1(item, index) {
      this.questionSubjectId = item.id
      this.$emit('change', {
        examId: this.examId,
        examName: item.examName,
        questionSubject: this.questionSubjectId
      })
    }
  }
}
</script>

<style scoped lang="less">
.filter {
  background-color: #fff;
  padding: 30px 30px 10px;
  border-radius: 6px;
  dl {
    display: flex;
    margin-bottom: 10px;
    dt {
      font-size: 16px;
      font-weight: 700;
      color: #11233f;
      width: 90px;
      line-height: 28px;
    }
    dd {
      flex: 1;
    }
  }
}
.type-item {
  display: inline-block;
  padding: 0 20px;
  line-height: 28px;
  margin: 0 10px 10px 0;
  cursor: pointer;
}
.type-item.active {
  background: #fff;
  box-shadow: 0 0 10px #e4e4e4;
  color: #e31436;
  padding: 0 20px;
  border-radius: 33px;
}
</style>