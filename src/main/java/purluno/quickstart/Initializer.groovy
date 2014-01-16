package purluno.quickstart

import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration

import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.context.support.GenericWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

class Initializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		def rootContext = new GenericWebApplicationContext(servletContext)
		new ClassPathBeanDefinitionScanner(rootContext).scan(this.class.package.name)
		new GroovyBeanDefinitionReader(rootContext).beans {
			freemarkerConfig(FreeMarkerConfigurer) {
				templateLoaderPath = "/WEB-INF/views/"
			}
			viewResolver(FreeMarkerViewResolver) {
				cache = false
				prefix = ""
				suffix = ".ftl"
				contentType = "text/html; charset=UTF-8"
				requestContextAttribute = "rc"
			}
		}
		new ResourceHandlerRegistry(rootContext, servletContext)
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/")
		def dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext))
		dispatcher.loadOnStartup = 1
		dispatcher.addMapping("/")
	}
}
