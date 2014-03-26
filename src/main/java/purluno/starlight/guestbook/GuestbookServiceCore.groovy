package purluno.starlight.guestbook

import javax.annotation.Resource

import org.apache.shiro.SecurityUtils;
import org.hibernate.SessionFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GuestbookServiceCore implements GuestbookService {
	@Resource
	SessionFactory sessionFactory

	@Override
	void add(GuestbookItem item) {
		def session = sessionFactory.currentSession
		session.save(item)
	}

	@Override
	List<GuestbookItem> getList(int first, int max) {
		def session = sessionFactory.currentSession
		if (SecurityUtils.subject.isPermitted("guestbook:readHidden")) {
			def hql = """\
from GuestbookItem as item
order by item.id desc
"""
			session.createQuery(hql)
				.setFirstResult(first)
				.setMaxResults(max)
				.list()
		} else {
			def principal = SecurityUtils.subject.principal as String
			def hql = """\
from GuestbookItem as item
where item.hiddenFromOther = false
	or item.authorId = :principal
order by item.id desc
"""
			session.createQuery(hql)
				.setString("principal", principal)
				.setFirstResult(first)
				.setMaxResults(max)
				.list()
		}
	}
}
