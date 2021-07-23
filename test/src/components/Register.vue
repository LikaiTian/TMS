<template>
  <div class="registe_container">
    <img src="../image/8.jpg" class="bodyImg" />
    <div class="registe_box">
      <!-- 头像 -->
      <div class="registeimg_box">
        <img src="../assets/logo.png" />
      </div>
      <!-- 表单区域 -->
      <el-form
        ref="registeFormRef"
        :rules="registeRules"
        :model="registeForm"
        class="registe_form"
        label-width="0"
      >
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input
            v-model="registeForm.username"
            prefix-icon="iconfont icon-denglu"
            placeholder="昵称"
          ></el-input>
        </el-form-item>
        <!-- 公司名字 -->
        <el-form-item prop="company">
          <el-input
            v-model="registeForm.company"
            prefix-icon="iconfont icon-gongsi"
            placeholder="公司名"
          ></el-input>
        </el-form-item>
        <!-- 电话 -->
        <el-form-item>
          <el-input
            v-model="registeForm.phonenum"
            prefix-icon="iconfont icon-dianhua01"
            placeholder="电话"
            @change="phone"
          ></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item>
          <el-input
            v-model="registeForm.email"
            prefix-icon="iconfont icon-email"
            placeholder="邮箱"
            @change="email"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item>
          <el-input
            v-model="registeForm.password"
            prefix-icon="iconfont icon-mima"
            type="password"
            placeholder="密码"
            show-password
            @change="password"
          ></el-input>
        </el-form-item>
        <!--再次输入密码-->
        <el-form-item>
          <el-input
            v-model="registeForm.repassword"
            prefix-icon="iconfont icon-mima"
            type="password"
            show-password
            placeholder="再次输入密码"
          ></el-input>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="registe" icon="iconfont icon-zhuce"
            >注册</el-button
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
      registeForm: {
        username: "",
        company: "",
        phonenum: "",
        email: "",
        password: "",
      },
      //校验规则
      registeRules: {
        //blur 验证时机
        username: [
          { required: true, message: "请设置用户名", trigger: "blur" },
          { min: 2, max: 10, message: "长度在2-10个字符", trihger: "blur" },
        ],
      },
    };
  },
  created() {
    this.keyupSubmit();
  },
  methods: {
    //键盘监视
    keyupSubmit() {
      document.onkeydown = (e) => {
        let _key = window.event.keyCode;
        if (_key === 13) {
          this.registe();
        }
      };
    },
    //验证密码
    password: function () {
      var pass1 = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;
      var pass2 =
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,}$/;
      if (
        !pass1.test(this.registeForm.password) &&
        !pass2.test(this.registeForm.password)
      ) {
        this.$message({
          duration: 1200,
          message: "密码最少六个字符，至少一个字母，一个数字",
          type: "error",
        });
        this.registeForm.password = "";
        return;
      }
    },
    //验证电话
    phone: function () {
      if (!/^1[345789]\d{9}$/.test(this.registeForm.phonenum)) {
        this.$message({
          duration: 800,
          message: "请输入正确的手机号！",
          type: "error",
        });
        this.registeForm.phonenum = "";
        return;
      }
    },
    //验证邮箱
    email: function () {
      var email =
        /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
      if (!email.test(this.registeForm.email)) {
        this.$message({
          duration: 800,
          message: "请输入正确的邮箱！",
          type: "error",
        });
        this.registeForm.email = "";
        return;
      }
    },
    //注册账号
    registe() {
      //验证校验规则
      this.$refs.registeFormRef.validate(async (valid) => {
        //验证失败
        if (!valid) return;
        //验证两次密码是否相同
        if (this.registeForm.password != this.registeForm.repassword) {
          this.$message({
            duration: 800,
            message: "两次输入密码不一致，请重新输入！",
            type: "error",
          });
          return;
        }
        //访问后台
        const { data: res } = await this.$http.get(
          "user/register?" +
            "nickname=" +
            this.registeForm.username.toString() +
            "&companyName=" +
            this.registeForm.company.toString() +
            "&phone=" +
            this.registeForm.phonenum.toString() +
            "&email=" +
            this.registeForm.email.toString() +
            "&password=" +
            this.registeForm.password.toString()
        );
        if (res.code == 0) {
          //信息提示
          this.$message({
            duration: 800,
            message: "注册成功，请登录！",
            type: "success",
          });
          //页面跳转
          this.$router.push({ path: "/login" });
          console.log(res);
        } else {
          //信息提示
          this.$message({
            duration: 800,
            message: "用户已存在！",
            type: "error",
          });
          console.log(res);
        }
      });
    },
  },
};
</script>
<style lang="less" scoped>
.registe_container {
  background-color: #2b4b6b;
  height: 100%;
}
.registe_box {
  opacity: 0.75;
  width: 450px;
  height: 450px;
  background-color: #fff;
  border-radius: 10px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .registeimg_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 5px;
    box-shadow: 0 0 5px #ddd;
    position: absolute;
    left: 50%;
    bottom: 87%;
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
  .registe_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
  }
}
.bodyImg {
  top: 10%;
  height: 100%;
  width: 100%;
}
</style>