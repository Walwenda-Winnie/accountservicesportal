package com.accountservicesportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accountservicesportal.Interface.ChequeBookPagesRepo;
import com.accountservicesportal.model.ChequeBookPages;

@Repository
public class ChequeBookPagesRepoImpl implements ChequeBookPagesRepo{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Environment env;

	@Override
	public int save(ChequeBookPages model) {

		String sql="INSERT INTO chequebookpages(accountType,currency,pages)values (?,?,?)";
		Object[]params=new Object[] {model.getAccountType(),model.getCurrency(),model.getPages()};
	return jdbcTemplate.update(sql,params);
	}
	
	@Override
	  public int update(ChequeBookPages model) {
	    return jdbcTemplate.update("UPDATE chequebookpages SET accountType=?, currency=?, pages=? WHERE id=?",
	        new Object[] {model.getAccountType(),model.getCurrency(),model.getPages(),model.getId() });
	}
	
	
	 @Override
	  public ChequeBookPages findById(int id) {
	    try {
	      ChequeBookPages model = jdbcTemplate.queryForObject("SELECT * FROM chequebookpages WHERE id=?",
	          BeanPropertyRowMapper.newInstance(ChequeBookPages.class), id);
	      return model;
	    } catch (IncorrectResultSizeDataAccessException e) {
	      return null;
	    }
	  }
	 
	 @Override
	  public int deleteById(int id) {
	    return jdbcTemplate.update("DELETE FROM chequebookpages WHERE id=?", id);
	  }
	 
	 @Override
	  public List<ChequeBookPages> findAll() {
	    return jdbcTemplate.query("SELECT * from chequebookpages", BeanPropertyRowMapper.newInstance(ChequeBookPages.class));
	  }

	 
	  @Override
	  public List<ChequeBookPages> findByAccountType(String accountType) {
	    return jdbcTemplate.query("SELECT * from chequebookpages WHERE accountType=?",
	        BeanPropertyRowMapper.newInstance(ChequeBookPages.class), accountType);
	  }
	  
	  @Override
	  public List<ChequeBookPages> findByCurrency(String currency) {
	    String q = "SELECT * from chequebookpages WHERE currency ILIKE '%" + currency + "%'";
	    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(ChequeBookPages.class));
	  }
	 

	  @Override
	  public int deleteAll() {
	    return jdbcTemplate.update("DELETE from chequebookpages");
	  }



}
