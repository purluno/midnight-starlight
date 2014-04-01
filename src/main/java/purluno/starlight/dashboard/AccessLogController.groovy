package purluno.starlight.dashboard

import javax.annotation.Resource

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import purluno.starlight.accesslog.AccessLogService

@Controller
@RequestMapping("dashboard/accesslog")
class AccessLogController {
	@Resource
	private AccessLogService accessLogService

	@RequestMapping("")
	String index(Model model, @RequestParam(defaultValue = "1") int page) {
		def pr = accessLogService.pagedResult(page)
		model.addAttribute("pagedResult", pr)
		"dashboard/accesslog/index"
	}
}
