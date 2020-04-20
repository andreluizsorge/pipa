package br.com.pipa.core.domain.dao.config;

import java.util.List;
import java.util.Map;

public interface DAO<T, K> {

	Map<Integer, Integer> data = DataBase.getInstance().getData();
	
	void insertOrUpdate(T t);
	T findById(K k);
	List<T> findAll();
}
