<template>
  <div class="adatar">
    <img :src="adatar" alt="" />
    <input
      type="file"
      name=""
      accept="image/gif,image/jpeg,image/jpg,image/png"
      @change="fileChange"
    />
    <button @click="submit" :disabled="btnBoolean == btnStatus ? true : false">
      提交
    </button>
  </div>
</template>
<script>
import routers from "../router";
export default {
  name: "adatar",
  data() {
    return {
      btnBoolean: Boolean,
      btnStatus: -1,
      adatar: "",
      OriginAdater: "",
      id: "",
      file: "",
      company: "",
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.adatar = this.$route.query.adatar;
    this.company = this.$route.query.company;
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
    },
    submit() {
      if (this.adatar == this.OriginAdater) {
           this.$message({
            duration: 600,
            message: "更换失败",
            type: "error",
          });
        return;
      }
      var data = new FormData();
      data.append("id", this.id);
      data.append("file", this.file);
      this.$http.post("user/getImg", data).then((res) => {
        this.adatar = res.data.date;
        console.log("portrait" + this.adatar);
      });
      //延时函数
      setTimeout(() => {
        //返回
        this.$router.push("/home");
      }, 2000);
    },
  },
};
</script>
<style lang="less">
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