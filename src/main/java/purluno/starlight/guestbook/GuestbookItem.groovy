package purluno.starlight.guestbook

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class GuestbookItem {
	@Id @GeneratedValue
	Long id

	String authorId

	String authorName

	Date writtenDate

	String text
}
