package purluno.starlight.auth

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

import org.springframework.util.ObjectUtils;

@Entity
class Role {
	@Id
	String name

	String description

	@ElementCollection(fetch = FetchType.EAGER)
	Set<String> permissions

	@Override
	int hashCode() {
		name.hashCode()
	}

	@Override
	boolean equals(Object obj) {
		if (obj == null) {
			false
		} else if (this.is(obj)) {
			true
		} else if (name == null) {
			false
		} else {
			(obj in Role && this.name == obj.name)
		}
	}
}
