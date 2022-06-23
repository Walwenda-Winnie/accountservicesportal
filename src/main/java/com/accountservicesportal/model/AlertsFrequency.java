package com.accountservicesportal.model;

public class AlertsFrequency {
	
	private int id;
	private String frequency;
	private String description;
	
	

	public AlertsFrequency() {
	}
	
	 public AlertsFrequency(String frequency, String description) {
		 this.frequency=frequency;
		    this.description = description;
		    
		   
		  }
	public AlertsFrequency(int id,String frequency, String description) {
		this.id=id;
		this.frequency = frequency;
	    this.description = description;
	   
	
		;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	


}
