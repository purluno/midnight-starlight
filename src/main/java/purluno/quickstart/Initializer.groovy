package purluno.quickstart

import javax.servlet.ServletContext
import javax.servlet.ServletException

import org.apache.commons.dbcp.BasicDataSource
import org.h2.Driver
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner
import org.springframework.context.support.GenericGroovyApplicationContext
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.orm.hibernate4.LocalSessionFactoryBean
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.context.support.GenericWebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.filter.DelegatingFilterProxy
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver

class Initializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		def encodingFilter = new CharacterEncodingFilter()
		encodingFilter.encoding = "UTF-8"
		servletContext.addFilter("encodingFilter", encodingFilter)
			.addMappingForUrlPatterns(null, false, "/*")
			
		def shiroFilter = new DelegatingFilterProxy()
		shiroFilter.targetFilterLifecycle = true
		servletContext.addFilter("shiroFilter", shiroFilter)
			.addMappingForUrlPatterns(null, false, "/*")

		def rootContext = new AnnotationConfigWebApplicationContext()
		rootContext.servletContext = servletContext
		rootContext.register(AppConfig)
		new ResourceHandlerRegistry(rootContext, servletContext)
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/")
		servletContext.addListener(new ContextLoaderListener(rootContext))

		def dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext))
		dispatcher.loadOnStartup = 1
		dispatcher.addMapping("/")
	}
}
