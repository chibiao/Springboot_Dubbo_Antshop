package com.itlike.realm;

import com.itlike.pojo.Admin;
import com.itlike.service.IAdminService;
import com.itlike.utils.SpringBeanFactoryUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/8/8
 */
public class UserRealm extends AuthorizingRealm {
    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("来到了授权");
        Admin admin = (Admin) principals.getPrimaryPrincipal();
        IAdminService adminService = SpringBeanFactoryUtils.getBean("adminService", IAdminService.class);
        /*根据当前用户id,查询角色和权限*/
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        /*查询角色*/
        roles = adminService.getRolesById(admin.getId());
        /*查询权限*/
        permissions = adminService.getPermissionById(admin.getId());
        /*给授权信息*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println(permissions);
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("来到了认证");
        String username = (String) token.getPrincipal();
        IAdminService adminService = SpringBeanFactoryUtils.getBean("adminService", IAdminService.class);
        Admin admin = adminService.getAdminByName(username);
        if (admin == null) {
            return null;
        }
        /*参数 主体,正确的密码,盐,当前realm的名称*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin,
                admin.getPassword(),
                ByteSource.Util.bytes(admin.getName()),
                this.getName());
        return info;
    }
}
