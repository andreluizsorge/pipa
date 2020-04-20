package br.com.pipa.core.domain.dao.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/***
 * @author Sorge
 * @since 18/04/2020
 */
public class DataBase {

	private static DataBase instance;

	private Map<Integer, Integer> data;

	public Map<Integer, Integer> getData() {
		return data;
	}

	private DataBase() {
		data = new HashMap<Integer, Integer>();
	}

	public static DataBase getInstance() {
		if (Objects.isNull(instance)) {
			instance = new DataBase();
		}
		return instance;
	}
}
