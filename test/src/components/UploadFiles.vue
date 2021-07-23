<template>
  <div class="adatar">
    <img :src="require('../image/file.png')" alt="" />
    <input
      type="file"
      name=""
      accept="aplication/zip,application/msword"
      @change="fileChange"
    />
    <span class="file">{{ fileName }}</span>
    <button @click="submit">提交文件</button>
  </div>
</template>
<script>
import routers from "../router";
export default {
  name: "adatar",
  data() {
    return {
      //员工信息
      company: "",
      dept: "",
      job: "",
      name: "",
      birth: "",
      cardId: "",
      sex: "",
      phone: "",
      salary: "",
      evaluate1: "",
      evaluate2: "",
      //文件
      adatar: "",
      OriginAdater: "",
      file: "",
      fileName: "",
    };
  },
  created() {
    //员工信息
    this.company = this.$route.query.company;
    this.dept = this.$route.query.dept;
    this.job = this.$route.query.job;
    this.name = this.$route.query.name;
    this.birth = this.$route.query.birth;
    this.cardId = this.$route.query.cardId;
    this.sex = this.$route.query.sex;
    this.phone = this.$route.query.phone;
    this.salary = this.$route.query.salary;
    this.evaluate1 = this.$route.query.evaluate1;
    this.evaluate2 = this.$route.query.evaluate2;
    //文件
    this.adatar = this.$route.query.adatar;
    this.OriginAdater = this.adatar;
    // this.$http
    //   .get("user/findAddress?" + "id=" + this.id)
    //   .then((res) => {
    //     if (res.data.date == null) return;
    //     //this.adatar = require(res.data.date);
    //   })
    //   .catch((err) => {});
  },
  methods: {
    navigate() {
      routers.go(-1);
    },
    //头像选择
    fileChange(e) {
      var that = this;
      var file = e.target.files[0];
      var reader = new FileReader();
      reader.onload = function (e) {
        that.adatar = e.target.result;
      };
      reader.readAsDataURL(file);
      this.file = file;
      console.log(this.file);
      this.fileName = this.file.name;
    },
    submit() {
      if (this.adatar == this.OriginAdater) {
        this.$message({
          duration: 600,
          message: "上传文件为空",
          type: "error",
        });
        return;
      }
      console.log("portrait" + this.file);
      var data = new FormData();
      data.append("file", this.file);

      this.$http.post("comment/upload", data).then((res) => {
        console.log(res.data.date);
        this.$router.push({
          path: "/employeeDetails",
          query: {
            filePath: res.data.date,
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
            uploadflag: -1,
          },
        });
        this.$message({
          duration: 600,
          message: "上传附件成功！",
          type: "success",
        });
      });
    },
  },
};
</script>
<style lang="less">
.file {
  display: block;
  position: absolute;
  left: 50%;
  top: 110%;
  width: 500px;
  text-align: center;
  transform: translate(-50%, -50%);
}
.adatar {
  display: block;
  position: absolute;
  left: 50%;
  top: 35%;
  width: 168px;
  height: 168px;
  img {
    object-fit: cover;
    object-position: center;
    width: 100%;
    height: 100%;
    border-radius: 50%;
  }
  input {
    display: block;
    position: absolute;
    top: 0;
    right: 0;
    width: 100%;
    height: 100%;
    border-radius: 50%;
    outline: none;
    opacity: 0;
    cursor: pointer;
    &:focus {
      box-shadow: none;
    }
  }
  button {
    display: block;
    position: absolute;
    top: 120%;
    left: 10%;
    width: 80%;
    height: 30%;
    background-color: #2969b3;
  }
}
</style>