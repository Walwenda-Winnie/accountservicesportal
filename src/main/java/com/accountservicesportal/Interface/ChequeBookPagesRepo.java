package com.accountservicesportal.Interface;

import java.util.List;

import com.accountservicesportal.model.ChequeBookPages;

public interface ChequeBookPagesRepo {
	
	int save(ChequeBookPages model);
	int update(ChequeBookPages model);
	ChequeBookPages findById(int id);
	int deleteById(int id);
	List<ChequeBookPages> findAll();
	  List<ChequeBookPages> findByAccountType(String accountType);
	  List<ChequeBookPages> findByCurrency(String currency);
	  int deleteAll();

}
