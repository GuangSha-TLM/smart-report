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

// 创建表单
export const formCreate = (data) => {
  return request.post("/form/add", data);
};

// 创建表单
export const formByPageLike = (data) => {
  return request.post("/form/formByPageLike", data);
};

// 创建表单
export const formDelete = (id) => {
  return request.get(`/form/formDelete/${id}`);
};

// 创建表单
export const formUpdate = (data) => {
  return request.post('/form/formUpdate',data);
};
