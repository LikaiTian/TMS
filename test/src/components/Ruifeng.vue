<template>
  <div>
    <img src="../image/0.jpg" class="bodyImg" />
    <el-menu
      :default-active="$route.path"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
    </el-menu>
    <h1 class="title">锐锋管理</h1>

    <el-button type="" @click="login" icon="el-icon-s-promotion" class="login"
      >log</el-button
    >

    <el-main class="light">
      <router-view></router-view>
    </el-main>

    <span class="main"><a :href="'https://www.baidu.com'">关于我们</a></span>
    <span class="body1">锐锋作为企业跨组织人才管理系统，功能齐全，能够实现对企业人员的有效管理，同时我们这款产品UI设计较清新，能够给Hr很好的使用体验，期待您的使用</span>
    <span class="body2">Tel:19812949996</span>
  </div>
</template>
<script>
import routers from "../router";

export default {
  data() {
    return {
      drawer: false,
      innerDrawer: false,
    };
  },
  created() {
    this.$router.push("/light");
  },
  methods: {
    login() {
      this.$router.push("/login");
      this.$message({
        duration: 600,
        message: "请登录",
        type: "success",
      });
    },
    handleSelect() {},
    handleClose(done) {
      this.$confirm("还有未保存的工作哦确定关闭吗？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
  },
};
</script>
<style lang="less" scoped>
.bodyImg {
  top: 10%;
  height: 90%;
  width: 100%;
}
img {
  position: absolute;
}
.light {
  transform: translate(0%, 20%);
}
.login {
  display: block;
  position: absolute;
  top: 2%;
  right: 2%;
  width: 100px;
  background-color: rgb(193, 219, 208);
}
.el-menu-demo {
  display: block;
  position: absolute;
  top: 0;
  width: 100%;
  height: 10%;
  background-color: #545c64;
}
.title {
  display: block;
  position: absolute;
  text-align: center;
  top: 0;
  width: 100%;
  height: 10%;
}
.main {
  opacity: 0.8;
  display: block;
  position: absolute;
  font-size: 24px;
  left: 0%;
  top: 85%;
  width: 100%;
  height: 6%;
  background-color: rgb(164, 190, 158);
}
.body1 {
  opacity: 0.8;
  display: block;
  position: absolute;
  font-size: 16px;
  text-align: center;
  font-family:"Microsoft YaHei";
  left: 0%;
  top: 91%;
  width: 100%;
  height: 5%;
  background-color: rgb(164, 190, 158);
}
.body2 {
 opacity: 0.8;
  display: block;
  position: absolute;
  font-size: 18px;
  text-align: right;
  right: 0%;
  top: 95%;
  width: 100%;
  height: 5%;
  background-color: rgb(164, 190, 158);
}
</style>
