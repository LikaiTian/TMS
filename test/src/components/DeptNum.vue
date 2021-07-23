<template>
  <div class="dd">
    <div id="main"></div>
  </div>
</template>
<script>
var a, b, c, d, e;
export default {
  data() {
    return {
      company: "",
    };
  },
  created() {
    this.company = this.$route.query.company;
  },
  methods: {
    drawChart() {
      //基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("main"));
      window.addEventListener("resize", function () {
        myChart.resize();
      });
      //环形图获取数据
      this.$http
        .get("employ/companySalary?" + "company=" + this.company.toString())
        .then((res) => {
          var dt = res.data.date.employees;
          console.log(dt[0]);
          a = dt[0];
          b = dt[1];
          c = dt[2];
          d = dt[3];
          e = dt[4];

          // 绘制图表
          myChart.setOption({
            tooltip: {
              trigger: "item",
            },
            legend: {
              top: "5%",
              left: "center",
            },
            series: [
              {
                name: "各部门人数分布",
                type: "pie",
                radius: ["40%", "70%"],
                avoidLabelOverlap: false,
                label: {
                  show: false,
                  position: "center",
                },
                emphasis: {
                  label: {
                    show: true,
                    fontSize: "40",
                    fontWeight: "bold",
                  },
                },
                //
                itemStyle: {
                  normal: {
                    color: function (colors) {
                      var colorList = [
                        "#45C2E0",
                        "#C1EBDD",
                        " #FFC851",
                        "#5A5476",
                        "#1869A0",
                        "#FF9393",
                      ];
                      return colorList[colors.dataIndex];
                    },
                  },
                },

                //
                labelLine: {
                  show: false,
                },
                data: [
                  { value: a, name: "项目部" },
                  { value: b, name: "人事部" },
                  { value: c, name: "财务部" },
                  { value: d, name: "技术部" },
                  { value: e, name: "销售部" },
                ],
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

<style lang="less" scoped>
.dd {
  top: 0;
  height: 100%;
  background-color: rgb(193, 219, 208);
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

