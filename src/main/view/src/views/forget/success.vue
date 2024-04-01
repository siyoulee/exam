<template>
  <div>
    <h2>
      <span>密码已重置成功，请 </span>
      <router-link to="/login" style="color: #67C23A;">重新登录</router-link>
    </h2>
  </div>
</template>

<script>
export default {
  name: "success"
}
</script>

<style scoped lang="less">
h2 {
  text-align: center;
  padding: 60px 0 40px;
  font-weight: 700;
  color: #666;
}
</style>