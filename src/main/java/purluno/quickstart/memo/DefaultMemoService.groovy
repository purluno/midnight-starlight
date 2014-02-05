package purluno.quickstart.memo

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.hibernate.SessionFactory
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 메모 서비스 구현 클래스.
 * <p>
 * 트랜잭션을 위해 Spring AOP를 사용해야하므로 인터페이스를 구현해야 했다.
 */
@Service
@Transactional
class DefaultMemoService implements MemoService {
	@Resource
	private SessionFactory sessionFactory
	
	@Override
	void add(MemoItem item) {
		item.writtenDate = new Date()
		def session = sessionFactory.currentSession
		session.save(item)
	}

	@Override
	public void deleteById(Integer id) {
		def session = sessionFactory.currentSession
		session.delete(new MemoItem(id: id))
	}

	@Override
	List<MemoItem> getAllItems() {
		def session = sessionFactory.currentSession
		def criteria = session.createCriteria(MemoItem)
		criteria.list()
	}
}
