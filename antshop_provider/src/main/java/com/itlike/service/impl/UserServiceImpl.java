package com.itlike.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.mapper.UserMapper;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.User;
import com.itlike.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author : 迟彪
 * @date : 2019/8/5
 */
@Service
@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate jsonRedisTemplate;

    @Override
    public PageListRes userList(QueryVo vo) {
        PageListRes pageListRes = new PageListRes();
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<User> users = userMapper.selectAll(vo);
        pageListRes.setRows(users);
        pageListRes.setTotal(page.getTotal());
        return pageListRes;
    }

    @Override
    public AjaxRes addUser(User user) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            userMapper.insert(user);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
        }catch (Exception e){
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes updateUser(User user) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            userMapper.updateByPrimaryKey(user);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
        }catch (Exception e){
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes updateUserState(long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            userMapper.updateUserState(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
        }catch (Exception e){
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void updateUState(String username) {

    }

    @Override
    public String addUserToRedis(User user) {
        String userToken = UUID.randomUUID().toString().replace("-", "")+user.getUsername();
        jsonRedisTemplate.opsForValue().set(userToken,user);
        return userToken;
    }

    @Override
    public User getUserByUserToken(String userToken) {
        User user = (User) jsonRedisTemplate.opsForValue().get(userToken);
        System.out.println(user);
        return user;
    }

    @Override
    public Boolean deleteUserByUserToken(String userToken) {
        return jsonRedisTemplate.delete(userToken);
    }
}
