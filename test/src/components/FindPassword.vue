<template>
  <div class="find_container">
    <img src="../image/8.jpg" class="bodyImg" />
    <!-- 登录块 -->
    <div class="find_box">
      <div class="avater_box">
        <img src="../assets/logo.png" />
      </div>
      <el-form
        ref="FindFormRef"
        :model="findForm"
        class="find_form"
        label-width="0"
      >
        <h2 class="title">找回密码</h2>

        <!-- 手机号 -->
        <el-form-item>
          <el-input
            v-model="findForm.phone"
            prefix-icon="iconfont icon-denglu"
            placeholder="手机号"
            @change="phone"
          ></el-input>
        </el-form-item>
        <!-- 验证码 -->
        <el-form-item>
          <el-input
            v-model="findForm.verificationCode"
            prefix-icon="iconfont icon-huoquyanzhengma"
            placeholder="验证码"
            @change="verificationCode"
          ></el-input>
        </el-form-item>
        <!-- 获取验证码 -->
        <el-form-item class="btns">
          <el-button
            type=""
            @click="getVerificationCode"
            icon="iconfont icon-huoquyanzhengma"
            >获取验证码</el-button
          >
          <el-button
            type=""
            @click="findPassword"
            icon="iconfont icon-zhaohuimima"
            >找回密码</el-button
          >
          <el-button type="" @click="returnLog" icon="iconfont icon-back"
            >返回</el-button
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
      findForm: {
        phone: "",
        verificationCode: "",
      },
      //随机验证码
      randNum: "",
    };
  },
  created() {
    this.randNum = this.getRandom();
    console.log(this.randNum);
    this.keyupSubmit();
  },
  methods: {
    //键盘监视
    keyupSubmit() {
      document.onkeydown = (e) => {
        let _key = window.event.keyCode;
        if (_key === 13) {
          this.findPassword();
        }
      };
    },
    phone: function () {
      if (!/^1[345789]\d{9}$/.test(this.findForm.phone)) {
        this.$message({
          duration: 800,
          message: "请输入正确的手机号！",
          type: "error",
        });
        this.findForm.phone = "";
        return;
      }
    },
    verificationCode: function () {
      if (!/[0-9]{5}/.test(this.findForm.verificationCode)) {
        this.$message({
          duration: 800,
          message: "验证码格式错误！",
          type: "error",
        });
        this.findForm.verificationCode = "";
        return;
      }
    },
    returnLog() {
      this.$router.push({ path: "/login" });
    },
    getVerificationCode() {
      this.$http
        .get("user/findByPhone?" + "phone=" + this.findForm.phone.toString())
        .then((res) => {
          console.log(res);
          if (res.data.date != null) {
            this.$http.get(
              "http://utf8.api.smschinese.cn/?" +
                "Uid=" +
                "FaRng" +
                "&Key=" +
                "d41d8cd98f00b204e980" +
                "&smsMob=" +
                this.findForm.phone +
                "&smsText=您的验证码为:" +
                this.randNum.toString() +
                "打死都不要告诉别人哦!"
            );
            this.$message({
              duration: 2000,
              message: "验证码已发送到您的手机，请查看！",
              type: "success",
            });
          } else {
            this.$message({
              duration: 2000,
              message: "用户不存在!",
              type: "error",
            });
          }
        })
        .catch((err) => {
          this.loading = false;
          this.$message({
            duration: 800,
            message: "查询失败",
            type: "error",
          });
        });
    },
    getRandom() {
      var code = "";
      for (var i = 0; i < 5; i++) {
        var index = Math.floor(Math.random() * 9 + 1);
        console.log(index);
        code += index.toString();
      }
      return code;
    },
    findPassword() {
      if (this.findForm.phone == "") {
        this.$message({
          duration: 800,
          message: "手机号不能为空!",
          type: "error",
        });
      }

      if (this.findForm.verificationCode == this.randNum) {
        this.$http
          .get("user/findByPhone?" + "phone=" + this.findForm.phone.toString())
          .then((res) => {
            this.$router.push({ path: "/login" });
            this.$message({
              duration: 2000,
              message: "您的密码已发送到您的手机，请注意查收!",
              type: "success",
            });
            //发送密码
            this.$http.get(
              "http://utf8.api.smschinese.cn/?" +
                "Uid=" +
                "FaRng" +
                "&Key=" +
                "d41d8cd98f00b204e980" +
                "&smsMob=" +
                this.findForm.phone +
                "&smsText=您的密码为:" +
                res.data.date.password
            );
          })
          .catch((err) => {
            this.loading = false;
            this.$message({
              duration: 800,
              message: "查询失败",
              type: "error",
            });
          });
      }
    },
  },
};
</script>
<style lang="less" scoped>
.find_container {
  background-color: #2b4b6b;
  height: 100%;
}
.find_box {
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
  .find_form {
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
