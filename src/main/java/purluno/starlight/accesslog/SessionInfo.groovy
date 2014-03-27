package purluno.starlight.accesslog

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OrderBy

import purluno.starlight.accesslog.entry.AccessEntry
import purluno.starlight.auth.User

@Entity
class SessionInfo {
	@Id
	String id

	String host

	@ManyToOne
	User user
}
