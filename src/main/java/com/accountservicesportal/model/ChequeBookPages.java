package com.accountservicesportal.model;

public class ChequeBookPages {
	
	private int id;
	private String accountType;
	private  String currency;
	private int pages;
	
	
	public ChequeBookPages() {
	}
	
	 public ChequeBookPages(String accountType, String currency, int pages) {
		    this.accountType = accountType;
		    this.currency = currency;
		    this.pages=pages;
		   
		  }
	public ChequeBookPages(int id,String accountType,String currency,int pages) {
		this.id=id;
		this.accountType = accountType;
	    this.currency = currency;
	    this.pages=pages;
		;
	}
	
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	 @Override
	  public String toString() {
	    return "ChequeBookPages [ accountType=" + accountType + ", currency=" + currency + ", pages" + pages + "]";
	  }
	

}
