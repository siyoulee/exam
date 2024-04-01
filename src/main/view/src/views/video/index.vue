<template>
  <div class="bg">
    <div class="container">
      <div class="filter">
        <dl>
          <dt>类别</dt>
          <dd>
            <span class="type-item" :class="categoryIndex === -1 && 'active'" @click="handleSearch()">全部</span>
            <span class="type-item" v-for="(item, index) in categoryList" :key="index" @click="handleSearch(item, index)" :class="categoryIndex === index && 'active'">{{ item.examName }}</span>
          </dd>
        </dl>
      </div>
      <ul v-if="list.length" class="video-list">
        <li class="video-item" v-for="(item, index) in list" :key="index" @click="handleViewVideo(item)">
          <div class="video-item-image">
            <img :src="item.videoPhoto" alt="">
          </div>
          <div class="video-item-content">
            <div class="video-item-title">{{item.videoName}}</div>
            <div class="video-item-text mb10">
              <p class="color-999 mt5">{{item.videoDesc}}</p>
            </div>
            <div class="video-item-footer">
              <span>{{item.teacherName}}</span>
              <span>{{item.publishDate}}</span>
            </div>
            <div class="video-item-footer">
              <span></span>
              <span>{{item.videoLength || 0}}分钟</span>
            </div>
          </div>
        </li>
      </ul>
      <el-empty v-else description="暂无数据"></el-empty>
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
import { VideoList } from '@/api/course';
export default {
  name: "video",
  data() {
    return {
      categoryIndex: -1,
      categoryList: [],
      list: [],
      categoryId: '',
      dialogVisible: false,
      player: null
    }
  },
  created() {
    this.list = [];
    this.getList();
  },
  methods: {
    handleSearch(item, index) {
      if (item) {
        this.categoryIndex = index
        this.categoryId = item.examId
      } else {
        this.categoryIndex = -1
        this.categoryId = ''
      }
      this.getList();
    },
    getList() {
      VideoList({
        userId: this.$store.state.user.userId,
        examId: this.categoryId
      }).then(res => {
        this.categoryList = res.examList || []
        this.list = res.videoList || [];
      })
    },
    handleViewVideo(item) {
      // fileID = '387702300194019998'
      if (item.fileId) {
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
  }
}
</script>

<style scoped lang="less">
.bg {
  background-color: #f6f7f9;
  height: 100%;
}
.container {
  width: 100%;
  margin: 0 auto;
  padding: 0;
  box-sizing: border-box;
}
.filter {
  background-color: #fff;
  padding: 30px 30px 20px;
  border-radius: 6px;
  margin-bottom: 20px;
  dl {
    display: flex;
    dt {
      font-size: 16px;
      font-weight: 700;
      color: #11233f;
      width: 70px;
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
.video-list {
  display: flex;
  flex-wrap: wrap;
  height: calc(100vh - 240px);
  overflow: auto;
  .video-item {
    width: 205px;
    margin-right: 30px;
    cursor: pointer;
    transition: all .3s;
    margin-bottom: 20px;
    img {
      border-radius: 8px 8px 0 0;
    }
    &:hover {
      box-shadow: 0 8px 16px 0 rgba(7, 17, 27, 0.1);
      transform: translateY(-8px);
    }
    .video-item-content {
      background-color: #fff;
      padding: 10px 15px;
      box-sizing: border-box;
      height: 168px;
      color: #7a8392;
      position: relative;
      border-radius: 0 0 8px 8px;
    }
    .video-item-title {
      height: 43px;
      font-size: 14px;
      word-break: break-all;
      line-height: 23px;
      cursor: pointer;
      font-weight: 700;
      color: #414f65;
      overflow: hidden;
    }
    .video-item-footer {
      display: flex;
      align-items: center;
      justify-content: space-between;
      font-size: 12px;
    }
  }
}
</style>
