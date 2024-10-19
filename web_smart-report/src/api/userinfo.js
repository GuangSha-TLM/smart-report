/*
 * @Author: tianleiyu
 * @Date: 2024-04-22 16:25:23
 * @LastEditTime: 2024-04-23 13:29:18
 * @LastEditors: tianleiyu
 * @Description:
 * @FilePath: /organization1/src/api/user.ts
 * 可以输入预定的版权声明、个性签名、空行等
 */
import request from '@/utils/request'

// export const userLogin = (user: any) => {
//     return request.post('/user/login', user)
// }

export const userRegister = (userRegisterData) => {
    return request.post('/user/userReg', userRegisterData)
}
// //用户数据的获取
// export const userPage = (userPageData: any) => {
//     // console.log(pageData.start, pageData.size)
//     return request.get<any, any>(`/user/queryPageUser/${userPageData.start}/${userPageData.size}`)
// }
// //用户信息填写的接口
// export const userInformation = (userInfoData: any) => {
//     return request.post<any, any>('/user/userInfo', userInfoData)
// }
// //管理用户的分配接口
// export const allocationPage = (allocation: any) => {
//     return request.post<any, any>('/user/addUserRolePermission', allocation)
// }


