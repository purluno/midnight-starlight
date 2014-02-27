package purluno.starlight.counter

import java.sql.Date

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DailyCounter {
	@Id
	Date date

	Long hits
}
