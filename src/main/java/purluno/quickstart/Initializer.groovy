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
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.context.support.GenericWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver

class Initializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		def rootContext = new AnnotationConfigWebApplicationContext()
		rootContext.servletContext = servletContext
		rootContext.register(AppConfig)
		new ResourceHandlerRegistry(rootContext, servletContext)
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/")
		def dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext))
		dispatcher.loadOnStartup = 1
		dispatcher.addMapping("/")
	}
}
