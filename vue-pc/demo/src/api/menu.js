import { http } from "../utils/request";


export  function queryMenuList (){
  return http({
    method: 'get',
    url: '/menu/pc/queryMenuList',
  });
}
