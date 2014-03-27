package purluno.starlight.dashboard

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("dashboard")
class DashboardController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	String index() {
		"dashboard/index"
	}
}
