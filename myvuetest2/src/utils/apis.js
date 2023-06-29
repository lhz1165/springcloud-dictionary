import axios from "axios";
import {Message} from "element-ui";
import router from "@/router";

axios.interceptors.request.use(config => {
        // console.log("config is url"+config.url)
        if (window.localStorage.getItem('token')) {
            //在请求头加入token，名字要和后端接收请求头的token名字一样
            config.headers.token=window.localStorage.getItem('token');
        }
        return config;
    },
    error => {
        return Promise.reject(error);
});

// axios.interceptors.response.use(success=>{
//         if (success.status!=null && success.status === 200) {
//             if (success.data.code === "401" ) {
//                 Message.error({message:"请登录"});
//                 router.replace("/login")
//                 return
//             }
//             if (success.data.code === "500" ) {
//                 Message.error({message:success.data.message});
//                 return
//             }
//
//             if (success.data.message){
//                 Message.success({message:success.data.message});
//             }
//         }
// },error => {
//     if (error.response.code === "500" || error.response.code === "404") {
//         Message.error({message:"服务器走丢了"});
//     }else {
//         if (error.response.data.message){
//             Message.error({message:error.response.data.message});
//         }else {
//             Message.error({message:"未知错误"});
//         }
//     }
// }
// )

axios.defaults.baseURL = 'http://localhost:12010'
export const postRequest=(url,params)=>{
    return axios({
        method:'post',
        url:url,
        data:params
    })
}
export const getRequest=(url,params)=>{
    return axios({
        method:"get",
        url:url,
        params:params
    })
}