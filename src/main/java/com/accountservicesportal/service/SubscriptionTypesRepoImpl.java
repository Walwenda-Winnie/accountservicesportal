package com.accountservicesportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accountservicesportal.Interface.SubscriptionTypesRepo;
import com.accountservicesportal.model.SubscriptionTypes;

@Repository
public class SubscriptionTypesRepoImpl implements SubscriptionTypesRepo{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Environment env;

	@Override
	public int save(SubscriptionTypes model) {

		String sql="INSERT INTO subscriptionType(type)values (?)";
		Object[]params=new Object[] {model.getType()};
	return jdbcTemplate.update(sql,params);
	}
	
	@Override
	  public int update(SubscriptionTypes model) {
	    return jdbcTemplate.update("UPDATE subscriptionType SET type=?, modifiedBy=? WHERE id=?",
	        new Object[] { model.getType(), model.getModifiedBy(),model.getId() });
	}
	
	
	 @Override
	  public SubscriptionTypes findById(int id) {
	    try {
	      SubscriptionTypes model = jdbcTemplate.queryForObject("SELECT * FROM subscriptionType WHERE id=?",
	          BeanPropertyRowMapper.newInstance(SubscriptionTypes.class), id);
	      return model;
	    } catch (IncorrectResultSizeDataAccessException e) {
	      return null;
	    }
	  }
	 
	 @Override
	  public int deleteById(int id) {
	    return jdbcTemplate.update("DELETE FROM subscriptionType WHERE id=?", id);
	  }
	 
	 @Override
	  public List<SubscriptionTypes> findAll() {
	    return jdbcTemplate.query("SELECT * from subscriptionType", BeanPropertyRowMapper.newInstance(SubscriptionTypes.class));
	  }

	 
	  @Override
	  public List<SubscriptionTypes> findByType(String type) {
	    return jdbcTemplate.query("SELECT * from subscriptionType WHERE type=?",
	        BeanPropertyRowMapper.newInstance(SubscriptionTypes.class), type);
	  }
	  
	  @Override
	  public List<SubscriptionTypes> findByModifiedBy(String modifiedBy) {
	    String q = "SELECT * from subscriptionType WHERE modifiedBy ILIKE '%" + modifiedBy + "%'";
	    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(SubscriptionTypes.class));
	  }
	  
	 
	 
	

	  @Override
	  public int deleteAll() {
	    return jdbcTemplate.update("DELETE from subscriptionType");
	  }

	

}
