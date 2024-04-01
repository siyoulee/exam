<template>
  <div class="pad20">
    <filter-tab @change="getList" />
    <ul class="list">
      <li class="list-item" v-for="(item, index) in list" :key="index">
        <div class="list-item-image">
          <img :src="item.paperPhoto" alt="">
        </div>
        <div class="list-item-content">
          <div class="list-item-text">
            <p class="color-333 fz16">{{item.paperName}}</p>
            <p class="color-999 mt10">{{item.paperDesc}}</p>
            <p style="color: red;" class="mt10">收藏数：{{item.totalCount}}</p>
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
  name: "exercise",
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
        practiseType: 2,
        userId: this.$store.state.user.userId,
        ...this.params
      }).then(res => {
        if (res.paperList) {
          this.list = res.paperList;
        }
      })
    },
    handleDetail(row) {
      this.$router.push({
        path: '/examination',
        query: {
          id: row.paperId,
          practiseType: 2,
          ...this.params
        }
      });
    }
  }
}
</script>

<style scoped>

</style>