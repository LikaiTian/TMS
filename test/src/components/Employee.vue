 <template>
  <div>
    <!-- 表格 -->
    <el-table
      class="table"
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
      <el-table-column prop="sex" label="性别" width="200"></el-table-column>
      <el-table-column prop="phone" label="电话" width="300"></el-table-column>
      <el-table-column prop="department" label="部门" width="200">
      </el-table-column>
      <el-table-column prop="job" label="工作" width="200"> </el-table-column>

      <el-table-column fixed="right" label="查看" width="100">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="
              showDetails(scope.$index, tableData, scope.row)
            "
            type="text"
            size="small"
          >
            查看
          </el-button>
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="修改" width="100">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="
              modifyDetails(scope.$index, tableData, scope.row)
            "
            type="text"
            size="small"
          >
            修改
          </el-button>
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="辞退" width="100">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="
              deleteRow(scope.$index, tableData, scope.row)
            "
            type="text"
            size="small"
          >
            辞退
          </el-button>
        </template>
      </el-table-column>
    </el-table>

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
      class="page"
    >
    </el-pagination>

    <!-- 导出Excel -->
    <el-button type="" @click="update" icon="el-icon-s-promotion" class="upload"
      >导出Excel</el-button
    >
    <!-- 查询条件 -->

    <el-form ref="QueryFormRef" :model="QueryForm" :rules="queryRules">
      <!-- 选择查询方式 -->
      <el-form-item class="selectQuery" prop="query">
        <el-select
          v-model="QueryForm.query"
          placeholder="选择查询方式"
          class="querySelect"
          @change="queryCondition"
        >
          <el-option label="按姓名查询" value="按姓名查询"></el-option>
          <el-option label="按部门查询" value="按部门查询"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item class="queryName">
        <el-input
          v-model="QueryForm.queryName"
          placeholder="请输入姓名"
          class="inputName"
          :disabled="inputBoolean == inputStatus ? true : false"
          v-if="show1 == true"
        ></el-input>
      </el-form-item>

      <el-form-item class="queryDept">
        <el-select
          v-model="QueryForm.queryDept"
          placeholder="任职部门"
          :disabled="selectBoolean == selectStatus ? true : false"
          v-if="show2 == true"
        >
          <el-option label="项目部" value="项目部"></el-option>
          <el-option label="人事部" value="人事部"></el-option>
          <el-option label="财务部" value="财务部"></el-option>
          <el-option label="技术部" value="技术部"></el-option>
          <el-option label="销售部" value="销售部"></el-option>
        </el-select>
      </el-form-item>

      <!-- 点击查询 -->
      <el-button type="" @click="query" icon="el-icon-s-promotion" class="query"
        >查询</el-button
      >
    </el-form>
  </div>
