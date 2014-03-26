package purluno.starlight.home

import javax.annotation.Resource

import org.slf4j.Logger
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import purluno.starlight.counter.CounterService
import twitter4j.Twitter

@Controller
class HomeController {
	static Logger logger = LoggerFactory.getLogger(HomeController)

	@Resource
	CounterService counterService

	@Resource
	Twitter defaultTwitter

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String home(Model model) {
		try {
			model.addAllAttributes([
				date: new Date(),
				totalHits: counterService.totalHits,
				todayHits: counterService.todayHits,
				twitterTimeline: defaultTwitter.userTimeline
			])
			"home"
		} catch (IllegalStateException e) {
			logger.debug("failed to add all attributes of the model", e)
			"redirect:/twitter-not-set"
		}
	}

	@RequestMapping(value = "twitter-not-set", method = RequestMethod.GET)
	String twitterNotSet() {
		"twitter-not-set"
	}
}
