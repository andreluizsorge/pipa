package br.com.pipa.integration.model;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.pipa.core.domain.Scoreboard;
import br.com.pipa.core.domain.User;
import br.com.pipa.core.domain.dao.impl.UserDAO;

/***
 * Classe criada para ser o modelo de negocio
 * nesta classe pode ser encontrado e implementado
 * todas as regras de negocio referente ao Usuario (User)
 * 
 * @author Sorge
 * @since 20/04/2020
 */
@Component
public class UserModel {
	
	private final Logger log = LoggerFactory.getLogger(UserModel.class);
	
	@Value("${scoretable.limit}")
	private int scoreTableLimit;
	
	private UserDAO userDAO = new UserDAO(); 
	
	public void addSocorePlayer(User user) {
		if (Objects.nonNull(user) && Objects.nonNull(user.getUserId())) {
			userDAO.insertOrUpdate(user);
		}
		else {
			log.error("Object or UserId is null.");
		}
	}

	public User findById(Integer userId) {
		if (Objects.nonNull(userId)) {
			return userDAO.findById(userId);
		}
		log.error("User.id is null.");
		return new User();
	}

	public Scoreboard scoretable() {
		final List<User> users = userDAO.findAll();
		
		users.forEach(player -> {
			player.setPosition(users.indexOf(player)+1);
		});
		
		List<User> usersLimited = limitList(users, scoreTableLimit);
		
		Scoreboard scoreboard = new Scoreboard();
		scoreboard.setHighscores(usersLimited);
		
		return scoreboard;
	}

	private List<User> limitList(final List<User> users, int limit) {
		List<User> newUsers = users;
		
		if (newUsers.size() > limit) {
			newUsers = newUsers.subList(0, limit);
		}
		return newUsers;
	}
}
