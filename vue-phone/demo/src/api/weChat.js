import { http } from "../utils/request";

//获取config
export  function wxJSSDKConifg (){
  return http({
    method: 'get',
    url: '/officialAccount/config?url='+encodeURIComponent(window.location.href),
  });
}



