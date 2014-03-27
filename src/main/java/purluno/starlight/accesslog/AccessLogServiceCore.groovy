package purluno.starlight.accesslog

import javax.annotation.Resource

import org.apache.shiro.SecurityUtils
import org.hibernate.SessionFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import purluno.starlight.accesslog.entry.AccessEntry
import purluno.starlight.accesslog.entry.DailyVisitEntry
import purluno.starlight.accesslog.entry.LoginAttemptEntry
import purluno.starlight.accesslog.entry.LoginEntry
import purluno.starlight.accesslog.entry.LogoutEntry
import purluno.starlight.accesslog.entry.RequestEntry
import purluno.starlight.accesslog.entry.SessionStartEntry
import purluno.starlight.auth.AuthService
import purluno.starlight.auth.User

@Service("accessLogService")
@Transactional
class AccessLogServiceCore implements AccessLogService {
	@Resource
	SessionFactory sessionFactory

	@Resource
	AuthService authService

	@Override
	AccessEntry access() {
		access("")
	}

	@Override
	AccessEntry access(String description) {
		def hibSession = sessionFactory.currentSession
		def e = new AccessEntry(
			sessionInfo: sessionInfo,
			description: description)
		hibSession.save(e)
		setLatestEntryForSession(e)
		e
	}

	protected SessionInfo buildSessionInfo() {
		def subject = SecurityUtils.subject
		def session = subject.session
		new SessionInfo(
			id: session.id,
			user: authService.getUser(subject.principal),
			host: session.host)
	}

	@Override
	DailyVisitEntry dailyVisit() {
		dailyVisit("")
	}

	AccessEntry getLatestEntryOfCurrentSession() {
		SecurityUtils.subject.session.getAttribute("latestAccessEntry")
	}

	@Override
	AccessEntry getLatestEntryOfSession(String sessionId) {
		def session = SecurityUtils.subject.session
		if (session.id as String == sessionId) {
			return getLatestEntryOfCurrentSession()
		}
		def hibSession = sessionFactory.currentSession
		def hql = """\
from AccessEntry as entry
	join fetch entry.sessionInfo as sessionInfo
where sessionInfo.id = :sessionId
order by entry.when desc, entry.id desc
"""
		hibSession.createQuery(hql)
			.setString("sessionId", sessionId)
			.setMaxResults(1)
			.uniqueResult()
	}

	@Override
	DailyVisitEntry dailyVisit(String description) {
		def hibSession = sessionFactory.currentSession
		def e = new DailyVisitEntry(
			sessionInfo: sessionInfo,
			description: description)
		hibSession.save(e)
		setLatestEntryForSession(e)
		e
	}

	@Override
	SessionInfo getSessionInfo() {
		def hibSession = sessionFactory.currentSession
		def id = SecurityUtils.subject.session.id as String
		def info = hibSession.get(SessionInfo, id) as SessionInfo
		if (info == null) {
			info = buildSessionInfo()
			hibSession.save(info)
		}
		info
	}

	@Override
	LoginEntry login() {
		login("")
	}

	@Override
	LoginEntry login(String description) {
		def hibSession = sessionFactory.currentSession
		def e = new LoginEntry(
			sessionInfo: sessionInfo,
			description: description,
			user: authService.getUser(SecurityUtils.subject.principal))
		hibSession.save(e)
		setLatestEntryForSession(e)
		e
	}

	@Override
	LoginAttemptEntry loginAttempt(String principal) {
		loginAttempt(principal, "")
	}

	@Override
	LoginAttemptEntry loginAttempt(String principal, String description) {
		def hibSession = sessionFactory.currentSession
		def e = new LoginAttemptEntry(
			sessionInfo: sessionInfo,
			description: description,
			targetUser: authService.getUser(principal),
			principal: principal)
		hibSession.save(e)
		setLatestEntryForSession(e)
		e
	}

	@Override
	LogoutEntry logout() {
		logout("")
	}

	@Override
	LogoutEntry logout(String description) {
		def hibSession = sessionFactory.currentSession
		def e = new LogoutEntry(
			sessionInfo: sessionInfo,
			description: description,
			user: authService.getUser(SecurityUtils.subject.principal))
		hibSession.save(e)
		setLatestEntryForSession(e)
		e
	}

	@Override
	RequestEntry request(String method, String url) {
		request(method, url, "")
	}

	@Override
	RequestEntry request(String method, String url, String description) {
		def hibSession = sessionFactory.currentSession
		def e = new RequestEntry(
			sessionInfo: sessionInfo,
			description: description,
			method: method,
			url: url,
			user: authService.getUser(SecurityUtils.subject.principal))
		hibSession.save(e)
		setLatestEntryForSession(e)
		e
	}

	@Override
	SessionStartEntry sessionStart() {
		sessionStart("")
	}

	@Override
	SessionStartEntry sessionStart(String description) {
		def hibSession = sessionFactory.currentSession
		def e = new SessionStartEntry(
			sessionInfo: sessionInfo,
			description: description)
		hibSession.save(e)
		setLatestEntryForSession(e)
		e
	}

	void setLatestEntryForSession(AccessEntry e) {
		SecurityUtils.subject.session.setAttribute("latestAccessEntry", e)
	}

	@Override
	SessionInfo updateSessionInfo() {
		def hibSession = sessionFactory.currentSession
		def info = buildSessionInfo()
		if (hibSession.get(SessionInfo, info.id) == null) {
			hibSession.save(info)
		} else {
			hibSession.update(info)
		}
	}
}
