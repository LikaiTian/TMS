<template>
  <div>
    <el-carousel
      :interval="3000"
      type="card"
      :height="bannerHeight + 'px'"
      indicator-position=""
    >
      <el-carousel-item v-for="item in images" :key="item.url">
        <img :src="item.url" class="img" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
export default {
  data() {
    return {
      bannerHeight: 100,
      images: [
        { url: require("../image/1.jpg") },
        { url: require("../image/2.jpg") },
        { url: require("../image/3.jpg") },
        { url: require("../image/4.jpg") },
        { url: require("../image/5.jpg") },
        { url: require("../image/6.jpg") },
        { url: require("../image/7.jpg") },
      ],
    };
  },
  mounted() {
    this.setSize();
    window.addEventListener(
      "resize",
      () => {
        this.setSize();
      },
      false
    );
  },
  methods: {
    setSize() {
      this.bannerHeight = document.body.clientWidth / 3.5;
    },
  },
};
</script>
<style lang="less" scoped>
.img{
  width: 100%;
  height: inherit;
  }
</style>