package com.accountservicesportal.Interface;

import java.util.List;
import com.accountservicesportal.model.AlertsFrequency;

public interface AlertsFrequencyRepo {
	
	int save(AlertsFrequency model);
	int update(AlertsFrequency model);
	AlertsFrequency findById(int id);
	int deleteById(int id);
	List<AlertsFrequency> findAll();
	  List<AlertsFrequency> findByFrequency(String frequency);
	  int deleteAll();


}
