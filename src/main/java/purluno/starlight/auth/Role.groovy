package purluno.starlight.auth

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

@Entity
class Role {
	@Id
	String name

	String description

	@ElementCollection(fetch = FetchType.EAGER)
	Set<String> permissions
}
