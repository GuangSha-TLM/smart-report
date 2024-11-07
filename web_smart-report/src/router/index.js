import Vue from "vue";
import Router from "vue-router";

import Login from "@/view/components/main/Login";
import UserReg from "@/view/components/main/UserReg";
import MyForm from "@/view/components/main/MyForm";
import Homeview from "@/view/Homeview";
import Form from "@/view/components/main/Form";
import Userinfo from "@/view/components/main/Userinfo";
Vue.use(Router);

export default new Router({
  mode: "history",
  base: "/",

  routes: [
    //主页
    {
      path: "/",
      name: "home",
      component: Homeview,
      children: [
        {
          path: "form",
          name: "form",
          component: Form,
        },
        {
          path: "myForm",
          name: "myForm",
          component: MyForm,
        },
        {
          path: "userinfo",
          name: "userinfo",
          component: Userinfo,
        },
      ],
    },
    //用户注册
    {
      path: "/user/reg",
      name: "userReg",
      component: UserReg,
    },
    //用户登入页面
    {
      path: "/user/login",
      name: "name",
      component: Login,
    },
    //主页内容
  ],
});
