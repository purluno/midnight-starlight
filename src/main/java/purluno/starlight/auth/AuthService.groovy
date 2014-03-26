package purluno.starlight.auth

interface AuthService {
	void addRole(Role role)

	void addUser(User user)

	Role getRole(String name)

	User getUser(String principal)

	Role prepareRole(String name)
}
