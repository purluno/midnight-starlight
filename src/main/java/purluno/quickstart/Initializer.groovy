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

/**
 * web.xml을 대신하여 웹 애플리케이션 설정 및 초기화를 수행하는 클래스.
 * 
 * @author 송영환
 */
class Initializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// 요청 텍스트 데이터의 인코딩을 UTF-8로 간주한다.
		def encodingFilter = new CharacterEncodingFilter()
		encodingFilter.encoding = "UTF-8"
		servletContext.addFilter("encodingFilter", encodingFilter)
			.addMappingForUrlPatterns(null, false, "/*")

		// Apache Shiro를 사용하기 위한 Spring 필터 프록시 등록
		def shiroFilter = new DelegatingFilterProxy()
		shiroFilter.targetFilterLifecycle = true
		servletContext.addFilter("shiroFilter", shiroFilter)
			.addMappingForUrlPatterns(null, false, "/*")

		// 최상위 컨텍스트 설정
		def rootContext = new AnnotationConfigWebApplicationContext()
		rootContext.register(AppConfig)
		servletContext.addListener(new ContextLoaderListener(rootContext))

		// 웹 컨텍스트 설정
		def webContext = new AnnotationConfigWebApplicationContext()
		webContext.servletContext = servletContext
		webContext.register(WebConfig)
		new ResourceHandlerRegistry(webContext, servletContext)
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/")

		// 스프링의 DispatcherServlet 등록
		def dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext))
		dispatcher.loadOnStartup = 1
		dispatcher.addMapping("/")
	}
}
