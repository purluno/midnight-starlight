package purluno.quickstart.memo/** * 메모 서비스 인터페이스. * <p> * 트랜잭션을 위해 Spring AOP를 사용하기 위해서는 * 불가피하게 인터페이스를 선언해줘야만 한다. *  * @author 송영환 */
interface MemoService {	void add(MemoItem item)		void deleteById(Integer id)
	List<MemoItem> getAllItems()
}		
