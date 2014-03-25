package purluno.starlight.guestbook

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator

class GuestbookItemValidator implements Validator {
	@Override
	boolean supports(Class<?> clazz) {
		return clazz == GuestbookItem
	}

	@Override
	void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "authorId", "authorId.empty")
		ValidationUtils.rejectIfEmpty(errors, "writtenDate", "writtenDate.empty")
		ValidationUtils.rejectIfEmpty(errors, "text", "text.empty")
	}
}
