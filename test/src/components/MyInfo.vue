<template>
  <el-form
    ref="HrModFormRef"
    :model="Hr"
    label-width="20%"
    class="Form"
    :rules="HrRules"
  >
    <div class="wrap">
      <h2>{{ company }}公司HR基本信息</h2>
    </div>

    <el-form-item label="姓名" class="name" prop="name">
      <el-input
        v-model="Hr.name"
        placeholder="请输入姓名"
        class="nameInput"
      ></el-input>
    </el-form-item>

    <el-form-item label="电话" class="phone" prop="phone">
      <el-input
        v-model="Hr.phone"
        placeholder="请输入联系电话"
        class="phoneInput"
        @change="phone"
      ></el-input>
    </el-form-item>

    <el-form-item label="邮箱" class="email" prop="email">
      <el-input
        v-model="Hr.email"
        placeholder="请输入邮箱"
        class="emailInput"
        @change="email"
      ></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">修改</el-button>
      <el-button @click="exit">取消</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import routers from "../router";
import moment from "moment";

export default {
  data() {
    return {
      Hr: {
        name: "",
        phone: "",
        email: "",
      },
      company: "",
      id: "",
      Time: "",
      Operation: "",

      //验证规则
      HrRules: {
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { min: 2, max: 7, message: "长度在2 ~ 7个字符", trihger: "blur" },
        ],
        phone: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { min: 11, max: 11, message: "长度为11个数字", trihger: "blur" },
        ],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
      },
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.company = this.$route.query.company;
    console.log(this.company);
    this.keyupSubmit();
    //初始化
    this.$http
      .get("user/getMessage?" + "id=" + this.id.toString())
      .then((res) => {
        console.log(res);
        this.Hr.name = res.data.nickname;
        this.Hr.phone = res.data.phone;
        this.Hr.email = res.data.email;
      });
  },
  methods: {
    //键盘监视
    keyupSubmit() {
      document.onkeydown = (e) => {
        let _key = window.event.keyCode;
        if (_key === 13) {
          this.onSubmit();
        }
      };
    },
    phone: function () {
      if (!/^1[345789]\d{9}$/.test(this.Hr.phone)) {
        this.$message({
          duration: 800,
          message: "请输入正确的手机号！",
          type: "error",
        });
        this.Hr.phone = "";
        return;
      }
    },
    email: function () {
      if (
        !/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(
          this.Hr.email
        )
      ) {
        this.$message({
          duration: 800,
          message: "请输入正确的邮箱！",
          type: "error",
        });
        this.Hr.email = "";
        return;
      }
    },
    onSubmit() {
      this.$refs.HrModFormRef.validate(async (valid) => {
        //验证失败
        if (!valid) return;
        await this.$http
          .get(
            "user/modify?" +
              "id=" +
              this.id.toString() +
              "&nickname=" +
              this.Hr.name.toString() +
              "&companyName=" +
              this.company.toString() +
              "&phone=" +
              this.Hr.phone.toString() +
              "&email=" +
              this.Hr.email.toString()
          )
          .then((res) => {
            console.log(res);
            if (res.data.code == 0) {
              this.$message({
                duration: 600,
                message: "修改成功！",
                type: "success",
              });
            } else if (res.data.code == 15) {
              this.$message({
                duration: 600,
                message: "",
                type: "error",
              });
            } else {
              this.$message({
                duration: 600,
                message: "修改失败",
                type: "error",
              });
            }
          });
      });
    },
    exit() {
      this.$router.go(-1);
    },
  },
};
</script>
<style lang= "less" scoped>
.Form {
  display: block;
  position: absolute;
  top: 10%;
  bottom: 0;
  right: 0;
  width: 80%;
  height: 90%;
  background-color: rgb(15, 170, 175);
  overflow-x: scroll;
}
.wrap {
  height: 15%;
  width: 80%;
  display: table;
  text-align: center;
}
.name {
  height: 8%;
}
.nameInput {
  width: 30%;
}
.phone {
  height: 8%;
}
.phoneInput {
  width: 60%;
}
.email {
  height: 8%;
}
.emailInput {
  width: 70%;
}
</style>