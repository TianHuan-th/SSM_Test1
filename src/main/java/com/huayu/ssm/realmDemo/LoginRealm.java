package com.huayu.ssm.realmDemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huayu.ssm.pojo.Emppwd;
import com.huayu.ssm.service.EmppwdService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 旗木卡卡西
 * @date 2020/8/4 9:06
 */
public class LoginRealm extends AuthorizingRealm {
    @Autowired
   private EmppwdService emppwdService;
    /*
登录认证
* */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*强转成实现类  可以获取controller里传的用户名和密码*/
        UsernamePasswordToken upToken=(UsernamePasswordToken)authenticationToken;
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("eaccount",upToken.getUsername());
        Emppwd emppwd=emppwdService.queryEmppwd(queryWrapper);
        if(null==emppwd){
            throw new UnknownAccountException("该用户不存在");
        }
        /*加上盐再传过去进行比对*/
        ByteSource miwen=ByteSource.Util.bytes(upToken.getUsername());
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(emppwd.getEaccount(),emppwd.getEpassword(),miwen,getName());
        return simpleAuthenticationInfo;
    }
    /*
    登录授权
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }


}
