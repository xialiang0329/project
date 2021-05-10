import { http } from "../utils/request";

export  function searchContacts (userId){
  return http({
    method: 'get',
    url: '/user/search-contacts?userId='+userId,
  });
}
