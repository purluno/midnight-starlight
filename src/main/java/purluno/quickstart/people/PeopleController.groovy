package purluno.quickstart.people

import javax.annotation.Resource

import org.springframework.stereotype.Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("people")
class PeopleController {
	@Resource
	private PeopleService peopleService

	@RequestMapping
	String index() {
		return "redirect:/people/list"
	}

	@RequestMapping("initialize")
	String initialize() {
		peopleService.initialize()
		return "redirect:/people/list"
	}

	@RequestMapping("list")
	String list(Model model) {
		model.addAttribute("people", peopleService.all)
		model.addAttribute("families", peopleService.allFamilies)
		return "people/list"
	}
}
