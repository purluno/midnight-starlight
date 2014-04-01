package purluno.starlight.accesslog

import java.util.List

import purluno.starlight.accesslog.entry.AccessEntry
import purluno.starlight.accesslog.entry.DailyVisitEntry
import purluno.starlight.accesslog.entry.LoginAttemptEntry
import purluno.starlight.accesslog.entry.LoginEntry
import purluno.starlight.accesslog.entry.LogoutEntry
import purluno.starlight.accesslog.entry.RequestEntry
import purluno.starlight.accesslog.entry.SessionStartEntry
import purluno.starlight.util.PagedResult

interface AccessLogService {
	AccessEntry access()

	AccessEntry access(String description)

	long count()

	DailyVisitEntry dailyVisit()

	DailyVisitEntry dailyVisit(String description)

	AccessEntry getLatestEntryOfCurrentSession()

	AccessEntry getLatestEntryOfSession(String sessionId)

	SessionInfo getSessionInfo()

	List<AccessEntry> list(int first, int max)

	LoginEntry login()

	LoginEntry login(String description)

	LoginAttemptEntry loginAttempt(String principal)

	LoginAttemptEntry loginAttempt(String principal, String description)

	LogoutEntry logout()

	LogoutEntry logout(String description)

	PagedResult<AccessEntry> pagedResult(int page)

	RequestEntry request(String method, String url)

	RequestEntry request(String method, String url, String description)

	SessionStartEntry sessionStart()

	SessionStartEntry sessionStart(String description)

	SessionInfo updateSessionInfo()
}
