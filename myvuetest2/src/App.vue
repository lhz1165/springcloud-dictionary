<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
name:"app",
// mounted() {
//   //刷新vuex消失
//   window.addEventListener('unload',this.saveState)
// },
// methods:{
//   saveState(){
//     localStorage.setItem('state',JSON.stringify(this.$store.state))
//   }
// }

}
</script>
<style>
html,body,.el-container{
  padding: 0px;
  margin: 0px;
  height: 100%;
}
</style>
