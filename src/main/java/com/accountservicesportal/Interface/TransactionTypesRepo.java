package com.accountservicesportal.Interface;

import java.util.List;

import com.accountservicesportal.model.TransactionTypes;

public interface TransactionTypesRepo {
	int save(TransactionTypes model);
	int update(TransactionTypes model);
	TransactionTypes findById(int id);
	int deleteById(int id);
	List<TransactionTypes> findAll();
	  List<TransactionTypes> findByName(String name);
	  int deleteAll();

}
