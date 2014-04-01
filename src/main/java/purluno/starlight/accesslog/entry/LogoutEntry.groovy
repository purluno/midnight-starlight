package purluno.starlight.accesslog.entry

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.ManyToOne

import purluno.starlight.auth.User

@Entity
@DiscriminatorValue("logout")
class LogoutEntry extends AccessEntry {
	@ManyToOne
	User user

	@Override
	String getType() {
		"로그아웃"
	}
}
