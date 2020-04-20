package br.com.pipa.integration.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import br.com.pipa.core.domain.Scoreboard;
import br.com.pipa.core.domain.User;

class PipaIntegrationRestControllerTest {

	@Test
	@Disabled
	void testService_SUCCESS() {
		User user = new User();
		user.setUserId(45);
		user.setPoints(74);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity("http://localhost:8080/score", user, String.class);

		Map<String, Integer> values = new HashMap<String, Integer>();
		values.put("userId", user.getUserId());
		User requestUser = restTemplate.getForEntity("http://localhost:8080/{userId}/position", User.class, values).getBody();
		assertEquals(user, requestUser);

		Scoreboard scoreboard = restTemplate.getForEntity("http://localhost:8080/highscorelist", Scoreboard.class).getBody();
		assertNotNull(scoreboard);

		for (User userFirst : scoreboard.getHighscores()) {
			assertEquals(1, userFirst.getPosition());
		}

	}


	@Test
	@Disabled
	void testService_SUCCESS_MULT_THREAD() throws InterruptedException {
		

		for(int j = 0; j <= 3; j++) {
			new Thread(() -> {  
				RestTemplate restTemplate = new RestTemplate();
				for (int i = 0; i <= 1000000; i++) {
					User user = new User();
					user.setUserId(45);
					user.setPoints(74);
					restTemplate.postForEntity("http://localhost:8080/score", user, String.class);
				}
			}).start();
		}

		for(int j = 0; j <= 3; j++) {
			new Thread(() -> {  
				RestTemplate restTemplate = new RestTemplate();
				for (int i = 0; i <= 1000000; i++) {
					Map<String, Integer> values = new HashMap<String, Integer>();
					values.put("userId", new Random().nextInt(1000000));
					User requestUser = restTemplate.getForEntity("http://localhost:8080/{userId}/position", User.class, values).getBody();
					assertNotNull(requestUser);
				}
			}).start();
		}

		
		for(int j = 0; j <= 3; j++) {
			new Thread(() -> {  
				RestTemplate restTemplate = new RestTemplate();
				for (int i = 0; i <= 1000000; i++) {
					Scoreboard scoreboard = restTemplate.getForEntity("http://localhost:8080/highscorelist", Scoreboard.class).getBody();
					assertNotNull(scoreboard);
				}
			}).start();
		}
	}

}
