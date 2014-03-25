package purluno.starlight.auth

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken

@Controller
class TwitterSignInController {
	protected Logger logger = LoggerFactory.getLogger(TwitterSignInController)

	@RequestMapping(value = "sign-in-with-twitter", method = RequestMethod.GET)
	String signInWithTwitter(HttpServletRequest request, HttpSession session) {
		def twitter = new TwitterFactory().getInstance(null as AccessToken)
		session.setAttribute("twitter", twitter)
		def url = request.requestURL.append("-callback").toString()
		def token = twitter.getOAuthRequestToken(url)
		session.setAttribute("twitter-request-token", token)
		"redirect:${token.authenticationURL}"
	}

	@RequestMapping(value = "sign-in-with-twitter-callback", method = RequestMethod.GET)
	String signInWithTwitterCallback(
			@RequestParam(value = "oauth_verifier", defaultValue = "") String oauthVerifier,
			HttpServletRequest request,
			HttpSession session) {
		try {
			Twitter twitter = session.getAttribute("twitter")
			RequestToken token = session.getAttribute("twitter-request-token")
			twitter.getOAuthAccessToken(token, oauthVerifier)
			session.removeAttribute("twitter-request-token")
			def username = "${twitter.screenName}@twitter"
			SecurityUtils.subject.login(new UsernamePasswordToken(username, ""))
			"redirect:/"
		} catch (AuthenticationException e) {
			// unexpected exception
			logger.error("unexpected authentication exception", e)
			throw e
		}
	}
}
