package com.itlike.service;


import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.User;

public interface IUserService {
    PageListRes userList(QueryVo vo);

    //Long getCount() ;

    AjaxRes addUser(User user) ;

    AjaxRes updateUser(User user) ;

    AjaxRes updateUserState(long id) ;

    User getUserByUsername(String username) ;

    void updateUState(String username) ;

    String addUserToRedis(User user);

    User getUserByUserToken(String userToken);

    Boolean deleteUserByUserToken(String userToken);
}
