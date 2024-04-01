<template>
  <div :class="{'hidden':hidden}" class="pagination-container">
    <div class="pagination-btn" @click="perv">上一页</div>
    <el-pagination
      class="pagination"
      ref="pagination"
      :background="background"
      :current-page.sync="currentPage"
      :page-size.sync="pageSize"
      :layout="layout"
      :page-sizes="pageSizes"
      :pager-count="pagerCount"
      :total="total"
      v-bind="$attrs"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <div class="pagination-btn" @click="next">下一页</div>
  </div>
</template>

<script>
export default {
  name: 'Pagination',
  props: {
    total: {
      required: true,
      type: Number
    },
    page: {
      type: Number,
      default: 1
    },
    limit: {
      type: Number,
      default: 20
    },
    pageSizes: {
      type: Array,
      default() {
        return [10, 20, 30, 50]
      }
    },
    // 移动端页码按钮的数量端默认值5
    pagerCount: {
      type: Number,
      default: document.body.clientWidth < 992 ? 5 : 7
    },
    layout: {
      type: String,
      default: 'pager' //'total, sizes, prev, pager, next, jumper'
    },
    background: {
      type: Boolean,
      default: true
    },
    autoScroll: {
      type: Boolean,
      default: true
    },
    hidden: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    currentPage: {
      get() {
        return this.page
      },
      set(val) {
        this.$emit('update:page', val)
      }
    },
    pageSize: {
      get() {
        return this.limit
      },
      set(val) {
        this.$emit('update:limit', val)
      }
    }
  },
  methods: {
    perv() {
      this.$refs.pagination.prev()
    },
    next() {
      this.$refs.pagination.next()
    },
    handleSizeChange(val) {
      this.$emit('pagination', { page: this.currentPage, limit: val })
    },
    handleCurrentChange(val) {
      this.$emit('pagination', { page: val, limit: this.pageSize })
    }
  }
}
</script>

<style scoped lang="less">
.pagination-container {
  background: #fff;
  padding: 25px 15px;
  text-align: center;
  /deep/ .el-pagination {
    padding: 0 5px;
  }
  /deep/ .el-pager li {
    min-width: 34px;
    height: 34px;
    line-height: 32px;
    background: #FFFFFF;
    border: 1px solid #DADADA;
    border-radius: 5px;
    box-sizing: border-box;
    color: #999999;
    font-width: 400;
  }
  /deep/ .el-pager li:not(.disabled).active {
    background: #409EFF;
    border: 1px solid #409EFF;
  }
  /deep/ .el-pager li:not(.disabled):hover {
    color: #409EFF;
  }
  /deep/ .el-pager li:not(.disabled).active:hover {
    color: #fff;
  }
}
.pagination-container.hidden {
  display: none;
}
.pagination {
  display: inline-block;
  vertical-align: top;
}
.pagination-btn {
  display: inline-block;
  width: 75px;
  height: 34px;
  line-height: 32px;
  background: #FFFFFF;
  border: 1px solid #DADADA;
  border-radius: 5px;
  text-align: center;
  box-sizing: border-box;
  color: #999999;
  cursor: pointer;
  user-select: none;
}
</style>
