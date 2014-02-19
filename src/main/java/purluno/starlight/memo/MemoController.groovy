package purluno.starlight.memo

import javax.annotation.Resource

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("memo")
class MemoController {
	@Resource
	private MemoService memoService
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	String add(Model model, @ModelAttribute MemoItem item) {
		memoService.add(item)
		"redirect:/memo/list"
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	String delete(Model model, @RequestParam Integer id) {
		memoService.deleteById(id)
		"redirect:/memo/list"
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	String list(Model model) {
		model.addAllAttributes([
			items: memoService.allItems
		])
		"memo/list"
	}
}
