package com.iflytek.utils;

import com.iflytek.domain.User;
import com.iflytek.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User u = (User) principals.getPrimaryPrincipal();

        //根据username查询权限信息
        User user = userMapper.getUserByUserName(u.getUsername());
        User users = userMapper.getAllRolesByUserId(user.getId());
        //输出信息
        System.out.println("roles================="+users.toString());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //授权角色和权限
        users.getRoles().forEach(role -> {
            info.addRole(role.getSn().toString());
            role.getPermissions().forEach(permission -> {
                info.addStringPermission(permission.getResource().toString());
            });
        });
        return info;
    }
    //身份验证  subject.login()时调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userMapper.getUserByUserName(username);
        if (user==null){
            return null;
        }
        //info表示realm登陆对比信息：参数1：用户信息（真实登陆中登陆对象user对象），参数2：密码，参数3;当前realm的名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getUsername()),
                getName());
        return info;
    }

    @Override
    public String getName() {
        return "UserRealm";
    }
}
