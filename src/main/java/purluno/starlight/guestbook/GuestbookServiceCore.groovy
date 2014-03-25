package purluno.starlight.guestbook

import javax.annotation.Resource

import org.apache.shiro.SecurityUtils
import org.hibernate.SessionFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import twitter4j.Twitter

@Service
@Transactional
class GuestbookServiceCore implements GuestbookService {
	@Resource
	SessionFactory sessionFactory

	@Override
	void add(GuestbookItem item) {
		def subject = SecurityUtils.subject
		subject.checkRole("signed-guest")
		def session = sessionFactory.currentSession
		session.save(item)
	}

	@Override
	List<GuestbookItem> getList(int first, int max) {
		def session = sessionFactory.currentSession
		session.createQuery("from GuestbookItem item order by item.id desc")
			.setFirstResult(first)
			.setMaxResults(max)
			.list()
	}
}
