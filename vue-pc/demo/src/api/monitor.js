import { http } from "../utils/request";

export  function getAssToken (data){
  return http({
    method: 'post',
    url: 'https://open.ys7.com/api/lapp/token/get?appKey='+data.appKey+"&appSecret="+data.appSecret,
    data:data,
    Host:'open.ys7.com',
    'Content-Type': 'application/x-www-form-urlencoded'
  });

}
export  function getAddress (accessToken){
  return http({
    method: 'post',
    url: 'https://open.ys7.com/api/lapp/v2/live/address/get?accessToken='+accessToken+'&deviceSerial=78957921&channelNo=1',
    Host:'open.ys7.com',
    'Content-Type': 'application/x-www-form-urlencoded'
  });
}
