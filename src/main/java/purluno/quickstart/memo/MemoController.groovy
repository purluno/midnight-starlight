package purluno.quickstart.memo

import javax.annotation.Resource

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("memo")
class MemoController {
	@Resource
	private MemoService memoService
	
	@RequestMapping("list")
	String list(Model model) {
		model.addAllAttributes([
			items: memoService.allItems
		])
		"memo/list"
	}
}
