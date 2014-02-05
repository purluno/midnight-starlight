package purluno.quickstart

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

/**
 * 뷰에서 Shiro의 subject를 사용할 수 있도록 해준다.
 * 
 * @author 송영환
 */
class ShiroInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 뷰에서 Shiro의 subject를 사용할 수 있도록 해준다.
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("subject", SecurityUtils.subject)
	}
}
