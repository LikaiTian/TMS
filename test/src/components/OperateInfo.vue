<template>
  <div class="block">
    <el-radio-group v-model="reverse" class="rank">
      <el-radio :label="true">倒序</el-radio>
      <el-radio :label="false">正序</el-radio>
    </el-radio-group>

    <el-timeline :reverse="reverse" class="line1">
      <el-timeline-item
        v-for="(activity, index) in activities1"
        :key="index"
        :timestamp="activity.timestamp"
      >
        {{ activity.content }}
      </el-timeline-item>
    </el-timeline>

    <el-timeline :reverse="reverse" class="line2">
      <el-timeline-item
        v-for="(activity, index) in activities2"
        :key="index"
        :timestamp="activity.timestamp"
      >
        {{ activity.content }}
      </el-timeline-item>
    </el-timeline>

    <el-timeline :reverse="reverse" class="line3">
      <el-timeline-item
        v-for="(activity, index) in activities3"
        :key="index"
        :timestamp="activity.timestamp"
      >
        {{ activity.content }}
      </el-timeline-item>
    </el-timeline>

    <el-timeline :reverse="reverse" class="line4">
      <el-timeline-item
        v-for="(activity, index) in activities4"
        :key="index"
        :timestamp="activity.timestamp"
      >
        {{ activity.content }}
      </el-timeline-item>
    </el-timeline>
  </div>
</template>
<script>
import routers from "../router";

export default {
  data() {
    return {
      reverse: true,

      activities1: [
        {
          content: "",
          timestamp: "",
        },
      ],
      activities2: [
        {
          content: "",
          timestamp: "",
        },
      ],
      activities3: [
        {
          content: "",
          timestamp: "",
        },
      ],
      activities4: [
        {
          content: "",
          timestamp: "",
        },
      ],
      id: "",
    };
  },
  created() {
    this.id = this.$route.query.id;
    console.log(this.id);
    this.queryOperateInfo();
  },
  methods: {
    queryOperateInfo() {
      this.$http.get("tip/query?" + "userId=" + this.id).then((res) => {
        console.log(res.data.date);
        if (res.data.date.length > 0) {
          for (let index = 0; index < 10; index++) {
            var singleInfo = {
              content: res.data.date[index].time,
              timestamp: res.data.date[index].message,
            };
            this.activities1.push(singleInfo);
          }
        }
        if (res.data.date.length > 10) {
          for (let index = 10; index < 20; index++) {
            var singleInfo = {
              content: res.data.date[index].time,
              timestamp: res.data.date[index].message,
            };
            this.activities2.push(singleInfo);
          }
        }
        if (res.data.date.length > 20) {
          for (let index = 20; index < 30; index++) {
            var singleInfo = {
              content: res.data.date[index].time,
              timestamp: res.data.date[index].message,
            };
            this.activities3.push(singleInfo);
          }
        }
        if (res.data.date.length > 30) {
          for (let index = 30; index < 40; index++) {
            var singleInfo = {
              content: res.data.date[index].time,
              timestamp: res.data.date[index].message,
            };
            this.activities4.push(singleInfo);
          }
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.block {
  display: block;
  position: absolute;
  top: 10%;
  left: 20%;
  width: 80%;
  height: 90%;
}
.rank {
  display: block;
  position: absolute;
  left: 2%;
  top: 2%;
  height: 30px;
  width: 10px;
}

.line1 {
  display: block;
  position: absolute;
  width: 20%;
  height: 10%;
  top: 2%;
  left: 5%;
}
.line2 {
  display: block;
  position: absolute;
  width: 20%;
  height: 10%;
  top: 2%;
  left: 28%;
}
.line3 {
  display: block;
  position: absolute;
  width: 20%;
  height: 10%;
  top: 2%;
  left: 51%;
}
.line4 {
  display: block;
  position: absolute;
  width: 20%;
  height: 10%;
  top: 2%;
  left: 74%;
}
</style>
