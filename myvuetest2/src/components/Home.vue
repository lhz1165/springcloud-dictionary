<template>
  <div>
    <h1>我是Home</h1>
    <h2>1111111111111</h2>
    <router-link to="/home/news">news</router-link>
    <router-link to="/home/message">message</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Home"
}
</script>

<style scoped>

</style>