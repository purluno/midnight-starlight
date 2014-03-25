package purluno.starlight

import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.filter.authc.LogoutFilter
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

import purluno.starlight.auth.DefaultRealm


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
		f.loginUrl = "/sign-in-with-twitter"
//		f.successUrl = "/securitySample/intro"
//		f.unauthorizedUrl = "/securitySample/unauthorized"
		f.filters = [
			authc: passThruAuthenticationFilter(),
			logout: logoutFilter()
		]
		f.filterChainDefinitionMap = [
			"/guestbook/**": "authc, roles[signed-guest]",
			"/logout": "logout",
//			"/**": "authc"
		]
		f
	}

	@Bean
	def passThruAuthenticationFilter() {
		new PassThruAuthenticationFilter()
	}

	@Bean
	def logoutFilter() {
		def f = new LogoutFilter()
		f.redirectUrl = "/"
		f
	}

	@Bean
	def securityManager() {
		def m = new DefaultWebSecurityManager(defaultRealm())
	}

	@Bean
	def lifecycleBeanPostProcessor() {
		new LifecycleBeanPostProcessor()
	}

	@Bean
	def defaultRealm() {
		def realm = new DefaultRealm()
		realm.setAuthenticationTokenClass(UsernamePasswordToken)
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
