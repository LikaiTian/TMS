<template>
  <div>
    <span class="top" style="line-height: 100px">部门建设</span>
    <span class="text">
      <div id="main" style="width: 600px; height: 400px"></div>
    </span>
  </div>
</template>
<script>
import echarts from "echarts";

export default {
  name: "",
  data() {
    return {
      charts: "",
      /*	opinion: ["1", "3", "3", "4", "5"],*/
      opinionData: ["30", "21", "16", "41", "19","27", "35"],
    };
  },
  methods: {
    drawLine(id) {
      this.charts = echarts.init(document.getElementById(id));
      this.charts.setOption({
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["近七日解决问题数"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [
            "第一周",
            "第二周",
            "第三周",
            "第四周",
            "第五周",
            "第六周",
            "第七周",
          ],
        },
        yAxis: {
          type: "value",
        },

        series: [
          {
            name: "近七日解决问题数",
            type: "line",
            stack: "总量",
            data: this.opinionData,
          },
        ],
      });
    },
  },
  //调用
  mounted() {
    this.$nextTick(function () {
      this.drawLine("main");
    });
  },
};
</script>
<style lang="less" scoped>
.top {
  display: block;
  position: absolute;
  font-size: 30px;
  top: 10%;
  width: 80%;
  right: 0;
  height: 15%;
  text-align: center;
  background-color: rgb(193, 219, 208);
}
.text {
  position: absolute;
  top: 25%;
  right: 0;
  width: 80%;
  height: 75%;
  background-color: rgb(193, 219, 208);
}
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
</style>