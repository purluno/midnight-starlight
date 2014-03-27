package purluno.starlight.accesslog.entry

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.ManyToOne

import purluno.starlight.auth.User

@Entity
@DiscriminatorValue("login")
class LoginEntry extends AccessEntry {
	@ManyToOne
	User user
}
