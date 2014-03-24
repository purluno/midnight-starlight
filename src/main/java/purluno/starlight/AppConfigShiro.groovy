package purluno.starlight

import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.filter.authc.LogoutFilter
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

import purluno.starlight.twitter.TwitterAuthToken
import purluno.starlight.twitter.TwitterRealm


/**
 * Apache Shiro 관련 설정.
 * 
 * @author 송영환
 */
@Configuration
class AppConfigShiro {
	@Bean
	def delegatingFilterProxy() {
		def f = new ShiroFilterFactoryBean()
		f.securityManager = securityManager()
//		f.loginUrl = "/securitySample/login"
//		f.successUrl = "/securitySample/intro"
//		f.unauthorizedUrl = "/securitySample/unauthorized"
		f.filters = [
			logout: logoutFilter()
		]
		f.filterChainDefinitionMap = [
			"/securitySample/adminOnly": "authc, roles[admin]",
			"/securitySample/allowAnonymous": "anon",
			"/securitySample/forKnown": "authc",
			"/securitySample/intro": "anon",
			"/securitySample/login": "authc",
			"/securitySample/logout": "logout",
//			"/**": "authc"
		]
		f
	}
	
	@Bean
	def logoutFilter() {
		def f = new LogoutFilter()
		f.redirectUrl = "/"
		f
	}
	
	@Bean
	def securityManager() {
		def m = new DefaultWebSecurityManager(twitterRealm())
	}
	
	@Bean
	def lifecycleBeanPostProcessor() {
		new LifecycleBeanPostProcessor()
	}
	
	@Bean
	def twitterRealm() {
		def realm = new TwitterRealm()
		realm.setAuthenticationTokenClass(TwitterAuthToken)
		realm
	}
	
	@Bean @DependsOn("lifecycleBeanPostProcessor")
	def defaultAdvisorAutoProxyCreator() {
		new DefaultAdvisorAutoProxyCreator()
	}
	
	@Bean
	def authorizationAttributeSourceAdvisor() {
		def a = new AuthorizationAttributeSourceAdvisor()
		a.securityManager = securityManager()
	}
}
