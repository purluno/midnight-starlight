package purluno.starlight.accesslog.entry

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.ManyToOne

import purluno.starlight.auth.User

@Entity
@DiscriminatorValue("request")
class RequestEntry extends AccessEntry {
	String method

	String url

	@ManyToOne
	User user
}
