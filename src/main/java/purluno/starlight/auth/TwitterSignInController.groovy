package purluno.starlight.auth

import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.web.util.WebUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import purluno.starlight.accesslog.AccessLogService
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken

@Controller
class TwitterSignInController {
	protected Logger logger = LoggerFactory.getLogger(TwitterSignInController)

	@Resource
	AccessLogService accessLogService

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
	void signInWithTwitterCallback(
			@RequestParam(value = "oauth_verifier", defaultValue = "") String oauthVerifier,
			@RequestParam(value = "denied", defaultValue = "") String denied,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if (!denied.isEmpty()) {
			WebUtils.issueRedirect(request, response, "/")
			return
		}
		try {
			Twitter twitter = session.getAttribute("twitter")
			RequestToken token = session.getAttribute("twitter-request-token")
			twitter.getOAuthAccessToken(token, oauthVerifier)
			session.removeAttribute("twitter-request-token")
			def principal = "${twitter.screenName}@twitter"
			accessLogService.loginAttempt(principal)
			SecurityUtils.subject.login(new UsernamePasswordToken(principal, ""))
			accessLogService.login()
			def user = twitter.showUser(twitter.id)
			session.setAttribute("twitterUser", user)
			session.setAttribute("userProfileImageUrl", user.profileImageURL)
			WebUtils.redirectToSavedRequest(request, response, "/")
		} catch (AuthenticationException e) {
			// unexpected exception
			logger.error("unexpected authentication exception", e)
			throw e
		}
	}
}
