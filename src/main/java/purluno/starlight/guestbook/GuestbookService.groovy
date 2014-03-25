package purluno.starlight.guestbook

import java.util.List

interface GuestbookService {
	void add(GuestbookItem item)

	List<GuestbookItem> getList(int first, int max)
}
