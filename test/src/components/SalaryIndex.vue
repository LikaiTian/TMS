<template>
  <div class="dd">
    <span class="choose">
      <el-select
        class="department"
        style="top=10px;"
        v-model="value"
        @change="selectDept"
        placeholder="请选择"
      >
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </span>
    <div id="main"></div>
  </div>
</template>
<script>
var da;
export default {
  data() {
    return {
      options: [
        {
          value: "项目部",
          label: "项目部",
        },
        {
          value: "人事部",
          label: "人事部",
        },
        {
          value: "财务部",
          label: "财务部",
        },
        {
          value: "技术部",
          label: "技术部",
        },
        {
          value: "销售部",
          label: "销售部",
        },
      ],
      value: "",
      company: "",
    };
  },
  created() {
    this.value = "项目部";
    this.company = this.$route.query.company;
  },
  methods: {
    selectDept() {
      this.drawChart();
    },
    drawChart(employees) {
      //基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("main"));
      // var da;
      console.log(this.value);
      this.$http
        .get(
          "employ/salaryStatus?" +
            "company=" +
            this.company.toString() +
            "&department=" +
            this.value.toString()
        )
        .then((res) => {
          da = res.data.date.salary;

          // 绘制图表
          myChart.setOption({
            title: {
              text: "部门薪资分布",
            },
            tooltip: {},
            xAxis: {
              data: ["<5k", "5k~10k", "10k~15k", "15k~20k"],
            },
            yAxis: {},
            series: [
              {
                name: "人数",
                type: "bar",
                data: da,
                //
                showBackground: true,
                barWidth: 50,
                itemStyle: {
                  //通常情况下：
                  normal: {
                    //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                    color: function (params) {
                      var colorList = [
                        "rgb(164,205,238)",
                        "rgb(42,170,227)",
                        "rgb(25,46,94)",
                        "rgb(195,229,235)",
                      ];
                      return colorList[params.dataIndex];
                    },
                  },
                  //鼠标悬停时：
                  emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: "rgba(0, 0, 0, 0.5,0.5)",
                  },
                },
              },
            ],
          });
        });
    },
  },
  mounted() {
    this.drawChart();
  },
};
</script>

<style>
.dd {
  top: 0;
  height: 100%;
  background-color: rgb(193, 219, 208);
}

.choose {
  display: block;
  position: absolute;
  font-size: 30px;
  top: 10%;
  width: 80%;
  right: 0;
  height: 15%;
}
#main {
  width: 600px;
  height: 565px;
  left: 20%;
  margin-top: 10%;
  margin-left: 20%;
  background-color: rgb(193, 219, 208);
}
</style>
