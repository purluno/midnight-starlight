package purluno.starlight.twitter

import javax.annotation.Resource

import org.springframework.stereotype.Service

import com.fasterxml.jackson.databind.ObjectMapper

@Service
class TwitterService {
	@Resource
	private ObjectMapper objectMapper

	String test() {
		"hello"
	}
}
