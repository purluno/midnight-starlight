package purluno.starlight.accesslog.entry

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.ManyToOne

import purluno.starlight.auth.User

@Entity
@DiscriminatorValue("login-attempt")
class LoginAttemptEntry extends AccessEntry {
	@ManyToOne
	User targetUser

	String principal

	@Override
	String getType() {
		"로그인 시도"
	}
}
