<template>
  <div class="login_container">
    <img src="../image/8.jpg" class="bodyImg" />
    <!-- 登录块 -->
    <div class="login_box">
      <!-- 头像 -->
      <div class="avater_box">
        <img src="../assets/logo.png" />
      </div>
      <!-- <router-link :to="{path:'/home', query:{id:123}}">home</router-link> -->
      <!-- 表单区域 -->
      <el-form
        ref="loginFormRef"
        :rules="loginRules"
        :model="loginForm"
        class="login_form"
        label-width="0"
      >
        <h2 class="title">锐锋管理</h2>

        <!-- 用户名 -->
        <el-form-item>
          <el-input
            v-model="loginForm.username"
            prefix-icon="iconfont icon-denglu"
            placeholder="手机号"
            @change="phone"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            prefix-icon="iconfont icon-mima"
            type="password"
            show-password
            placeholder="密码"
          ></el-input>
        </el-form-item>
        <!--不想链接 <router-link to="Register.vue">注册</router-link> -->
        <!-- 按钮 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="login" icon="iconfont icon-tijiao"
            >登录</el-button
          >
          <el-button
            type="info"
            @click="resetLoginForm"
            icon="iconfont icon-zhongzhi"
            >重置</el-button
          >
          <el-button type="" @click="register" icon="iconfont icon-zhuce"
            >注册</el-button
          >
          <el-button
            type=""
            @click="findPassword"
            icon="iconfont icon-wangjimima"
            >忘记密码？</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //表单数据
      loginForm: {
        username: "",
        password: "",
      },
      id: "",
      company: "",
      //校验规则
      loginRules: {
        //blur 验证时机

        //本地
        // username: [
        //   { required: true, message: "请输入用户名", trigger: "blur" },
        //   { min: 3, max: 10, message: "长度为3 ~ 10个字符", trihger: "blur" },
        // ],
        // password: [
        //   { required: true, message: "请输入用户密码", trigger: "blur" },
        //   { min: 6, max: 15, message: "长度在3 ~ 15个字符", trihger: "blur" },
        // ]
        //服务器
        password: [
          { required: true, message: "请输入用户密码", trigger: "blur" },
          { min: 6, max: 15, message: "长度在6 ~ 15个字符", trihger: "blur" },
        ],
      },
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.company = this.$route.query.company;
    this.keyupSubmit();
  },
  methods: {
    //重置表单内容
    resetLoginForm() {
      this.loginForm.username = "";
      this.loginForm.password = "";
      // this.$refs.loginFormRef.resetFields();
    },
    //键盘监视
    keyupSubmit() {
      document.onkeydown = (e) => {
        let _key = window.event.keyCode;
        if (_key === 13) {
          this.login();
        }
      };
    },
    phone: function () {
      if (!/^1[345789]\d{9}$/.test(this.loginForm.username)) {
        this.$message({
          duration: 800,
          message: "请输入正确的手机号！",
          type: "error",
        });
        this.loginForm.username = "";
        return;
      }
    },
    //验证登录信息
    login() {
      //验证校验规则
      this.$refs.loginFormRef.validate(async (valid) => {
        //验证失败
        if (!valid) return;

        //本地api
        // const { data: res } = await this.$http.post("/login", this.loginForm);
        // if (res.flag == "ok") {
        //   //信息提示
        //   this.$message({
        //   duration: 600,
        //   message: "登录成功！",
        //   type: "success",
        // });
        //   //页面跳转5-3
        //   this.$router.push({ path: "/home", query: { id: res.user.id } });
        // } else {
        //   //信息提示
        //      this.$message({
        //   duration: 600,
        //   message: "登录失败！",
        //   type: "error",
        // });
        //   console.log(res);
        // }
        // 服务器api
        const { data: res } = await this.$http.get(
          "user/login?" +
            "phone=" +
            this.loginForm.username.toString() +
            "&password=" +
            this.loginForm.password.toString()
        );
        if (res.code == 0) {
          //信息提示
          this.$message({
            duration: 800,
            message: "登录成功！",
            type: "success",
          });
          //页面跳转：带参数
          sessionStorage.setItem("token", "true");
          this.$router.push({
            path: "/home",
            query: { company: res.date.companyName, id: res.date.id },
          });
          //不带参数
          // this.$router.push({
          //   path: "/home",
          // });
          //console.log(res);
        } else {
          //信息提示
          this.$message({
            duration: 800,
            message: "密码或手机号错误，请重新输入！",
            type: "error",
          });
          console.log(res);
        }
      });
    },
    //注册
    register() {
      this.$router.push("/register");
    },
    findPassword() {
      this.$router.push("/findpassword");
    },
  },
};
</script>

<style lang="less" scoped>
.login_container {
  background-color: rgb(193, 219, 208);
  height: 100%;
}
.login_box {
  opacity: 0.75;
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 10px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .avater_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 5px;
    box-shadow: 0 0 5px #ddd;
    position: absolute;
    left: 50%;
    bottom: 80%;
    transform: translate(-50%, -50%);
    background-color: #0ee;
    img {
      width: 100%;
      height: 100%;
      background-color: #eee;
      border-radius: 50%;
    }
  }
  .btns {
    display: flex;
    justify-content: flex-end;
  }
  .login_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
  }
  .title {
    text-align: center;
  }
}
.bodyImg {
  top: 10%;
  height: 100%;
  width: 100%;
}
</style>
