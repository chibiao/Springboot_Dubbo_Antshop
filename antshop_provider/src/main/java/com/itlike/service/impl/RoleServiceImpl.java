package com.itlike.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.mapper.RoleMapper;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.Permission;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.Role;
import com.itlike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public PageListRes getRoles(QueryVo vo) {
        /*调用mapper 查询员工 */
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Role> roles = roleMapper.selectAll();
        /*封装成pageList*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(roles);
        return pageListRes;
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
        /*保存角色与权限之间的关系*/
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getRid(),permission.getPid());
        }
    }

    @Override
    public void updateRole(Role role) {
        /*打破角色与权限之间的关系*/
        roleMapper.deleteRoleAndPermissionRel(role.getRid());
        /*更新角色*/
        roleMapper.updateByPrimaryKey(role);
        /*重新建立角色与权限之间的关系*/
        /*保存角色与权限之间的关系*/
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getRid(),permission.getPid());
        }
    }

    @Override
    public void deleteRole(long rid) {
        /*删除关系*/
        roleMapper.deleteRoleAndPermissionRel(rid);
        /*删除角色*/
        roleMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public List<Role> roleList() {
        return roleMapper.selectAll();
    }
    /*根据用户id查询对应的角色*/
    @Override
    public List<Long> getRoleByAid(Long id) {
        return  roleMapper.getRoleWithId(id);
    }
}
