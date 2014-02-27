package purluno.starlight.counter

import javax.annotation.Resource

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CounterService {
	long getTodayHits()

	long getTotalHits()
	
	void hit()
}

@Service
@Transactional
class DefaultCounterService implements CounterService {
	@Resource
	private SessionFactory sessionFactory
	
	private java.sql.Date getToday() {
		new java.sql.Date(System.currentTimeMillis())
	}

	long getTodayHits() {
		def today = getToday()
		def session = sessionFactory.currentSession
		def c = session.get(DailyCounter, today) as DailyCounter
		if (c == null) {
			c = new DailyCounter()
			c.date = today
			c.hits = 0
			session.save(c)
		}
		c.hits
	}
	
	@Override
	long getTotalHits() {
		def session = sessionFactory.currentSession
		def c = session.get(TotalCounter, 1L) as TotalCounter
		if (c == null) {
			c = new TotalCounter()
			c.hits = 0
			session.save(c)
		}
		c.hits
	}

	@Override
	void hit() {
		def session = sessionFactory.currentSession
		hitTotalCounter(session)
		hitDailyCounter(session)
	}

	private void hitTotalCounter(Session session) {
		def c = session.get(TotalCounter, 1L) as TotalCounter
		if (c != null) {
			c.hits = (c.hits ?: 0) + 1
			session.update(c)
		} else {
			c = new TotalCounter()
			c.hits = 1
			session.save(c)
		}
	}
	
	private void hitDailyCounter(Session session) {
		def today = getToday()
		def c = session.get(DailyCounter, today) as DailyCounter
		if (c != null) {
			c.hits = (c.hits ?: 0) + 1
			session.update(c)
		} else {
			c = new DailyCounter()
			c.date = today
			c.hits = 1
			session.save(c)
		}
	}
}
