package purluno.starlight

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.apache.shiro.SecurityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import org.springframework.web.servlet.view.RedirectView;

import purluno.starlight.auth.AuthUtils;
import twitter4j.Twitter;

/**
 * 뷰에서 활용할 다양한 모델을 추가한다.
 * <ul>
 * <li>env - Spring의 {@link Environment}
 * <li>subject - Apache Shiro의 SecurityUtils.subject
 * </ul>
 * 
 * @author 송영환
 */
class ModelInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware {
	protected Logger logger = LoggerFactory.getLogger(ModelInterceptor)

	private ApplicationContext applicationContext

	/**
	 * 뷰에서 활용할 다양한 모델을 추가한다. (클래스 설명 참조)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView == null) {
			return
		}
		if (modelAndView.view instanceof RedirectView) {
			return
		}
		if (modelAndView.viewName?.startsWith("redirect:")) {
			return
		}
		modelAndView.addObject("contextPath", request.contextPath)
		modelAndView.addObject("env", applicationContext.environment)
		def subject = SecurityUtils.subject
		modelAndView.addObject("subject", subject)
		def principal = subject.principal
		if (principal == null) {
			return
		}
		modelAndView.addObject("principal", principal)
		modelAndView.addObject("userProfileImageUrl", subject.session.getAttribute("userProfileImageUrl"))
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext
	}
}
