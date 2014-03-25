package purluno.starlight.auth

class AuthUtils {
	static String origin(String principal) {
		int index = principal.lastIndexOf('@')
		if (index == -1) {
			""
		} else {
			principal.substring(index + 1)
		}
	}

	static String username(String principal) {
		int index = principal.lastIndexOf('@')
		if (index == -1) {
			principal
		} else {
			principal.substring(0, index)
		}
	}
}
