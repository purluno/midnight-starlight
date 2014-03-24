package purluno.starlight

import static org.junit.Assert.*

import javax.annotation.Resource

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import twitter4j.Twitter

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = AppConfig)
@WebAppConfiguration
class TwitterTest {
	@Resource
	private Twitter twitter

	@Before
	void setUp() throws Exception {
	}

	@After
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		def respList = twitter.userTimeline
		for (status in respList) {
			println "$status.user.name // $status.text"
		}
	}
}
