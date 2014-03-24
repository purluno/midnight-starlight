package purluno.starlight.twitter

import javax.annotation.Resource

import org.springframework.stereotype.Service

import twitter4j.Status
import twitter4j.Twitter

@Service
class TwitterService {
	@Resource
	private Twitter twitter

	List<Status> getUserTimeline() {
		twitter.userTimeline
	}
}
