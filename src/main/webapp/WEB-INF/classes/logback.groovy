import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.*

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
	pattern = "%d{HH:mm:ss.SSS} [%.5thread] %.-1level %logger{36} - %msg%n%xEx"
  }
}

logger("org.hibernate", INFO)
logger("org.hibernate.SQL", DEBUG)
logger("org.hibernate.tool.hbm2ddl", DEBUG)
logger("org.springframework", INFO)

root(DEBUG, ["STDOUT"])
