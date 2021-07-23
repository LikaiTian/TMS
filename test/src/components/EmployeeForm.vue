<template>
  <el-form
    ref="EmployeeFormRef"
    :model="EmployeeForm"
    label-width="20%"
    class="Form"
    :rules="employeeRules"
  >
    <div class="wrap">
      <h2>员工信息</h2>
    </div>

    <el-form-item label="员工姓名" class="name" prop="name">
      <el-input
        v-model="EmployeeForm.name"
        placeholder="请输入姓名"
        class="nameInput"
      ></el-input>
    </el-form-item>

    <el-form-item label="任职部门" class="department" prop="department">
      <el-select v-model="EmployeeForm.department" placeholder="请选择任职部门">
        <el-option label="项目部" value="项目部"></el-option>
        <el-option label="人事部" value="人事部"></el-option>
        <el-option label="财务部" value="财务部"></el-option>
        <el-option label="技术部" value="技术部"></el-option>
        <el-option label="销售部" value="销售部"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="职位" class="job" prop="job">
      <el-input
        v-model="EmployeeForm.job"
        placeholder="请输入职位"
        class="jobInput"
      ></el-input>
    </el-form-item>

    <el-form-item label="员工电话" class="phone" prop="phone">
      <el-input
        v-model="EmployeeForm.phone"
        placeholder="请输入联系电话"
        class="phoneInput"
        @change="phone"
      ></el-input>
    </el-form-item>

    <el-form-item label="性别" class="sex" prop="sex">
      <el-select
        v-model="EmployeeForm.sex"
        placeholder="性别"
        class="sexSelect"
      >
        <el-option label="男" value="男"></el-option>
        <el-option label="女" value="女"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="出生日期" class="date" prop="birth">
      <el-col :span="11">
        <el-date-picker
          type="date"
          placeholder="出生日期"
          v-model="EmployeeForm.birth"
          style="width: 100%"
          value-format="yyyy-MM-dd"
          format="yyyy 年 MM 月 dd 日"
        ></el-date-picker>
      </el-col>
      <!-- <el-col class="line" :span="2">-</el-col>
      <el-col :span="11">
        <el-time-picker
          placeholder="选择时间"
          v-model="form.date2"
          style="width: 100%"
        ></el-time-picker>
      </el-col> -->
    </el-form-item>

    <el-form-item label="卡号" class="cardId" prop="cardId">
      <el-input
        v-model="EmployeeForm.cardId"
        placeholder="请输入卡号"
        class="cardIdInput"
        @change="cardId"
      ></el-input>
    </el-form-item>

    <el-form-item label="工资" class="salary" prop="salary">
      <el-input
        v-model="EmployeeForm.salary"
        placeholder="请设置该员工工资(单位:元)"
        class="salaryInput"
        @change="salary"
      ></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">立即添加</el-button>
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
      EmployeeForm: {
        name: "",
        department: "",
        job: "",
        phone: "",
        sex: "",
        birth: "",
        cardId: "",
        salary: "",
      },
      id: "",
      company: "",
      Time: "",
      Operation: "",

      //验证规则
      employeeRules: {
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { min: 2, max: 7, message: "长度在2 ~ 7个字符", trihger: "blur" },
        ],
        job: [
          { required: true, message: "请输入职位", trigger: "blur" },
          { min: 1, max: 10, message: "长度在1 ~ 10个字符", trihger: "blur" },
        ],
        sex: [{ required: true, message: "请选择性别", trigger: "change" }],
        birth: [{ required: true, message: "请选择出生日期", trigger: "blur" }],
        department: [
          { required: true, message: "请选择部门", trigger: "change" },
        ],
        phone: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          { min: 11, max: 11, message: "长度为11个数字", trihger: "blur" },
        ],
        cardId: [
          { required: true, message: "请输入银行卡号", trigger: "blur" },
          { min: 19, max: 19, message: "长度为19个数字", trihger: "blur" },
        ],
        salary: [
          { required: true, message: "请输入工资", trigger: "blur" },
          { min: 4, max: 7, message: "公司范围:1千~100万", trihger: "blur" },
        ],
      },
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.company = this.$route.query.company;
    console.log(this.company);
    this.keyupSubmit();
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
      if (!/^1[345789]\d{9}$/.test(this.EmployeeForm.phone)) {
        this.$message({
          duration: 800,
          message: "请输入正确的手机号！",
          type: "error",
        });
        this.EmployeeForm.phone = "";
        return;
      }
    },
    cardId: function () {
      if (!/^[^0]\d{18}/.test(this.EmployeeForm.cardId)) {
        this.$message({
          duration: 800,
          message: "请输入正确的卡号！",
          type: "error",
        });
        this.EmployeeForm.cardId = "";
        return;
      }
    },
    salary: function () {
      var salary1 = /^[^0]\d{3}$/;
      var salary2 = /^[^0]\d{4}$/;
      var salary3 = /^[^0]\d{5}$/;
      var salary4 = /^[^0]\d{6}$/;
      if (
        !salary1.test(this.EmployeeForm.salary) &&
        !salary2.test(this.EmployeeForm.salary) &&
        !salary3.test(this.EmployeeForm.salary) &&
        !salary4.test(this.EmployeeForm.salary)
      ) {
        this.$message({
          duration: 800,
          message: "请输入正确的工资！",
          type: "error",
        });
        this.EmployeeForm.salary = "";
        return;
      }
    },
    onSubmit() {
      //方法一
      // this.$http
      //   .get(
      //     "employ/add?" +
      //       "cardId=" +
      //       this.EmployeeForm.cardId.toString() +
      //       "&name=" +
      //       this.EmployeeForm.name.toString() +
      //       "&sex=" +
      //       this.EmployeeForm.sex.toString() +
      //       "&birth=" +
      //       this.EmployeeForm.birth.toString() +
      //       "&phone=" +
      //       this.EmployeeForm.phone.toString() +
      //       "&company=" +
      //       this.company +
      //       "&department=" +
      //       this.EmployeeForm.department.toString() +
      //       "&job=" +
      //       this.EmployeeForm.job.toString()
      //   )
      //   .then((res) => {
      //     console.log(res);
      //     if (res.data.code == 0) {
      //       this.$message.success("添加成功!");
      //       this.$router.push({
      //         path: "/employee",
      //         query: { company: this.company },
      //       });
      //     } else if (res.data.code == 15) {
      //       this.$message.error("员工已存在!");
      //     } else {
      //       this.$message.error("添加失败!");
      //     }
      //   });

      this.$refs.EmployeeFormRef.validate(async (valid) => {
        //验证失败
        if (!valid) return;

        await this.$http
          .get(
            "employ/add?" +
              "cardId=" +
              this.EmployeeForm.cardId.toString() +
              "&name=" +
              this.EmployeeForm.name.toString() +
              "&sex=" +
              this.EmployeeForm.sex.toString() +
              "&birth=" +
              this.EmployeeForm.birth.toString() +
              "&phone=" +
              this.EmployeeForm.phone.toString() +
              "&company=" +
              this.company +
              "&department=" +
              this.EmployeeForm.department.toString() +
              "&job=" +
              this.EmployeeForm.job.toString() +
              "&salary=" +
              this.EmployeeForm.salary.toString()
          )
          .then((res) => {
            console.log(res);
            if (res.data.code == 0) {
              this.$message({
                duration: 600,
                message: "添加成功！",
                type: "success",
              });

              this.Operation = "添加员工" + this.EmployeeForm.name.toString();
              this.Time = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
              console.log(this.Operation);
              console.log(this.Time);
              this.addOperateInfo();
              console.log(this.id);
              this.navigate();

              // this.$router.push({
              //   path: "/employee",
              //   query: { company: this.company },
              // });
            } else if (res.data.code == 15) {
              this.$message({
                duration: 600,
                message: "员工已存在",
                type: "error",
              });
            } else {
              this.$message({
                duration: 600,
                message: "添加失败",
                type: "error",
              });
            }
          });
      });
    },
    navigate() {
      routers.go(-1);
    },
    //添加操作
    addOperateInfo() {
      this.$http
        .get(
          "tip/add?" +
            "userId=" +
            this.id.toString() +
            "&message=" +
            this.Operation.toString()
        )
        .then((res) => {
          console.log(res);
          this.$message({
            duration: 600,
            message: "添加成功！",
            type: "success",
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
  background-color: rgb(193, 219, 208);
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
.department {
  height: 8%;
}
.job {
  height: 8%;
}
.jobInput {
  width: 30%;
}
.phone {
  height: 8%;
}
.phoneInput {
  width: 60%;
}
.sex {
  height: 8%;
}
.sexSelect {
  width: 15%;
}
.date {
  height: 8%;
}
.cardId {
  height: 8%;
}
.cardIdInput {
  width: 70%;
}
.salary {
  height: 8%;
}
</style>