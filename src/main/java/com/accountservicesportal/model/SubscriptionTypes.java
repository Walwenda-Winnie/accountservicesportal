package com.accountservicesportal.model;

public class SubscriptionTypes {
	

	private int id;
	private String type;
	private String modifiedBy;
	
	
	public SubscriptionTypes() {
	}
	
	 public SubscriptionTypes(String type) {
		    this.type = type;
		    
		   
		  }
	public SubscriptionTypes(int id,String type,String modifiedBy) {
		this.id=id;
		this.type=type;
		this.modifiedBy=modifiedBy;
		;
	}
	
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	 @Override
	  public String toString() {
	    return "SubscriptionTypes [ type=" + type + ",modifiedBy="+modifiedBy+"]";
	  }

}
