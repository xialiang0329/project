import axios from 'axios'
import {Loading,Message} from 'element-ui'


export  const http = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 500000 // request timeout
});
export const fileHttp = axios.create({
  baseURL: process.env.FILE_API, // api 的 base_url
  timeout: 500000 // request timeout
});

// 配置head,拦截器等
let loadingInstance = {};
let URL = "";

http.interceptors.request.use(config => {

  let token = JSON.parse(window.localStorage.getItem("token"));
  let username = JSON.parse(window.localStorage.getItem("username"));
  // let mt = JSON.parse(window.localStorage.getItem("mt"))==null ?  "" :JSON.parse(window.localStorage.getItem("mt"));
  // let isAuth2Login = JSON.parse(window.localStorage.getItem("isAuth2Login"));

  if (token != null) {
    config.headers.common['token'] = token['key_Spp_token'];
    config.headers.common['username'] = username['key_Spp_username'];
    // config.headers.common['userid'] = username['key_Spp_userid'];
    // config.headers.common['mt'] = mt['mt'];
    /*config.headers.common['isAuth2Login'] = isAuth2Login['isAuth2Login'];*/
    // config.headers.common['isAuth2Login'] = isAuth2Login;
  }
  URL=config.baseURL;
  loadingInstance = Loading.service({"background": "rgba(0, 0, 0, 0.8)"});
  return config;
}, err => {
 // Message.error({message: '请求超时!'});
  loadingInstance.close();
  return Promise.reject(err);
});


http.interceptors.response.use(data => {
  loadingInstance.close();
  // todo 异常情况后续待处理
  return data;
}, error => {
  loadingInstance.close();
  return Promise.reject(error);
});

http.interceptors.response.use(
  response => response,
  error => {
    if (error.response.status == 504 || error.response.status == 404) {
      Message.error({message: '服务器被吃了⊙﹏⊙∥'});
    } else if (error.response.status == 403) {
      Message.error({message: '权限不足,请联系管理员!'});
    } else if (error.response.status == 401) {
      Message.error({message: error.response.data.msg});
      window.location.href ="login";
    }
    return Promise.reject(error);
  }
);


export default http;
