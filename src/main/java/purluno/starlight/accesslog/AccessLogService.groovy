package purluno.starlight.accesslog

import purluno.starlight.accesslog.entry.AccessEntry
import purluno.starlight.accesslog.entry.DailyVisitEntry
import purluno.starlight.accesslog.entry.LoginAttemptEntry
import purluno.starlight.accesslog.entry.LoginEntry
import purluno.starlight.accesslog.entry.LogoutEntry
import purluno.starlight.accesslog.entry.RequestEntry
import purluno.starlight.accesslog.entry.SessionStartEntry

interface AccessLogService {
	AccessEntry access()

	AccessEntry access(String description)

	DailyVisitEntry dailyVisit()

	DailyVisitEntry dailyVisit(String description)

	AccessEntry getLatestEntryOfCurrentSession()

	AccessEntry getLatestEntryOfSession(String sessionId)

	SessionInfo getSessionInfo()

	LoginEntry login()

	LoginEntry login(String description)

	LoginAttemptEntry loginAttempt(String principal)

	LoginAttemptEntry loginAttempt(String principal, String description)

	LogoutEntry logout()

	LogoutEntry logout(String description)

	RequestEntry request(String method, String url)

	RequestEntry request(String method, String url, String description)

	SessionStartEntry sessionStart()

	SessionStartEntry sessionStart(String description)

	SessionInfo updateSessionInfo()
}
