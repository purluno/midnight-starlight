package purluno.starlight.counter

import java.sql.Date

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class TotalCounter {
	@Id
	Long id = 1
	
	Long hits
}
