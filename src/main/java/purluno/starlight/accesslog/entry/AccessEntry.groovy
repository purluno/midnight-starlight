package purluno.starlight.accesslog.entry

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.ManyToOne

import purluno.starlight.accesslog.SessionInfo

@Entity
@Inheritance
@DiscriminatorValue("access")
class AccessEntry {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id

	@ManyToOne
	SessionInfo sessionInfo

	Date when = new Date()

	String description

	String getType() {
		"접근"
	}
}
