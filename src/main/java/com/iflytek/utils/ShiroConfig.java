package com.iflytek.utils;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ShiroConfig {
    //过滤器链
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        //过滤规则
        Map<String,String> filterChain = bean.getFilterChainDefinitionMap();
        //filterChain.put("/user/*", "authc");
        filterChain.put("/user/index", "authc,perms[user:query]");
        filterChain.put("/user/tags", "authc,perms[user:query]");

        bean.setLoginUrl("/login");
        bean.setUnauthorizedUrl("/unauthority");//没授权跳转页面
        return bean;
    }
    //安全管理器
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //自定义Realm
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
