package br.com.pipa.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pipa.core.domain.Scoreboard;
import br.com.pipa.core.domain.User;
import br.com.pipa.integration.model.UserModel;

/**
 * Classe criada para conter todos os serviços REST que fazem referencia
 * ao Usuario (User). Essa classe não pode ter nenhuma
 * regra de negocio, isso fica a cargo da classe UserModel.
 * 
 * @author AndreSorge
 * @since 20/04/2020
 */
@RestController
public class PipaIntegrationRestController {

	private Logger log = LoggerFactory.getLogger(PipaIntegrationRestController.class);
	
	@Autowired
	private UserModel userModel;
	
	@PostMapping(path = "/score")
	public void addScore(@RequestBody User player) {
		log.info("addScore: " + player);
		userModel.addSocorePlayer(player);
	}		

	@GetMapping(path = "/{userId}/position")
	public User findUserPosition(@PathVariable Integer userId) {
		log.info("findUserPosition: " + userId);
		return userModel.findById(userId);
	}

	@GetMapping(path = "/highscorelist")
	public Scoreboard findHighScorelist() {
		log.info("findHighScorelist started");
		return userModel.scoretable();
	}
}
