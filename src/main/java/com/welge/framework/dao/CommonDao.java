package com.welge.framework.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> findBySql(String sql , Object... args){
		return jdbcTemplate.queryForList(sql, args);
	}
	
}
