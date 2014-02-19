package purluno.starlight.people

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity
class Person {
	@Id @GeneratedValue
	Long id

	String name

	@ManyToOne
	Family family

	@Enumerated(EnumType.STRING)
	Gender gender
}
