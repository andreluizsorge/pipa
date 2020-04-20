package br.com.pipa.integration.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.pipa.core.domain.User;

public class UserStressTest {

	@Test
	@Disabled
	void testAddSocorePlayer_SUCCESS_10_THREAD_1M() {
		UserModel model = new UserModel();

		for(int j = 0; j <= 10; j++) {

			new Thread(() -> {  
				for (int i = 0; i <= 1000000; i++) {
					User user = new User();
					user.setUserId(i);
					user.setPoints(10);
					model.addSocorePlayer(user);
				}
			}).start();

		}
	}
	
	@Test
	@Disabled
	void testAddSocorePlayer_SUCCESS_1000_THREAD_1M() {
		UserModel model = new UserModel();

		for(int j = 0; j <= 1000; j++) {

			new Thread(() -> {  
				for (int i = 0; i <= 1000000; i++) {
					User user = new User();
					user.setUserId(i);
					user.setPoints(10);
					model.addSocorePlayer(user);
				}
			}).start();

		}
	}
}
