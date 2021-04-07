import { http } from "../utils/request";


export  function queryMenuList (){
  return http({
    method: 'get',
    url: '/menu/pc/queryMenuList',
  });
}

export  function addMenu (data){
  return http({
    method: 'post',
    url: '/menu/addMenu',
    data:data
  });
}
