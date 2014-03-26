package purluno.starlight

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Controller
import org.springframework.util.ResourceUtils

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
	static Logger logger = LoggerFactory.getLogger(AppConfig)

	@Bean
	Twitter defaultTwitter() {
		TwitterFactory.singleton
	}

	@Bean
	ConfigObject settings() {
		try {
			def url = ResourceUtils.getURL("classpath:settings.groovy")
			return new ConfigSlurper().parse(url)
		} catch (FileNotFoundException e) {
			logger.warn("settings.groovy not found")
		} catch (IOException e) {
			logger.warn("cannot read settings.groovy")
		}
		new ConfigObject()
	}
}
