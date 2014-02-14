package purluno.quickstart.people

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Family {
	@Id @GeneratedValue
	Long id

	String name

	@OneToMany(cascade=CascadeType.ALL, mappedBy="family")
	Set<Person> people = [] as Set

	protected void setPeople(Set<Person> people) {
		this.people = people
	}
	
	Family addPeople(Set<Person> people) {
		this.people.addAll(people)
		people.each { it.family = this }
		this
	}
}
