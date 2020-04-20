package br.com.pipa.integration.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.pipa.core.domain.Scoreboard;
import br.com.pipa.core.domain.User;
import br.com.pipa.core.domain.dao.config.DAO;

class UserModelTest {


	@Test
	void testAddSocorePlayer_NULL() {
		Integer sizeBefore = DAO.data.size();
		new UserModel().addSocorePlayer(null);
		assertEquals(sizeBefore, DAO.data.size());
	}

	@Test
	void testAddSocorePlayer_EMPTY() {
		Integer sizeBefore = DAO.data.size();
		new UserModel().addSocorePlayer(new User());
		assertEquals(sizeBefore, DAO.data.size());
	}

	@Test
	void testAddSocorePlayer_SUCCESS() {
		Integer sizeBefore = DAO.data.size();

		User user = new User();
		user.setUserId(1);
		user.setPoints(10);

		new UserModel().addSocorePlayer(user);
		assertEquals(sizeBefore+1, DAO.data.size());
		assertEquals(user.getPoints(), DAO.data.get(user.getUserId()));
	}
	
	@Test
	void testAddSocorePlayer_SUCCESS_SAME_USER() {
		Integer sizeBefore = DAO.data.size();

		User user = new User();
		user.setUserId(25);
		user.setPoints(10);

		new UserModel().addSocorePlayer(user);
		
		user = new User();
		user.setUserId(25);
		user.setPoints(10);
		
		new UserModel().addSocorePlayer(user);

		assertEquals(sizeBefore+1, DAO.data.size());
		assertEquals(20, DAO.data.get(user.getUserId()));
	}
	

	@Test
	@Disabled
	void testAddSocorePlayer_SUCCESS_1M() {
		Integer sizeBefore = DAO.data.size();
		UserModel model = new UserModel();

		for (int i = 0; i <= 1000000; i++) {
			User user = new User();
			user.setUserId(i);
			user.setPoints(10);
			model.addSocorePlayer(user);
		}
		assertEquals(sizeBefore+1000000, DAO.data.size());
	}
	
	
	@Test
	void testFindById_NULL() {
		User user = new UserModel().findById(null);
		assertNotNull(user);
		assertNull(user.getUserId());
		assertEquals(0, user.getPoints());
		assertEquals(0, user.getPosition());
	}
	
	@Test
	void testFindById_NOT_FOUND() {
		User user = new UserModel().findById(54);
		assertNotNull(user);
		assertEquals(54, user.getUserId());
		assertEquals(0, user.getPoints());
		assertEquals(0, user.getPosition());
	}
	
	@Test
	void testFindById_FOUND() {

		User user = new User();
		user.setUserId(99);
		user.setPoints(99999);

		new UserModel().addSocorePlayer(user);
		
		user = new UserModel().findById(99);
		assertNotNull(user);
		assertEquals(99, user.getUserId());
		assertEquals(99999, user.getPoints());
		assertEquals(1, user.getPosition());
	}
	
	
	@Test
	void testScoretable_SUCESS() {
		Scoreboard scoreboard = new UserModel().scoretable();
		assertNotNull(scoreboard);
	}
	
}
