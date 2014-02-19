package purluno.starlight.memo

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
class MemoItem {
	@Id @GeneratedValue
	Integer id
	
	String author
	
	Date writtenDate
	
	String body
}