</template>
<script>
import routers from "../router";
import moment from "moment";
export default {
  data() {
    return {
      inputBoolean: Boolean,
      inputStatus: -1,
      selectBoolean: Boolean,
      selectStatus: -1,
      show1: true,
      show2: true,
      QueryForm: {
        query: "",
      },
      queryRules: {
        query: [
          { required: true, message: "请选择查询方式", trigger: "change" },
        ],
      },
      tableData: [
        {
          id: "",
          name: "",
          department: "",
          job: "",
          sex: "",
          phone: "",
        },
      ],
      company: "",
      total: 0,
      currentPage: 1,
      pageSize: 9,
      loading: false,
      name: "",
      dept: "",
      index: "",
      // 查询
      QueryForm: {
        queryName: "",
        queryDept: "",
      },
    };
  },
  created() {
    this.company = this.$route.query.company;
    this.index = this.$route.query.index;
    //console.log(this.index);
    //更新
    if (this.index == 1) {
      this.select_user();
      this.$message.success("刷新成功!");
    }
    this.select_user();
    this.keyupSubmit();
  },
  methods: {
    //键盘监视
    keyupSubmit() {
      document.onkeydown = (e) => {
        let _key = window.event.keyCode;
        if (_key === 13) {
          this.query();
        }
      };
    },
    navigate() {
      routers.go(-1);
    },
    //员工总览
    totalData() {
      this.$http
        .get("employ/findAll?" + "company=" + this.company)
        .then((res) => {
          //console.log("employee" + res);
          this.total = res.data.date.length;
        });
    },
    select_user() {
      this.totalData();
      this.loading = true;
      this.$http
        .get(
          "employ/pageQuery?" +
            "company=" +
            this.company +
            "&pageSize=" +
            this.pageSize +
            "&page=" +
            this.currentPage
        )
        .then((res) => {
          this.loading = false;
          //console.log("employee" + res);
          this.tableData = res.data.date;
          //删除空白行
          //this.tableData.splice(0, 1);
          //遍历后端data
          // if (this.tableData.length == res.data.date.length) return;
          // res.data.date.forEach((element) => {
          //   var employee = {
          //     id: element.id,
          //     cardId: element.cardId,
          //     username: element.name,
          //     sex: element.sex,
          //     phone: element.phone,
          //     department: element.department,
          //     job: element.job,
          //   };
          //   this.tableData.push(employee);
          // });
        })
        .catch((err) => {
          this.loading = false;
          this.$message.error(err);
          //console.log("Employee: " + this.tableData.length);
          //console.log("Employee: " + res.data.date.length);
          //console.log("Employee: " + res);
        });
    },
    handleCurrentChange(val) {
      this.currentPage = val;

      this.select_user();
    },

    handleSizeChange(val) {
      this.pageSize = val;
    },
    deleteRow(index, rows, row) {
      var mymessage = confirm("你真的要辞退" + row.name + "吗?");
      if (mymessage == true) {
        rows.splice(index, 1);
        //删除员工
        this.$http.get("employ/delete?" + "id=" + row.id).then((res) => {
          console.log(res);
        });
        this.select_user();
        this.$message({
          duration: 800,
          message: "辞退成功",
          type: "success",
        });
        //消息中心
        this.Operation = "辞退员工" + row.name.toString();
        this.Time = moment(new Date()).format("YYYY-MM-DD hh:mm:ss");
        console.log(this.Operation);
        console.log(this.Time);
        this.addOperateInfo();

        this.$router.push({
          path: "/operateInfo",
          query: { id: this.id },
        });
      } else {
        this.$message({
          duration: 800,
          message: "辞退失败",
          type: "error",
        });
      }
    },
    //添加操作
    addOperateInfo() {
      this.$http
        .get(
          "tip/add?" +
            "userId=" +
            this.id.toString() +
            "&time=" +
            this.Time.toString() +
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
    showDetails(index, rows, row) {
      //查看详细信息
      //  this.$http.get("employ/?" + "id=" + row.id).then((res) => {
      //   console.log(res);
      // });

      //到home下的EmployeeDetails
      this.$router.push({
        path: "/EmployeeDetails",
        query: {
          company: this.company,
          dept: row.department,
          job: row.job,
          name: row.name,
          birth: row.birth,
          cardId: row.cardId,
          sex: row.sex,
          phone: row.phone,
          salary: row.salary,
        },
      });
    },
    modifyDetails(index, rows, row) {
      //修改信息
      this.$router.push({
        path: "/EmployeeModify",
        query: {
          id: row.id,
          company: this.company,
          dept: row.department,
          job: row.job,
          name: row.name,
          birth: row.birth,
          cardId: row.cardId,
          sex: row.sex,
          phone: row.phone,
          salary: row.salary,
        },
      });
    },
    // id: "",
    // name: "",
    // department: "",
    // job: "",
    // cardId: "",
    // sex: "",
    // phone: "",
    update() {
      this.export2Excel();
    },
    //导出excel方法
    export2Excel() {
      require.ensure([], () => {
        const { export_json_to_excel } = require("../excel/Export2Excel.js");
        //设置Excel的表格第一行的标题
        const tHeader = [
          "序号",
          "姓名",
          "部门",
          "职位",
          "卡号",
          "性别",
          "电话",
        ];
        const filterVal = [
          "id",
          "name",
          "department",
          "job",
          "cardId",
          "sex",
          "phone",
        ];
        const list = this.tableData; //把data里的tableData存到list
        const data = this.formatJson(filterVal, list);
        export_json_to_excel(tHeader, data, "列表excel");
      });
    },
    queryCondition() {
      if (this.QueryForm.query == "按姓名查询") {
        this.QueryForm.queryDept = "";
        this.inputBoolean = 1;
        this.selectBoolean = -1;
        this.show1 = true;
        this.show2 = false;
      }
      if (this.QueryForm.query == "按部门查询") {
        this.QueryForm.queryName = "";
        this.inputBoolean = -1;
        this.selectBoolean = 1;
        this.show1 = false;
        this.show2 = true;
      }
    },
    query() {
      console.log(this.QueryForm.queryName + this.QueryForm.queryDept);

      this.$refs.QueryFormRef.validate(async (valid) => {
        //验证失败
        if (!valid) return;
        console.log(this.QueryForm.query);

        if (this.QueryForm.queryName == "" && this.QueryForm.queryDept != "") {
          this.loading = true;
          //人数
          this.$http
            .get(
              "employ/departPageQuery?" +
                "company=" +
                this.company +
                "&pageSize=" +
                this.pageSize +
                "&page=" +
                this.currentPage +
                "&department=" +
                this.QueryForm.queryDept
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
          this.QueryForm.queryDept == "" &&
          this.QueryForm.queryName != ""
        ) {
          this.loading = true;
          //人数
          this.$http
            .get(
              "employ/findByName?" +
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
          this.QueryForm.queryDept == ""
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

    formatJson(filterVal, jsonData) {
      return jsonData.map((v) => filterVal.map((j) => v[j]));
    },
  },
};
</script>
<style lang="less" scoped>
.table {
  display: block;
  position: absolute;
  top: 10%;
  right: 0;
  width: 80%;
  height: 90%;
  bottom: 0;
}
.page {
  display: block;
  position: absolute;
  width: 50%;
  height: 5%;
  right: 5%;
  bottom: 2.5%;
  color: aqua;
}
.upload {
  display: block;
  position: absolute;
  text-align: center;
  color: rgb(27, 21, 21);
  width: 125px;
  height: 40px;
  top: 2%;
  right: 200px;
  background-color: rgb(193, 219, 208);
}
.queryDept {
  display: block;
  position: absolute;
  top: 2%;
  right: 530px;
  width: 125px;
  background-color: rgb(51, 138, 179);
}
.queryName {
  display: block;
  position: absolute;
  top: 2%;
  right: 530px;
  width: 125px;
  background-color: rgb(51, 138, 179);
}
.query {
  display: block;
  position: absolute;
  top: 2%;
  right: 370px;
  width: 100px;
  background-color: rgb(51, 138, 179);
}
.selectQuery {
  display: block;
  position: absolute;
  top: 2%;
  right: 700px;
  width: 140px;
  background-color: rgb(51, 138, 179);
}
</style>