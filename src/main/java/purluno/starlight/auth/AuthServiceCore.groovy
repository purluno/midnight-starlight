package purluno.starlight.auth

import javax.annotation.Resource

import org.hibernate.SessionFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthServiceCore implements AuthService {
	static Logger logger = LoggerFactory.getLogger(AuthServiceCore)

	@Resource
	SessionFactory sessionFactory

	void addRole(Role role) {
		def session = sessionFactory.currentSession
		session.save(role)
	}

	void addUser(User user) {
		def session = sessionFactory.currentSession
		session.save(user)
	}

	Role getRole(String name) {
		def session = sessionFactory.currentSession
		session.get(Role, name)
	}

	User getUser(String principal) {
		def session = sessionFactory.currentSession
		session.get(User, principal)
	}

	Role prepareRole(String name) {
		def session = sessionFactory.currentSession
		def role = session.get(Role, name)
		if (role != null) {
			return role
		}
		role = new Role(name: name)
		if (name == "signed-guest") {
			role.description = "손님"
			role.permissions = ["guestbook:add,read"] as Set
		} else if (name == "owner") {
			role.description = "주인"
			role.permissions = ["guestbook:*", "dashboard:*"] as Set
		} else {
			logger.warn("The role '{}' isn't in the preset", name)
		}
		session.save(role)
		role
	}
}
