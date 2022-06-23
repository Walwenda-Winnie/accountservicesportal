package com.accountservicesportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accountservicesportal.Interface.TransactionTypesRepo;
import com.accountservicesportal.model.TransactionTypes;

@Repository
public class TransactionTypesRepoImpl implements TransactionTypesRepo{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Environment env;

	@Override
	public int save(TransactionTypes model) {

		String sql="INSERT INTO transactionType(name)values (?)";
		Object[]params=new Object[] {model.getName()};
	return jdbcTemplate.update(sql,params);
	}
	
	@Override
	  public int update(TransactionTypes model) {
	    return jdbcTemplate.update("UPDATE transactionType SET name=? WHERE id=?",
	        new Object[] { model.getName(),model.getId() });
	}
	
	
	 @Override
	  public TransactionTypes findById(int id) {
	    try {
	      TransactionTypes model = jdbcTemplate.queryForObject("SELECT * FROM transactionType WHERE id=?",
	          BeanPropertyRowMapper.newInstance(TransactionTypes.class), id);
	      return model;
	    } catch (IncorrectResultSizeDataAccessException e) {
	      return null;
	    }
	  }
	 
	 @Override
	  public int deleteById(int id) {
	    return jdbcTemplate.update("DELETE FROM transactionType WHERE id=?", id);
	  }
	 
	 @Override
	  public List<TransactionTypes> findAll() {
	    return jdbcTemplate.query("SELECT * from transactionType", BeanPropertyRowMapper.newInstance(TransactionTypes.class));
	  }

	 
	  @Override
	  public List<TransactionTypes> findByName(String name) {
	    return jdbcTemplate.query("SELECT * from transactionType WHERE name=?",
	        BeanPropertyRowMapper.newInstance(TransactionTypes.class), name);
	  }
	 
	  @Override
	  public int deleteAll() {
	    return jdbcTemplate.update("DELETE from transactionType");
	  }

}
