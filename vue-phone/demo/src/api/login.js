import { http } from "../utils/request";


export  function getImageCode (){
  return http({
    method: 'get',
    url: '/account/getImageCode',
  });
}
export  function verityImageCode (code){
  return http({
    method: 'get',
    url: '/account/verityImageCode?imageCode='+code,
    type:'json'
  });
}
export  function login (data){
  return http({
    method: 'post',
    url: '/account/login',
    data:data,
  });
}
