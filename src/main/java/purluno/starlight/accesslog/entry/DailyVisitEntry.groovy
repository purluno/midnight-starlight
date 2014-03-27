package purluno.starlight.accesslog.entry

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("daily-visit")
class DailyVisitEntry extends AccessEntry {
}
