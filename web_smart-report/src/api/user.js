/*
 * @Author: tianleiyu
 * @Date: 2024-04-22 16:25:23
 * @LastEditTime: 2024-04-23 13:29:18
 * @LastEditors: tianleiyu
 * @Description:
 * @FilePath: /organization1/src/api/user.ts
 * 可以输入预定的版权声明、个性签名、空行等
 */
import request from "@/utils/request";

// export const userLogin = (user: any) => {
//     return request.post('/user/login', user)
// }

// 用户登录
export const login = (data) => {
  return request.post("/user/login", data);
};

// 用户注册
export const reg = (data) => {
  return request.post("/user/userReg", data);
};

// 填写表单信息
export const addFormInfo = (data) => {
  return request.post("/form/addFormInfo", data);
};

// 填写表单信息
export const getUserDatas = () => {
  return request.get("/user/list");
};

// 填写表单信息
export const pageUserDatas = (data) => {
  return request.post("/user/page", data);
};
