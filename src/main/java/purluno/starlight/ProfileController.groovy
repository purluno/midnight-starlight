package purluno.starlight

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class ProfileController {
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	String profile() {
		"profile"
	}
}
