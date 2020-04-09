package com.hcb.newBilly.config;

import com.hcb.newBilly.common.BillyException;
import com.hcb.newBilly.common.JwtUtils;
import com.hcb.newBilly.dao.UserDao;
import com.hcb.newBilly.po.UserPO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ShiroRealm extends AuthenticatingRealm {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public String getName() {
        return "ShiroRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        String userAccount = JwtUtils.getUserAccount(token);
        UserPO userPO = userDao.getUserByUserAccount(userAccount);
        if (null == userPO) {
            throw new BillyException("用户为空");
        } else {
            if (JwtUtils.checkUser(token, userPO.getUserAccount())) {
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());
                return info;
            } else {
                throw new BillyException("token认证失败");
            }

        }
    }
}
