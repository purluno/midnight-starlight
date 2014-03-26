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

	Boolean hiddenFromOther = false

	Date writtenDate

	String text
}
