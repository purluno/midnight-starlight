package purluno.starlight.counter

import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

class CounterInterceptor extends HandlerInterceptorAdapter {
	protected Logger logger = LoggerFactory.getLogger(CounterInterceptor)

	@Resource
	private CounterService counterService

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		def session = request.session
		Boolean c = session.getAttribute("counter-check") ?: false
		if (!c) {
			counterService.hit()
			session.setAttribute("counter-check", true)
		}
		true
	}
}
