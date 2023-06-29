<template>
  <div>
    <h1>profile</h1>
    <h2>sadasddddddddddd</h2>
    {{querb.name}}
    {{querb.sex}}
  </div>
</template>

<script>
export default {
  name: "Profile",
  computed:{
    querb(){
      return this.$route.query
    }
  }
}
</script>

<style scoped>

</style>