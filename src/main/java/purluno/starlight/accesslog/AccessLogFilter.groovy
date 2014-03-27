package purluno.starlight.accesslog

import javax.annotation.Resource
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.web.filter.OncePerRequestFilter

class AccessLogFilter extends OncePerRequestFilter {
	@Resource
	AccessLogService accessLogService

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		def urlBuffer = request.requestURL
		def queryString = request.queryString
		if (queryString != null) {
			urlBuffer.append("?" as char)
			urlBuffer.append(queryString)
		}
		def entry = accessLogService.latestEntryOfCurrentSession
		if (entry == null) {
			accessLogService.sessionStart()
		}
		if (entry == null || entry.when.clearTime() < new Date().clearTime()) {
			accessLogService.dailyVisit()
		}
		accessLogService.request(request.method, urlBuffer.toString())
		filterChain.doFilter(request, response)
	}
}
