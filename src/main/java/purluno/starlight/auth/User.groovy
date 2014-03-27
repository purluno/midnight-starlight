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

	@Override
	public int hashCode() {
		if (principal == null) {
			0
		} else {
			principal.hashCode()
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			false
		} else if (this.is(obj)) {
			true
		} else if (principal == null) {
			false
		} else {
			obj in User && principal == obj.principal
		}
	}
}
