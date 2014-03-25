package purluno.starlight.auth

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection

/**
 * 모든 인증 시도를 허가하며 signed-guest 역할을 인가한다.
 * 
 * @author 송영환
 */
class DefaultRealm extends AuthorizingRealm {
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		def authzInfo = new SimpleAuthorizationInfo()
		authzInfo.addRole("signed-guest")
		authzInfo
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		new SimpleAuthenticationInfo(token.principal, token.credentials, "default")
	}
}
