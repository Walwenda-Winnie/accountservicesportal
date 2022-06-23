package com.accountservicesportal.model;

public class TransactionTypes {
	
	private int id;
	private String name;
	
	public TransactionTypes() {
	}
	
	 public TransactionTypes(String name) {
		    this.name = name;
		    
		    
		   
		  }
	public TransactionTypes(int id,String name) {
		this.id=id;
		this.name=name;
		;
		
		;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	 @Override
	  public String toString() {
	    return "TransactionTypes [ name=" + name + "]";
	  }

}
