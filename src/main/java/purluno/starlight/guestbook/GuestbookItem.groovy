package purluno.starlight.guestbook

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import purluno.starlight.auth.AuthUtils
import twitter4j.Twitter

@Entity
class GuestbookItem {
	@Id @GeneratedValue
	Long id

	String authorId

	String authorName

	String authorProfileImageUrl

	Date writtenDate

	String text
}
