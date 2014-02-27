package purluno.starlight.counter

import javax.annotation.Resource

import org.hibernate.SessionFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CounterService {
	long getTotalHits()
	
	void hit()
}

@Service
@Transactional
class DefaultCounterService implements CounterService {
	@Resource
	private SessionFactory sessionFactory
	
	@Override
	long getTotalHits() {
		def session = sessionFactory.currentSession
		def tc = session.get(TotalCounter, 1L) as TotalCounter
		if (tc == null) {
			tc = new TotalCounter()
			tc.hits = 0
			session.save(tc)
		}
		tc.hits
	}

	@Override
	void hit() {
		def session = sessionFactory.currentSession
		def hql = "update TotalCounter set hits = hits + 1"
		def n = session.createQuery(hql).executeUpdate()
		if (n != 1) {
			session.createQuery("delete from TotalCounter").executeUpdate()
			def tc = new TotalCounter()
			tc.hits = 1
			session.save(tc)
		}
	}
}
