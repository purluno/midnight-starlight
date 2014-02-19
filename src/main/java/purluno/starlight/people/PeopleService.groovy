package purluno.starlight.people

import java.util.List

interface PeopleService {
	void initialize()

	List<Person> getAll()

	List<Family> getAllFamilies()
}
