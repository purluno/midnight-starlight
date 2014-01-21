package purluno.quickstart.memo

import javax.annotation.Resource;

import org.hibernate.SessionFactory
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class DefaultMemoService implements MemoService {
	@Resource
	private SessionFactory sessionFactory
	
	List<MemoItem> getAllItems() {
		def session = sessionFactory.currentSession
		def criteria = session.createCriteria(MemoItem)
		criteria.list()
	}
}
