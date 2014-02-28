package purluno.starlight

import javax.annotation.Resource

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import purluno.starlight.counter.CounterService

@Controller
class HomeController {
	@Resource
	CounterService counterService

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String home(Model model) {
		model.addAllAttributes([
			date: new Date(),
			totalHits: counterService.totalHits,
			todayHits: counterService.todayHits
		])
		return "home"
	}
}
