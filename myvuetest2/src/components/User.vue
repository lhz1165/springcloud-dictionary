<template>
  <div>
    <h1>User1111</h1>
    <p>user{{userid}} 22222222222</p>
  </div>

</template>

<script>
export default {
  name: "User",
  computed:{
    userid(){
      return this.$route.params.user_id
    }
  }
}
</script>

<style scoped>

</style>