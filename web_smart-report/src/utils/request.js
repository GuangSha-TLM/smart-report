/*
 * @Author: tianleiyu 
 * @Date: 2024-04-22 16:21:40
 * @LastEditTime: 2024-04-23 14:27:29
 * @LastEditors: tianleiyu
 * @Description: 
 * @FilePath: /organization1/src/utils/request.ts
 * 可以输入预定的版权声明、个性签名、空行等
 */
import axios from 'axios'
//引入token
// import { GET_TOKEN } from "@/utils/cookie";
//处理 类型“AxiosResponse<any, any>”上不存在属性 的问题
// declare module "axios" {
//     interface AxiosResponse<T = any> {
//         code: string
//         data: T
//         message: string
//         // 这里追加你的参数
//     }
//     export function create(config?: AxiosRequestConfig): AxiosInstance;
// }

const request = axios.create({
    baseURL: '/api',
    timeout: 2000

})

request.interceptors.request.use(
    (config) => {
        //获取token
        // const userToken = GET_TOKEN()
        // if (userToken) {
        //     //设置请求头token，传递给后端。
        //     config.headers.Authorization = userToken
        // }
        return config

    }
)

request.interceptors.response.use(
    (response) => {
        console.log(response);

        if (response.status === 200) {
            return response.data
        } else {

            return Promise.reject(response.data)
        }
    },
    (error) => {
        return Promise.reject(error)
    }
)

export default request;