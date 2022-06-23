package com.accountservicesportal.model;

public class Branch {
	int id;
	String branchName;
	String branchCode;
	String modifiedBy;
	
	public Branch() {
		
	}
	
	
	public Branch(String branchName, String branchCode) {
	
		this.branchName=branchName;
		this.branchCode=branchCode;
	}
	public Branch(int id,String branchName, String branchCode, String modifiedBy) {
		this.id=id;
		this.branchName=branchName;
		this.branchCode=branchCode;
		this.modifiedBy=modifiedBy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	

}
