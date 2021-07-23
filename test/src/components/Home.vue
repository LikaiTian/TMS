<template>
  <div class="hello">
    <el-menu
      :default-active="$route.path"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <el-menu-item index="1">
        <template slot="title">
          <i class="el-icon-refresh-left"></i>
        </template>
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-more"></i>
          <span>员工操作</span>
        </template>
        <el-menu-item index="2-1">
          <template slot="title">
            <i class="el-icon-circle-plus-outline"></i>
            <span>添加员工</span>
          </template>
        </el-menu-item>
      </el-submenu>

      <el-menu-item index="3">消息中心</el-menu-item>

      <el-submenu index="4" class="admin">
        <template slot="title">
          <!-- <i class="el-icon-s-custom" ></i> -->
        </template>
        <el-menu-item index="4-1">
          <template slot="title">
            <i class="el-icon-notebook-2"></i>
            <span>个人信息</span>
          </template>
        </el-menu-item>
        <el-menu-item index="4-2">
          <template slot="title">
            <i class="el-icon-edit"></i>
            <span>更换头像</span>
          </template>
        </el-menu-item>
        <el-menu-item index="4-3">
          <template slot="title">
            <i class="el-icon-loading"></i>
            <span>退出登录</span>
          </template>
        </el-menu-item>
      </el-submenu>
    </el-menu>
    <!-- 侧拉列表 -->
    <el-row class="tac">
      <el-col :span="null">
        <el-menu
          :default-active="$route.path"
          :unique-opened="false"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          @select="handleSelect1"
          :default-openeds="selectedIndexs"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>我的公司</span>
            </template>

            <el-menu-item-group>
              <el-menu-item index="1-1">
                <i class="el-icon-user"></i>
                <span>员工总览</span>
              </el-menu-item>
            </el-menu-item-group>

            <el-submenu index="1-2">
              <template slot="title">
                <i class="el-icon-s-home"></i>
                <span>部门建设</span>
              </template>

              <el-menu-item-group>
                <el-menu-item index="1-2-1">
                  <i class="iconfont icon-yuangong"></i>
                  <span>人员分布</span>
                </el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="1-2-2">
                  <i class="iconfont icon-pingjia"></i>
                  <span>薪资水平</span>
                </el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="1-2-3">
                  <i class="iconfont icon-liebiao"></i>
                  <span>薪资统计</span>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-submenu>

          <el-menu-item index="2">
            <i class="el-icon-aim"></i>
            <span>大数据</span>
          </el-menu-item>

        </el-menu>
      </el-col>
    </el-row>

    <el-main style="background-color: rgb(193, 219, 208)">
      <router-view></router-view>
    </el-main>

    <!-- 头像 -->
    <div class="portrait">
      <img :src="adatar" alt="" />
    </div>
  </div>
</template>
<script>
import routers from "../router";

export default {
  inject: ["reload"],
  data() {
    return {
      selectedIndexs: ["1", "1-2"],
      activeIndex: "1",
      company: "",
      index: "",
      id: "",
      portrait: "",
      //头像
      adatar: "",
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.company = this.$route.query.company;
    this.adatar = this.$route.query.adatar;
    this.portrait =
      // 初始化头像
      this.$http
        .get("user/findAddress?" + "id=" + this.id)
        .then((res) => {
          if (res.data.date == null) return;
          this.adatar = res.data.date;
          console.log(this.adatar);
        })
        .catch((err) => {
          this.$message({
            duration: 600,
            message: "头像加载失败！",
            type: "error",
          });
        });
    //this.addData();
  },
  watch: {
    $route(to, from) {
      console.log(from.path);
      if (from.path == "/portrait") {
        this.$http
          .get("user/findAddress?" + "id=" + this.id)
          .then((res) => {
            if (res.data.date == null) return;
            this.adatar = res.data.date;
            console.log("watch" + this.adatar);
          })
          .catch((err) => {
            this.$message({
              duration: 600,
              message: "头像加载失败！",
              type: "error",
            });
          });
      }
    },
  },
  methods: {
    addData() {
      this.$http
        .get(
          "employ/add?" +
            "cardId=" +
            "123456" +
            "&name=" +
            "张三" +
            "&sex=" +
            "男" +
            "&birth=" +
            "2000-7-1" +
            "&phone=" +
            "19812949998" +
            "&company=" +
            this.company +
            "&department=" +
            "财务部" +
            "&job=" +
            "普通员工"
        )
        .then((res) => {
          console.log(res);
        });
    },

    navigate() {
      routers.go(-1);
    },
    //上方导航栏
    handleSelect(key) {
      switch (key) {
        case "1":
          this.index = key;
          if (this.$route.path == "/employee") {
            // console.log(this.$route.path);
            this.$message({
              duration: 600,
              message: "刷新成功",
              type: "success",
            });
            return;
          }
          this.$message({
            duration: 600,
            message: "刷新成功",
            type: "success",
          });
          this.$router.push({
            path: this.$route.path,
            query: { company: this.company, index: this.index },
          });

          break;
        //添加员工
        case "2-1":
          this.$router.push({
            path: "/employeeForm",
            query: { company: this.company, id: this.id },
          });
          break;
        //消息记录
        case "3":
          this.$router.push({
            path: "/operateInfo",
            query: { id: this.id },
          });
          break;
        //个人信息
        case "4-1":
          this.$router.push({
            path: "/myInfo",
            query: { company: this.company, id: this.id },
          });
          break;
        //修改头像
        case "4-2":
          this.$router.push({
            path: "/portrait",
            query: { id: this.id, company: this.company, adatar: this.adatar },
          });
          break;
        //退出登录
        case "4-3":
          this.$message({
            duration: 600,
            message: "已退出,请登录！",
            type: "success",
          });
          window.sessionStorage.clear();
          this.$router.push({ path: "/锐锋" });
          break;
        default:
          break;
      }
    },
    //左侧导航栏
    handleSelect1(key) {
      switch (key) {
        // case "1-1":
        //   this.$router.push({
        //     path: "/company",
        //     query: { company: this.company },
        //   });
        //   break;
        case "1-1":
          this.$router.push({
            path: "/employee",
            query: { company: this.company },
          });
          break;
        case "1-2-1":
          this.$router.push({
            path: "/deptNum",
            query: { company: this.company },
          });
          break;
        case "1-2-2":
          this.$router.push({
            path: "/deptAvg",
            query: { company: this.company },
          });
          break;
        case "1-2-3":
          this.$router.push({
            path: "/salaryIndex",
            query: { company: this.company },
          });
          break;
        case "2":
          this.$router.push({
            path: "/dataSet",
            query: { company: this.company },
          });
          break;

        default:
          break;
      }
    },
    handleOpen() {},
    handleClose() {},
  },
};
</script>
<style lang="less" scoped>
.body {
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
.tac {
  display: block;
  position: absolute;
  left: 0;
  height: 90%;
  bottom: 0;
  background-color: #545c64;
  border-right: 0 !important;
  width: 20%;
}
.admin {
  display: block;
  position: absolute;
  right: 0;
  border-right: 0 !important;
}
.portrait {
  display: block;
  position: absolute;
  right: 4%;
  top: 1%;
  width: 55px;
  height: 55px;
  background-color: #545c64;
  img {
    object-fit: cover;
    object-position: center;
    width: 100%;
    height: 100%;
    border-radius: 50%;
  }
}
</style>