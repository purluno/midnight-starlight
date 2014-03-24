package purluno.starlight.twitter

import org.apache.shiro.authc.AuthenticationToken

import twitter4j.Twitter

class TwitterAuthToken implements AuthenticationToken {
	Twitter twitter

	String oauthVerifier

	@Override
	public Twitter getPrincipal() {
		twitter
	}

	@Override
	public String getCredentials() {
		oauthVerifier
	}
}
