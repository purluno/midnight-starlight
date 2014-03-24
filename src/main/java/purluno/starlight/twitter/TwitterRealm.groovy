package purluno.starlight.twitter

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection

class TwitterRealm extends AuthorizingRealm {
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		if (principals.primaryPrincipal instanceof TwitterAuthToken) {
			def authzInfo = new SimpleAuthorizationInfo()
			authzInfo.addRole("signed-guest")
			authzInfo
		} else {
			null
		}
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		if (token instanceof TwitterAuthToken) {
			def t = token as TwitterAuthToken
			new SimpleAuthenticationInfo(t.principal, t.credentials, "twitter")
		} else {
			null
		}
	}
}
