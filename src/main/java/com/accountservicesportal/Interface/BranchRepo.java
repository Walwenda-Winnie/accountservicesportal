package com.accountservicesportal.Interface;

import java.util.List;

import com.accountservicesportal.model.Branch;

public interface BranchRepo {
	public int save(Branch model);
	int update(Branch model);
	Branch findById(int id);
	int deleteById(int id);
	List<Branch> findAll();
	  List<Branch> findByBranchCode(String branchCode);
	  List<Branch> findByBranchName(String branchName);
	  int deleteAll();

}
