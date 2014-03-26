package purluno.starlight.auth

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class User {
	@Id
	String principal

	@OneToMany(fetch = FetchType.EAGER)
	Set<Role> roles
}
