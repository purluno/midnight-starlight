package purluno.quickstart

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.hibernate.SessionFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 스프링 컨텍스트 설정 클래스. 일반적인 XML 설정을 대체한다.
 * 
 * @author 송영환
 */
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackageClasses=AppConfig)
class AppConfig {
	@Bean
	def shiroInterceptor() {
		new ShiroInterceptor()
	}

	@Bean
	def requestMappingHandlerMapping() {
		def m = new RequestMappingHandlerMapping()
		m.interceptors = [shiroInterceptor()]
		m
	}

	@Bean
	FreeMarkerConfigurer freeMarkerConfigurer() {
		def fmc = new FreeMarkerConfigurer()
		fmc.templateLoaderPath = "/WEB-INF/views/"
		fmc
	}

	@Bean
	FreeMarkerViewResolver viewResolver() {
		def vr = new FreeMarkerViewResolver()
		vr.cache = false
		vr.prefix = ""
		vr.suffix = ".ftl"
		vr.contentType = "text/html; charset=UTF-8"
		vr.requestContextAttribute = "rc"
		vr
	}
	
	@Bean
	BasicDataSource dataSource() {
		def bds = new BasicDataSource()
		bds.driverClassName = org.h2.Driver.name
		bds.url = "jdbc:h2:~/.h2/quickstart;AUTO_SERVER=TRUE"
		bds
	}
	
	@Bean
	SessionFactory sessionFactory() {
		def b = new LocalSessionFactoryBuilder(dataSource())
		b.scanPackages(this.class.package.name)
		b.setProperty("hibernate.hbm2ddl.auto", "update")
		b.buildSessionFactory()
	}
	
	@Bean
	PlatformTransactionManager transactionManager() {
		def tm = new HibernateTransactionManager()
		tm.sessionFactory = sessionFactory()
		tm
	}
	
	@Bean
	def shiroFilter() {
		def f = new ShiroFilterFactoryBean()
		f.securityManager = securityManager()
		f.loginUrl = "/securitySample/login"
		f.successUrl = "/securitySample/intro"
		f.unauthorizedUrl = "/securitySample/intro"
//		f.filters = [
//			anAlias: someFilter()
//		]
		f.filterChainDefinitionMap = [
			"/admin/**": "authc, roles[admin]",
			"/docs/**": "authc, perms[document:read]",
			"/securitySample/login": "authc",
//			"/**": "authc"
		]
		f
	}
	
	@Bean
	def securityManager() {
		def m = new DefaultWebSecurityManager(myRealm())
	}
	
	@Bean
	def lifecycleBeanPostProcessor() {
		new LifecycleBeanPostProcessor()
	}
	
	@Bean
	def myRealm() {
		def r = new SimpleAccountRealm("myRealm")
		r.addAccount("john", "john100", "known-guest")
		r.addAccount("alice", "alice100", "admin")
		r
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
