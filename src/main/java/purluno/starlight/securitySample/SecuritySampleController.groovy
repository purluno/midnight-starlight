package purluno.starlight.securitySample

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.DisabledAccountException
import org.apache.shiro.authc.ExpiredCredentialsException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UnknownAccountException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("securitySample")
class SecuritySampleController {
	@RequestMapping(value = "adminOnly", method = RequestMethod.GET)
	String adminOnly() {
		"securitySample/adminOnly"
	}

	@RequestMapping(value = "allowAnonymous", method = RequestMethod.GET)
	String anonymousOnly() {
		"securitySample/allowAnonymous"
	}

	@RequestMapping(value = "forKnown", method = RequestMethod.GET)
	String forKnown() {
		"securitySample/forKnown"
	}

	@RequestMapping(value = "intro", method = RequestMethod.GET)
	String intro() {
		"securitySample/intro"
	}

	/**
	 * 이 컨트롤러 메소드는 실패한 로그인에 대하여 다시 로그인 폼이 있는 intro 페이지로
	 * 리다이렉션시키면서 URL 인자 loginFailure에 로그인 실패 사유를 전달한다.
	 * 다음은 로그인 요청에 대한 전반적인 처리 순서이다.
	 * <ol>
	 * <li> 사용자가 로그인 폼에 계정명과 비밀번호를 입력하고 로그인 버튼을 누르면
	 * 브라우저가 이 URL로 로그인을 요청하게 된다.
	 * <li> 실제로 로그인 작업을 수행하는 클래스는 Apache Shiro의 FormAuthenticationFilter이다.
	 * <li> 로그인에 성공하면 이 컨트롤러까지 도달하지 않고 성공 URL로 리다이렉션을 통해
	 * 이동시키고, 로그인에 실패하면 요청 속성 shiroLoginFailure에 예외클래스명을 지정하여
	 * 이 컨트롤러에 도달하게 된다.
	 * </ol>
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	String login(Model model, HttpServletRequest request) {
		def exceptionName = request.getAttribute("shiroLoginFailure") as String
		def loginFailure
		if (exceptionName == UnknownAccountException.name) {
			loginFailure = "unknown-account"
		} else if (exceptionName == DisabledAccountException.name) {
			loginFailure = "disabled-account"
		} else if (exceptionName == IncorrectCredentialsException.name) {
			loginFailure = "incorrect-credentials"
		} else if (exceptionName == ExpiredCredentialsException.name) {
			loginFailure = "expired-credentials"
		} else {
			loginFailure = "unknown-reason"
		}
		model.addAttribute("loginFailure", loginFailure)
		"redirect:/securitySample/intro"
	}
	
	/**
	 * 실제 로그인 폼이 있는 intro 페이지로 리다이렉트한다.
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	String loginGet() {
		"redirect:/securitySample/intro"
	}
	
	@RequestMapping(value = "unauthorized", method = RequestMethod.GET)
	String unauthorized() {
		"securitySample/unauthorized"
	}
}
