package purluno.starlight.util

class PageIterator implements Iterator<Page> {
	int page

	int pageFrom

	int pageTo

	int cursor = pageFrom - 1

	@Override
	boolean hasNext() {
		return cursor < pageTo
	}

	@Override
	Page next() {
		cursor += 1
		new Page(number: cursor, current: cursor == page)
	}

	@Override
	void remove() {
		throw new UnsupportedOperationException()
	}

	void setPageFrom(int pageFrom) {
		this.@pageFrom = pageFrom
		this.cursor = pageFrom - 1
	}
}
