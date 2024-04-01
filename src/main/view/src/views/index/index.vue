<template>
  <div>

  </div>
</template>

<script>
import { getPaperList } from '@/api/course';
export default {
  name: 'Index',
  components: {

  },
  data() {
    return {
    }
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
          if (res.paperList.length > 1) {
            this.list = res.paperList.splice(1);
          }
        }
      })
    }
  }
}
</script>

<style lang="less">

</style>
