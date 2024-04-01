<template>
  <div class="pad20">
    <filter-tab @change="getList" :isShowType="false" />
    <ul class="list">
      <li class="list-item" v-for="(item, index) in list" :key="index">
        <div class="list-item-image">
          <img :src="item.paperPhoto" alt="">
        </div>
        <div class="list-item-content">
          <div class="list-item-text">
            <p class="color-333 fz16">{{item.paperName}}</p>
            <p class="color-999 mt10">{{item.paperDesc}}</p>
          </div>
          <button class="theme-btn list-item-btn" @click="handleDetail(item)">学习</button>
        </div>
      </li>
      <el-empty v-if="list.length === 0" description="暂无数据"></el-empty>
    </ul>
    <pagination
        v-show="total>0"
        :total="total"
        @pagination="getList"
    />
  </div>
</template>

<script>
import { getPaperList } from '@/api/course';
import FilterTab from "./components/Filter";
export default {
  name: "simulation",
  components: {
    FilterTab
  },
  data() {
    return {
      total: 10,
      list: [],
      params: {}
    }
  },
  created() {
    this.list = [];
  },
  activated() {
    this.getList()
  },
  methods: {
    getList(data) {
      if (data) {
        this.params = data
      }
      if (Object.keys(this.params).length === 0) return
      getPaperList({
        practiseType: '4',
        userId: this.$store.state.user.userId,
        ...this.params,
        questionSubject: '2'
      }).then(res => {
          this.list = res.paperList || [];
      })
    },
    handleDetail(row) {
      this.$router.push({
        path: '/examination',
        query: {
          id: row.id,
          practiseType: 4,
          questionSubject: this.params.questionSubject,
          examId: this.params.examId
        }
      });
    }
  }
}
</script>

<style scoped>

</style>