package purluno.starlight.util

class PagedResult<T> {
	int pageSize

	int requestedPage

	int totalCount

	List<T> result

	PageIterator pageIterator

	def getActualPage() {
		Math.min(Math.max(1, requestedPage), pageCount)
	}

	int getFirst() {
		(actualPage - 1) * pageSize
	}

	int getPageCount() {
		Math.max(1, Math.ceil(totalCount / pageSize) as int)
	}

	void setPageIterator(int pageFrom, int pageTo) {
		def pi = new PageIterator()
		pi.page = actualPage
		pi.pageFrom = Math.min(Math.max(1, pageFrom), pageCount)
		pi.pageTo = Math.min(Math.max(1, pageTo), pageCount)
		this.pageIterator = pi
	}
}
