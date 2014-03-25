package purluno.starlight.guestbook

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("guestbook")
class GuestbookController {
	@RequestMapping("")
	String root() {
		"guestbook/root"
	}
}
