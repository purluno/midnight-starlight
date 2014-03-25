package purluno.starlight

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Controller

import twitter4j.Twitter
import twitter4j.TwitterFactory

/**
 * 스프링 최상위 컨텍스트 설정. Hibernate 설정과 Shiro 설정을 import하고 기타 필요한 빈을 설정한다.
 * 
 * @author 송영환
 */
@Configuration
@ComponentScan(basePackageClasses = AppConfig, excludeFilters = @ComponentScan.Filter(Controller))
@Import([AppConfigHibernate, AppConfigShiro])
class AppConfig {
	@Bean
	Twitter defaultTwitter() {
		TwitterFactory.singleton
	}
}
