package purluno.quickstart.people

import javax.annotation.Resource

import org.hibernate.SessionFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DefaultPeopleService implements PeopleService {
	@Resource
	private SessionFactory sessionFactory

	@Override
	List<Person> getAll() {
		def session = sessionFactory.currentSession
		session.createQuery("from Person p left join fetch p.family").list()
	}

	@Override
	List<Family> getAllFamilies() {
		def session = sessionFactory.currentSession
		session.createQuery("from Family f left join fetch f.people").list().unique()
	}

	@Override
	void initialize() {
		def session = sessionFactory.currentSession
		session.createQuery("delete from Person").executeUpdate()
		session.createQuery("delete from Family").executeUpdate()
		[
			new Family(name: "김").addPeople([
				new Person(name: "연아", gender: Gender.FEMALE),
				new Person(name: "수현", gender: Gender.MALE),
				new Person(name: "동성", gender: Gender.MALE),
				new Person(name: "용판", gender: Gender.MALE),
				new Person(name: "태희", gender: Gender.FEMALE)
			] as Set),
			new Family(name: "이").addPeople([
				new Person(name: "상화", gender: Gender.FEMALE),
				new Person(name: "규혁", gender: Gender.MALE),
				new Person(name: "호석", gender: Gender.MALE),
				new Person(name: "승훈", gender: Gender.MALE),
				new Person(name: "상엽", gender: Gender.MALE)
			] as Set),
			new Family(name: "박").addPeople([
				new Person(name: "승희", gender: Gender.FEMALE),
				new Person(name: "보영", gender: Gender.FEMALE),
				new Person(name: "세영", gender: Gender.MALE),
				new Person(name: "해진", gender: Gender.MALE),
				new Person(name: "승주", gender: Gender.FEMALE)
			] as Set)
		].each {
			session.save(it)
		}
	}
}
