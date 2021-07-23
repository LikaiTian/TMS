<template>
  <div>
    <span class="top">大数据</span>
    <span class="text"></span>
    <el-table
      class="tableData"
      :data="tableData"
      v-loading="loading"
      element-loading-text="拼命加载中"
      element-loading-spinner="iconfont icon-jiazai"
      element-loading-background="rgba(0, 0, 0, 0.8)"
    >
      <el-table-column
        fixed
        prop="id"
        label="员工编号"
        width="200"
      ></el-table-column>
      <el-table-column prop="name" label="姓名" width="200"> </el-table-column>
      <el-table-column prop="companyName" label="公司" width="200">
      </el-table-column>
      <el-table-column prop="comment" label="评价" width="300">
      </el-table-column>
      <el-table-column prop="workStatus" label="绩效" width="300">
      </el-table-column>

      <el-table-column fixed="right" label="下载评论附件" width="150">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="downLoad(scope.$index, tableData, scope.row)"
            type="text"
            size="small"
          >
            下载评价附件
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :total="total"
      :current-page="currentPage"
      :page-size="pageSize"
      :page-sizes="[9, 18, 27, 36]"
      :pager-count="5"
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
      background
      layout="total, sizes, prev, pager, next"
      class="pageData"
    >
    </el-pagination>

    <el-form ref="QueryFormRef" :model="QueryForm" :rules="queryRules">
      <!-- 选择查询方式 -->
      <el-form-item class="selectQueryData" prop="query">
        <el-select
          v-model="QueryForm.query"
          placeholder="选择查询方式"
          class="queryDataSelect"
          @change="queryDataCondition"
        >
          <el-option label="按姓名查询" value="按姓名查询"></el-option>
          <el-option label="按公司查询" value="按公司查询"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item class="queryDataName">
        <el-input
          v-model="QueryForm.queryName"
          placeholder="请输入姓名"
          class="inputName"
          v-if="show1 == true"
        ></el-input>
      </el-form-item>

      <el-form-item class="queryDataDept">
        <el-select
          v-model="QueryForm.queryCompany"
          placeholder="任职公司"
          v-if="show2 == true"
        >
          <el-option label="本公司" value="本公司"></el-option>
        </el-select>
      </el-form-item>

      <!-- 点击查询 -->
      <el-button
        type=""
        @click="queryData"
        icon="el-icon-s-promotion"
        class="queryData"
        >查询</el-button
      >
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //查询
      show1: true,
      show2: true,
      tableData: [
        {
          id: "",
          name: "",
          companyName: "",
          comment: "",
          workStatus: "",
        },
      ],
      // 查询
      QueryForm: {
        queryName: "",
        queryCompany: "",
      },
      company: "",
      loading: false,
      total: 0,
      currentPage: 1,
      pageSize: 9,

      queryRules: {
        query: [
          { required: true, message: "请选择查询方式", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.company = this.$route.query.company;
    console.log(this.company);
    this.selectData_user();
    this.keyupSubmit();
  },
  methods: {
    keyupSubmit() {
      document.onkeydown = (e) => {
        let _key = window.event.keyCode;
        if (_key === 13) {
          this.queryData();
        }
      };
    },
    //员工总览
    totalData() {
      this.$http.get("comment/num?" + "company=" + this.company).then((res) => {
        console.log(res);
        this.total = res.data.date;
      });
    },
    selectData_user() {
      this.totalData();
      this.loading = true;
      this.$http
        .get(
          "comment/queryCompany?" +
            "company=" +
            this.company +
            "&pageSize=" +
            this.pageSize +
            "&page=" +
            this.currentPage
        )
        .then((res) => {
          console.log(res);
          this.loading = false;
          this.tableData = res.data.date;
        })
        .catch((err) => {
          this.loading = false;
          this.$message.error(err);
        });
    },
    downLoad(index, rows, row) {
      console.log(row.fileAddress);
      window.location.href = row.fileAddress;
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.selectData_user();
    },

    handleSizeChange(val) {
      this.pageSize = val;
    },
    //查询条件
    queryDataCondition() {
      if (this.QueryForm.query == "按姓名查询") {
        this.QueryForm.queryCompany = "";
        this.show1 = true;
        this.show2 = false;
      }
      if (this.QueryForm.query == "按公司查询") {
        this.QueryForm.queryName = "";
        this.show1 = false;
        this.show2 = true;
      }
    },
    //点击查询
    queryData() {
      console.log(this.QueryForm.queryName + this.QueryForm.queryCompany);

      this.$refs.QueryFormRef.validate(async (valid) => {
        //验证失败
        if (!valid) return;
        console.log(this.QueryForm.query);

        if (
          this.QueryForm.queryName == "" &&
          this.QueryForm.queryCompany != ""
        ) {
          this.loading = true;
          //人数  按公司查询
          this.$http
            .get(
              "comment/queryCompany?" +
                "company=" +
                this.company +
                "&pageSize=" +
                this.pageSize +
                "&page=" +
                this.currentPage +
                "&department=" +
                this.QueryForm.queryCompany
            )
            .then((res) => {
              this.total = res.data.date.length;
              console.log(res);
              this.loading = false;
              this.tableData = res.data.date;
            })
            .catch((err) => {
              this.loading = false;
              this.$message({
                duration: 800,
                message: "查询失败",
                type: "error",
              });
            });
        } else if (
          this.QueryForm.queryCompany == "" &&
          this.QueryForm.queryName != ""
        ) {
          this.loading = true;
          //人数  按名字查询
          this.$http
            .get(
              "comment/query?" +
                "company=" +
                this.company +
                "&name=" +
                this.QueryForm.queryName +
                "&pageSize=" +
                this.pageSize +
                "&page=" +
                this.currentPage
            )
            .then((res) => {
              this.total = res.data.date.length;
              console.log(this.total);
              console.log(res);
              this.loading = false;
              this.tableData = res.data.date;
            })
            .catch((err) => {
              this.loading = false;
              this.$message({
                duration: 800,
                message: "查询失败",
                type: "error",
              });
            });
        } else if (
          this.QueryForm.queryName == "" &&
          this.QueryForm.queryCompany == ""
        ) {
          this.loading = false;
          this.$message({
            duration: 800,
            message: "至少有一个查询条件",
            type: "error",
          });
          return;
        } else {
        }
      });
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
  top: 14%;
  width: 80%;
  right: 0;
  height: 15%;
}
.text {
  position: absolute;
  top: 25%;
  right: 0;
  width: 80%;
  height: 75%;
  background-color: rgb(15, 170, 175);
}
.tableData {
  display: block;
  position: absolute;
  top: 10%;
  right: 0;
  width: 80%;
  height: 90%;
  bottom: 0;
}
.pageData {
  display: block;
  position: absolute;
  width: 50%;
  height: 5%;
  right: 5%;
  bottom: 2.5%;
  color: aqua;
}
.selectQueryData {
  display: block;
  position: absolute;
  top: 2%;
  right: 700px;
  width: 140px;
  background-color: rgb(51, 138, 179);
}
.queryDataName {
  display: block;
  position: absolute;
  top: 2%;
  right: 530px;
  width: 125px;
  background-color: rgb(51, 138, 179);
}
.queryDataDept {
  display: block;
  position: absolute;
  top: 2%;
  right: 530px;
  width: 125px;
  background-color: rgb(51, 138, 179);
}
.queryData {
  display: block;
  position: absolute;
  top: 2%;
  right: 370px;
  width: 100px;
  background-color: rgb(51, 138, 179);
}
</style>