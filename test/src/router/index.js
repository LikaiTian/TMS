//进度条  这两句必须放在vue实例的上面
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Register from '../components/Register.vue'
//公司简介
import Company from '../components/Company.vue'
//员工总览
import Employee from '../components/Employee.vue'
//员工详细信息
import EmployeeDetails from '../components/EmployeeDetails'
//部门建设
import Department from '../components/Department.vue'
//大数据
import DataSet from '../components/DataSet.vue'

//添加员工表单
import EmployeeForm from '../components/EmployeeForm.vue'
//消息中心
import OperateInfo from '../components/OperateInfo.vue'
//最炫酷网页
import Ruifeng from '../components/Ruifeng.vue'

import Light from '../components/Light.vue'
//找回密码
import FindPassword from '../components/FindPassword.vue'
//头像
import Portrait from '../components/Portrait.vue'
//修改员工信息
import EmployeeModify from '../components/EmployeeModify.vue'
//上传文件
import UploadFiles from '../components/UploadFiles.vue'
//个人信息
import MyInfo from '../components/MyInfo.vue'
//部门人数
import DeptNum from '../components/DeptNum.vue'
//部门平均薪资
import DeptAvg from '../components/DeptAvg.vue'
//薪资分布
import SalaryIndex from '../components/SalaryIndex.vue'

Vue.use(Router)


const routerPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error => error)
}

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/锐锋',
    },
    {
      path: '/锐锋',
      name: "锐锋",
      component: Ruifeng,
      children: [
        {
          path: '/light',
          name: 'light',
          component: Light,
        },
      ]
    },
    {
      path: '/login',
      component: Login,
    }, {
      path: '/register',
      component: Register,
    }, {
      path: '/findpassword',
      component: FindPassword,
    }, {
      //主页面
      path: '/home',
      name: 'home',
      component: Home,
      redirect: '/employee',
      meta: {
        requireAuth: true
      },
      children: [
        {
          //公司简介
          path: '/company',
          name: 'company',
          component: Company,
          meta: {
            requireAuth: true
          },
        },
        {
          //员工总览
          path: '/employee',
          name: 'employee',
          component: Employee,
          meta: {
            requireAuth: true
          },
        },
        {
          //人员分布
          path: '/deptNum',
          name: 'deptNum',
          component: DeptNum,
          meta: {
            requireAuth: true
          },
        },
        {
          //薪资水平
          path: '/deptAvg',
          name: 'deptAvg',
          component: DeptAvg,
          meta: {
            requireAuth: true
          },
        },
        {
          //薪资分布
          path: '/salaryIndex',
          name: 'salaryIndex',
          component: SalaryIndex,
          meta: {
            requireAuth: true
          },
        }, {
          //大数据
          path: '/dataSet',
          name: 'dataSet',
          component: DataSet,
          meta: {
            requireAuth: true
          },
        },{
          path: '/employeeDetails',
          name: 'employeeDetails',
          component: EmployeeDetails,
          meta: {
            requireAuth: true
          },
        }, {
          //修改员工信息
          path: '/employeeModify',
          name: 'employeeModify',
          component: EmployeeModify,
          meta: {
            requireAuth: true
          },
        }, {
          //添加员工
          path: '/employeeForm',
          name: 'employeeForm',
          component: EmployeeForm,
          meta: {
            requireAuth: true
          },

        }, {
          path: '/operateInfo',
          name: 'operateInfo',
          component: OperateInfo,
          meta: {
            requireAuth: true
          },
        }, {
          //修改头像
          path: '/portrait',
          name: 'portrait',
          component: Portrait,
          meta: {
            requireAuth: true
          },
        }, {
          //个人信息
          path: '/myInfo',
          name: 'myInfo',
          component: MyInfo,
          meta: {
            requireAuth: true
          },
        }, {
          //上传附件
          path: '/uploadFiles',
          name: 'uploadFiles',
          component: UploadFiles,
          meta: {
            requireAuth: true
          },
        },
      ]
    },
    {
      path: '*',
      component: Error,
      name: 'error'
    },
  ]
});
router.beforeEach((to, from, next) => {

  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (sessionStorage.getItem("token") == 'true') { // 判断本地是否存在token
      NProgress.start() // 显示进度条
      next()
    } else {
      // 未登录,跳转到登陆页面
      next({
        path: '/锐锋'
      })
    }
  } else {
    if (sessionStorage.getItem("token") == 'true') {
      NProgress.start() // 显示进度条
      next('/home');
    } else {
      NProgress.start() // 显示进度条
      next();
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 完成进度条
})

export default router