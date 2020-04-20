package br.com.pipa.core.domain;

/***
 * @author Sorge
 * @since 18/04/2020
 */
public class User implements Comparable<User>{

	private Integer userId;
	private Integer points = 0;
	private Integer position = 0;
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public int compareTo(User player) {
		int result = player.getPoints().compareTo(this.getPoints());
		
		if (result == 0) {
			return this.getUserId().compareTo(player.getUserId());
		}
		
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		User other = (User) obj;
		return this.getUserId().equals(other.getUserId());
	}

	@Override
	public String toString() {
		return "{\"userId\":"+userId+", \"points\":"+points+", \"position\":"+position+"}";
	}
	
}
