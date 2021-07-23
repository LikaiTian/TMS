<template>
  <div>
    <span class="top"
      >{{ company }}公司--{{ dept }}门--{{ job }}：{{ name }}</span
    >
    <el-form label-width="20%" class="Form" id="detailsForm">
      <el-form-item label="公司" class="companyLabel">
        <span class="company">{{ company }}科技股份有限公司</span>
      </el-form-item>
      <el-form-item label="姓名" class="nameLabel">
        <span class="name">{{ name }}</span>
      </el-form-item>
      <el-form-item label="性别" class="sexLabel">
        <span class="sex">{{ sex }}</span>
      </el-form-item>
      <el-form-item label="生日" class="birthLabel">
        <span class="birth">{{ birth }}</span>
      </el-form-item>
      <el-form-item label="卡号" class="cardIdLabel">
        <span class="cardId">{{ cardId }}</span>
      </el-form-item>
      <el-form-item label="电话" class="phoneLabel">
        <span class="phone">{{ phone }}</span>
      </el-form-item>
      <el-form-item label="部门" class="deptLabel">
        <span class="dept">{{ dept }}</span>
      </el-form-item>
      <el-form-item label="职位" class="jobLabel">
        <span class="job">{{ job }}</span>
      </el-form-item>
      <el-form-item label="薪资" class="salaryLabel">
        <span class="salary">{{ salary }}</span>
      </el-form-item>
      <!-- 评价框 -->
      <span class="HR">HR评价:</span>
      <el-input
        type="textarea"
        :rows="8"
        placeholder="请输入内容"
        v-model="evaluate1"
        class="evaluateInput"
        :disabled="inputBoolean1 == inputStatus1 ? true : false"
      >
      </el-input>
      <!-- 个人评价 -->
      <span class="personnal">工作绩效:</span>
      <el-input
        type="textarea"
        :rows="8"
        placeholder="请输入内容"
        v-model="evaluate2"
        class="personnalInput"
        :disabled="inputBoolean2 == inputStatus2 ? true : false"
      >
      </el-input>
      <div class="buttons">
        <el-button type="" @click="evaluate" icon="iconfont icon-pingjia1"
          >评价</el-button
        >
        <el-button type="" @click="save" icon="iconfont icon-baocun"
          >保存</el-button
        >
        <el-button
          type=""
          @click="upload"
          icon="el-icon-s-promotion"
          :disabled="uploadBoolean == uploadStatus ? true : false"
          >{{ uploadInfo }}</el-button
        >
        <el-button type="" @click="back" icon="el-icon-s-promotion"
          >返回</el-button
        >
      </div>
    </el-form>
  </div>
</template>
<script>
import routers from "../router";
export default {
  data() {
    return {
      //可编辑
      inputBoolean1: Boolean,
      inputStatus1: -1,
      inputBoolean2: Boolean,
      inputStatus2: -1,
      uploadBoolean: Boolean,
      uploadStatus: -1,
      //个人信息
      company: "",
      name: "",
      dept: "",
      job: "",
      sex: "",
      cardId: "",
      birth: "",
      phone: "",
      salary: "",
      //评价
      evaluate1: "",
      evaluate2: "",
      //文件的路径
      filePath: "",
      //上传文件
      uploadInfo: "",
      uploadflag: Boolean,
    };
  },
  created() {
    this.uploadflag = 1;
    this.evaluate1 = null;
    this.evaluate2 = null;
    this.filePath = null;
    this.company = this.$route.query.company;
    this.name = this.$route.query.name;
    this.dept = this.$route.query.dept;
    this.job = this.$route.query.job;
    this.birth = this.$route.query.birth;
    this.phone = this.$route.query.phone;
    this.cardId = this.$route.query.cardId;
    this.sex = this.$route.query.sex;
    this.salary = this.$route.query.salary;
    this.evaluate1 = this.$route.query.evaluate1;
    this.evaluate2 = this.$route.query.evaluate2;
    this.uploadflag = this.$route.query.uploadflag;
    console.log(" created" + this.uploadflag);
    this.filePath = this.$route.query.filePath;
    //不可编辑
    this.inputBoolean1 = -1;
    this.inputBoolean2 = -1;
    this.uploadBoolean = 1;
    this.uploadInfo = "上传附件";
    //初始化评价
    this.$http
      .get("comment/findNew?" + "&name=" + this.name.toString())
      .then((res) => {
        console.log(res);
        this.evaluate1 = res.data.date.comment;
        this.evaluate2 = res.data.date.workStatus;
      });

    if (this.uploadflag) {
      this.uploadBoolean = -1;
      this.uploadInfo = "附件已上传";
    }
  },
  methods: {
    navigate() {
      routers.go(-1);
    },
    evaluate() {
      this.inputBoolean1 = 1;
      this.inputBoolean2 = 1;
    },
    save() {
      this.inputBoolean1 = -1;
      this.inputBoolean2 = -1;
      console.log(this.filePath);

      if (this.evaluate1 == null || this.evaluate2 == null) {
        this.$message({
          duration: 800,
          message: "请填写评价",
          type: "error",
        });
      } else if (this.filePath == null) {
        this.$message({
          duration: 800,
          message: "请上传附件",
          type: "error",
        });
      } else {
        this.$http
          .get(
            "comment/add?" +
              "&name=" +
              this.name.toString() +
              "&companyName=" +
              this.company.toString() +
              "&comment=" +
              this.evaluate1.toString() +
              "&workStatus=" +
              this.evaluate1.toString() +
              "&fileAddress=" +
              this.filePath.toString()
          )
          .then((res) => {
            console.log(res);
            this.$message({
              duration: 600,
              message: "评价成功！",
              type: "success",
            });
            this.evaluate1 = null;
            this.evaluate2 = null;
            this.filePath = null;
          });
      }
    },
    upload() {
      this.$router.push({
        path: "/uploadFiles",
        query: {
          company: this.company,
          dept: this.dept,
          job: this.job,
          name: this.name,
          birth: this.birth,
          cardId: this.cardId,
          sex: this.sex,
          phone: this.phone,
          salary: this.salary,
          evaluate1: this.evaluate1,
          evaluate2: this.evaluate2,
        },
      });
    },
    back() {
      this.$router.push("/home");
    },
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
  left: 20%;
  width: 80%;
  right: 0;
  height: 15%;
  background-color: rgb(193, 219, 208);
}
.Form {
  display: block;
  position: absolute;
  top: 19%;
  bottom: 0;
  left: 20%;
  right: 0;
  width: 80%;
  height: 81%;
  background-color: rgb(193, 219, 208);
  overflow-x: scroll;
}
.HR {
  display: block;
  position: absolute;
  left: 50%;
  top: 1%;
  font-size: 30px;
}
.evaluateInput {
  display: block;
  position: absolute;
  left: 50%;
  top: 8%;
  width: 45%;
}
.personnal {
  display: block;
  position: absolute;
  left: 50%;
  top: 41%;
  font-size: 30px;
}
.personnalInput {
  display: block;
  position: absolute;
  left: 50%;
  top: 48%;
  width: 45%;
}
.buttons {
  display: block;
  position: absolute;
  width: 50%;
  left: 50%;
  top: 80%;
}
</style>