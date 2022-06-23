package com.accountservicesportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accountservicesportal.Interface.AlertsFrequencyRepo;
import com.accountservicesportal.model.AlertsFrequency;

@Repository
public class AlertsFrequencyRepoImpl implements AlertsFrequencyRepo{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Environment env;

	@Override
	public int save(AlertsFrequency model) {

		String sql="INSERT INTO frequencyofalerts(frequency,description)values (?,?)";
		Object[]params=new Object[] {model.getFrequency(),model.getDescription()};
	return jdbcTemplate.update(sql,params);
	}
	
	@Override
	  public int update(AlertsFrequency model) {
	    return jdbcTemplate.update("UPDATE frequencyofalerts SET frequency=?, description=? WHERE id=?",
	        new Object[] { model.getFrequency(),model.getDescription(),model.getId() });
	}
	
	
	 @Override
	  public AlertsFrequency findById(int id) {
	    try {
	      AlertsFrequency model = jdbcTemplate.queryForObject("SELECT * FROM frequencyofalerts WHERE id=?",
	          BeanPropertyRowMapper.newInstance(AlertsFrequency.class), id);
	      return model;
	    } catch (IncorrectResultSizeDataAccessException e) {
	      return null;
	    }
	  }
	 
	 @Override
	  public int deleteById(int id) {
	    return jdbcTemplate.update("DELETE FROM frequencyofalerts WHERE id=?", id);
	  }
	 
	 @Override
	  public List<AlertsFrequency> findAll() {
	    return jdbcTemplate.query("SELECT * from frequencyofalerts", BeanPropertyRowMapper.newInstance(AlertsFrequency.class));
	  }

	 
	  @Override
	  public List<AlertsFrequency> findByFrequency(String frequency) {
	    return jdbcTemplate.query("SELECT * from frequencyofalerts WHERE frequency=?",
	        BeanPropertyRowMapper.newInstance(AlertsFrequency.class), frequency);
	  }
	  
	 
	  
	 
	 
	

	  @Override
	  public int deleteAll() {
	    return jdbcTemplate.update("DELETE from frequencyofalerts");
	  }

	

}
