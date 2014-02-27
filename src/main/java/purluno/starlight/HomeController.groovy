package purluno.starlight

import javax.annotation.Resource

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import purluno.starlight.counter.CounterService

@Controller
class HomeController {
	static Random random = new Random()
	
	@Resource
	CounterService counterService

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String home(Model model) {
		model.addAllAttributes([
			date: new Date(),
			totalHits: counterService.totalHits
		])
		return "home"
	}

	@RequestMapping(value = "/dice", method = RequestMethod.GET)
	String dice(Model model) {
		model.addAllAttributes([
			dice: random.nextInt(6) + 1
		])
		return "dice"
	}

	@RequestMapping(value = "seasons", method = RequestMethod.GET)
	String seasons(Model model) {
		model.addAllAttributes([
			seasons: ["봄", "여름", "가을", "겨울"]
		])
		return "seasons"
	}
}
