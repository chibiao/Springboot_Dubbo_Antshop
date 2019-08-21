package com.itlike.mapper;

import com.itlike.pojo.QueryVo;
import com.itlike.pojo.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll(QueryVo vo);

    int updateByPrimaryKey(User record);

    void updateUserState(long id);

    User getUserByUsername(String username);
}