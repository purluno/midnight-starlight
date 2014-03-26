package purluno.starlight.auth

import javax.annotation.Resource

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection

/**
 * 인증 및 인가를 관리하는 핵심 클래스
 * 
 * @author 송영환
 */
class DefaultRealm extends AuthorizingRealm {
	@Resource
	ConfigObject settings

	@Resource
	AuthService authService

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String principal = principals.primaryPrincipal as String
		def user = authService.getUser(principal)
		if (user != null) {
			if (principal == settings.owner) {
				user.roles += authService.prepareRole("owner")
			}
			new SimpleAuthorizationInfo(user.roles.collect { it.name } as Set)
		} else {
			new SimpleAuthorizationInfo()
		}
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		def principal = token.principal
		def user = authService.getUser(principal)
		if (user == null) {
			user = new User()
			user.principal = principal
			user.roles = [authService.prepareRole("signed-guest")] as Set
			authService.addUser(user)
		}
		new SimpleAuthenticationInfo(principal, token.credentials, "default")
	}
}
