<template>
  <div>
    <span class="top" style="line-height: 140px">锐锋公司简介</span>
    <span class="text"></span>
  </div>
</template>
<script>
import echarts from 'echarts'
export default {
  data() {
    return {};
  },
};
</script>
<style lang="less" scoped>
.top {
  display: block;
  position: absolute;
  text-align: center;
  font-size: 30px;
  top: 10%;
  width: 80%;
  right: 0;
  height: 15%;
  background-color: rgb(58, 132, 138);
}
.text {
  position: absolute;
  top: 25%;
  right: 0;
  width: 80%;
  height: 75%;
  background-color: rgb(15, 170, 175);
}
</style>