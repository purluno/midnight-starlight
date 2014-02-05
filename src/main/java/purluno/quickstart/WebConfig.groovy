package purluno.quickstart

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver

/**
 * Spring MVC 관련 설정.
 * 
 * @author 송영환
 */
@EnableWebMvc
@ComponentScan(basePackageClasses = WebConfig, includeFilters = @ComponentScan.Filter(Controller))
class WebConfig {
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
}