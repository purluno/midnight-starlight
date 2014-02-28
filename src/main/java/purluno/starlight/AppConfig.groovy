package purluno.starlight

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Controller

import com.fasterxml.jackson.databind.ObjectMapper

/**
 * 스프링 최상위 컨텍스트 설정. Hibernate 설정과 Shiro 설정을 import한다.
 * 
 * @author 송영환
 */
@Configuration
@ComponentScan(basePackageClasses = AppConfig, excludeFilters = @ComponentScan.Filter(Controller))
@Import([AppConfigHibernate, AppConfigShiro])
class AppConfig {
	@Bean
	ObjectMapper objectMapper() {
		new ObjectMapper()
	}
}
