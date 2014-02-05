package purluno.quickstart

import javax.servlet.ServletContext
import javax.servlet.ServletException

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.filter.DelegatingFilterProxy
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry

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
		rootContext.register(AppConfig)
		servletContext.addListener(new ContextLoaderListener(rootContext))

		def webContext = new AnnotationConfigWebApplicationContext()
		webContext.servletContext = servletContext
		webContext.register(WebConfig)
		new ResourceHandlerRegistry(webContext, servletContext)
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/")

		def dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext))
		dispatcher.loadOnStartup = 1
		dispatcher.addMapping("/")
	}
}
