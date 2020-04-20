package br.com.pipa.core.domain.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.pipa.core.domain.User;
import br.com.pipa.core.domain.dao.config.DAO;

public class UserDAO implements DAO<User, Integer>{

	private final Logger log = LoggerFactory.getLogger(UserDAO.class); 
	
	@Override
	public synchronized void insertOrUpdate(User user) {
		if (data.containsKey(user.getUserId())) {
			log.info("User for update: " + user.getUserId());
			data.merge(user.getUserId(), user.getPoints(), Integer::sum);
		}
		else {
			log.info("New User: " + user.getUserId());
			data.put(user.getUserId(), user.getPoints());
			log.info("User added successfully");
		}
	}

	@Override
	public User findById(Integer userId) {
		User user = new User();
		user.setUserId(userId);

		if (data.containsKey(userId)) {
			
			user.setPoints(data.get(userId).intValue());
			user.setPosition(findAll().indexOf(user)+1);
			log.info("User found: " + user);
			
			return user;		
		}
		else {
			log.info("User not found: " + userId);
		}

		return user;
	}

	@Override
	public List<User> findAll() {
		List<User> currentScores = new ArrayList<User>();

		Set<Map.Entry<Integer, Integer>> entries = data.entrySet();
		log.info("Total found: " + entries.size());
		
		entries.forEach( entry -> {
			
			User user = new User();
			user.setUserId(entry.getKey());
			user.setPoints(entry.getValue());
			
			currentScores.add(user);
		});
		
		Collections.sort(currentScores);

		return currentScores;
	}

}
