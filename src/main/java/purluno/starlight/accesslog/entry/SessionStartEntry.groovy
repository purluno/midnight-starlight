package purluno.starlight.accesslog.entry

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("session-start")
class SessionStartEntry extends AccessEntry {
	@Override
	String getType() {
		"세션 시작"
	}
}
