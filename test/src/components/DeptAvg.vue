<template>
  <div class="dd">
    <div id="main"></div>
  </div>
</template>
<script>
var da;
export default {
  data() {
    return {
      company: "",
    };
  },
  created() {
    this.company = this.$route.query.company;
  },
  mounted() {
    this.drawChart("main");
  },
  methods: {
    drawChart(id) {
      //基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById(id));
      // var da;
      this.$http
        .get("employ/companySalary?" + "company=" + this.company.toString())
        .then((res) => {
          da = res.data.date.salary;

          // 绘制图表
          myChart.setOption({
            title: {
              text: "各部门平均薪资",
            },
            tooltip: {},
            xAxis: {
              data: ["项目部", "人事部", "财务部", "技术部", "销售部"],
            },
            yAxis: {},
            series: [
              {
                name: "薪资",
                type: "bar",
                data: da,
                //
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
                        "rgb(50,100,100)",
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
};
</script>

<style>
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
  margin-left: 30%;
  background-color: rgb(193, 219, 208);
}
</style>
