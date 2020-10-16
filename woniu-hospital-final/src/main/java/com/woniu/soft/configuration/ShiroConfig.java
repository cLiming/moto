package com.woniu.soft.configuration;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.woniu.soft.shiro.LoginFilter;
import com.woniu.soft.shiro.WorkerRealm;



/**
 * Shiro配置类
 * 
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfig {
	@Bean
	public WorkerRealm initRealm() {
		return new WorkerRealm();
	}

	@Bean
	public SecurityManager initSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 注入领域对象
		securityManager.setRealm(initRealm());
		// 注入rememberMe组件
		securityManager.setRememberMeManager(rememberManager());
		return securityManager;
	}
	@Bean
	public CookieRememberMeManager rememberManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		//创建一个Cookie
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setMaxAge(3*24*60*60);
		cookieRememberMeManager.setCookie(cookie);
		
		cookieRememberMeManager.setCipherKey(Base64.decode("wGiHplamyXlVB11UXWol8g=="));
		return cookieRememberMeManager;
	}
	@Bean
	@Scope("prototype")
	public Filter loginFilter() {
		return new LoginFilter();
	}
	// 实例化Shiro过滤器工厂组件
	@Bean
	public ShiroFilterFactoryBean shiroFilter() throws UnsupportedEncodingException {
		// 实例化Filter工厂
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 注册securityManager
		shiroFilterFactoryBean.setSecurityManager(initSecurityManager());
		//将自定义的过滤器加入到Shiro的过滤器工厂中
		//创建一个过滤器键值对 用于匹配不同的校验规则
//		HashMap<String, Filter> filters = new HashMap<String,Filter>();
//		filters.put("authc", loginFilter());
//		shiroFilterFactoryBean.setFilters(filters);
		// 设置Shiro过滤器过滤规则
		// LinkHashMap是有序的，shiro会根据添加的顺序进行拦截,匹配到过滤器后就执行该过滤器不会在继续向下查找过滤器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截地址规则
		// anon:所有的url都可以不登陆的情况下访问
		// authc：所有url都必须认证通过才可以访问
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/lib/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/user/login", "anon");
		filterChainDefinitionMap.put("/workers/login", "anon");
		filterChainDefinitionMap.put("/page/login.html", "anon");

		filterChainDefinitionMap.put("/page/userlogin.html", "anon");
		filterChainDefinitionMap.put("/page/userindex.html", "anon");
		filterChainDefinitionMap.put("/page/user/yuyue.html", "anon");
		filterChainDefinitionMap.put("/page/user/preyuyue.html", "anon");
		filterChainDefinitionMap.put("/page/user/checked.html", "anon");
		filterChainDefinitionMap.put("/user/getdept", "anon");//
		filterChainDefinitionMap.put("/user/getUser", "anon");
		filterChainDefinitionMap.put("/nurse/getAllDocotor", "anon");
		filterChainDefinitionMap.put("/nurse/getAllNurse", "anon");
		filterChainDefinitionMap.put("/nurse/Admissionregistration", "anon");
		filterChainDefinitionMap.put("/user/updateUserButton", "anon");
		// 如果不满足上方所有的规则 则需要进行登录验证
		filterChainDefinitionMap.put("/logout", "logout");
		//在登陆之后或者通过记住我登陆之后都可以正常访问
		filterChainDefinitionMap.put("/**", "user");
		// 未登录时重定向的网页地址
		shiroFilterFactoryBean.setLoginUrl("/page/login.html");
		// 将地址过滤规则设置到Filter工厂中
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		// 返回
		return shiroFilterFactoryBean;
	}
	//配置控制层的前置通知
	//在通知中判断权限是否足够
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
	    advisor.setSecurityManager(initSecurityManager());
	    return advisor;
	}
	//强制使用CGLIB动态代理
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator app=new DefaultAdvisorAutoProxyCreator();
	    app.setProxyTargetClass(true);
	    return app;
	}
}