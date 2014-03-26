package purluno.starlight.guestbook

import javax.annotation.Resource

import org.apache.shiro.SecurityUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.View
import org.springframework.web.servlet.view.json.MappingJackson2JsonView

import purluno.starlight.auth.AuthUtils
import twitter4j.User

@Controller
@RequestMapping("guestbook")
class GuestbookController {
	@Resource
	GuestbookService guestbookService

	@RequestMapping("")
	String root(Model model) {
		"guestbook/root"
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	String add(@RequestParam String text) {
		def subject = SecurityUtils.subject
		subject.checkRole("signed-guest")
		def principal = subject.principal as String
		def item = new GuestbookItem()
		item.authorId = principal
		item.writtenDate = new Date()
		item.text = text
		if (AuthUtils.origin(principal) == "twitter") {
			def user = subject.session.getAttribute("twitterUser") as User
			item.authorName = user.name
			item.authorProfileImageUrl = user.profileImageURL
		}
		def errors = new BeanPropertyBindingResult(item, "item")
		new GuestbookItemValidator().validate(item, errors)
		if (!errors.hasErrors()) {
			guestbookService.add(item)
		} else {
			// TODO should code properly
			throw new RuntimeException("validation is failed")
		}
		"redirect:/guestbook"
	}

	@RequestMapping(value = "ajax/list.json", method = RequestMethod.GET)
	View ajaxListJson(Model model,
			@RequestParam(defaultValue = "0") int first,
			@RequestParam(defaultValue = "5") int max) {
		max = Math.min(5, max)
		def items = guestbookService.getList(first, max)
		model.addAllAttributes([
			first: first,
			max: max,
			items: items
		])
		new MappingJackson2JsonView()
	}
}
