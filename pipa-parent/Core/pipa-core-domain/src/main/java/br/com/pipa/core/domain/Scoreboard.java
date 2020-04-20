package br.com.pipa.core.domain;

import java.util.List;

/***
 * @author Sorge
 * @since 18/04/2020
 */
public class Scoreboard {

	private List<User> highscores;

	public List<User> getHighscores() {
		return highscores;
	}

	public void setHighscores(List<User> highscores) {
		this.highscores = highscores;
	}	
	
}
