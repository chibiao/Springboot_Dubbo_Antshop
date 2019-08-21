package com.itlike.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.mapper.AdminMapper;
import com.itlike.pojo.*;
import com.itlike.service.IAdminService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/8/4
 */
@Service  //dubbo的service
@Component
public class AdminServiceImpl implements IAdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public PageListRes adminList(QueryVo vo)  {
        PageListRes pageListRes = new PageListRes();
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Admin> admins = adminMapper.selectAll();
        pageListRes.setRows(admins);
        pageListRes.setTotal(page.getTotal());
        return pageListRes;
    }

    @Override
    public AjaxRes updateAdmin(Admin admin) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            /*打破员工和角色的关系*/
            adminMapper.deleteAdminAndRoleRel(admin.getId());
            /*保存员工*/
            adminMapper.updateByPrimaryKey(admin);
            /*保存员工和 角色 关系*/
            for (Role role : admin.getRoles()) {
                adminMapper.insertAdminAndRoleRel(admin.getId(),role.getRid());
            }
            adminMapper.updateByPrimaryKey(admin);
            ajaxRes.setMsg("添加成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes addAdmin(Admin admin)  {
        AjaxRes ajaxRes = new AjaxRes();
        /*把密码进行加密*/
        Md5Hash md5Hash = new Md5Hash(admin.getPassword(), admin.getName(), 2);
        admin.setPassword(md5Hash.toString());
        /*保存员工*/
        try {
            adminMapper.insert(admin);
            /*保存员工和 角色 关系*/
            for (Role role : admin.getRoles()) {
                adminMapper.insertAdminAndRoleRel(admin.getId(),role.getRid());
            }
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes deleteAdmin(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            adminMapper.deleteAdminAndRoleRel(id);
            adminMapper.deleteByPrimaryKey(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
        }
        return ajaxRes;
    }

    @Override
    public Admin getAdminByName(String name) {
        return adminMapper.getAdminByName(name);
    }

    @Override
    public List<String> getRolesById(Long id) {
        return adminMapper.getRolesById(id);
    }

    @Override
    public List<String> getPermissionById(Long id) {
        return adminMapper.getPermissionById(id);
    }
}
