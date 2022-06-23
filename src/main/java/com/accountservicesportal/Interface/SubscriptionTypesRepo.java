package com.accountservicesportal.Interface;

import java.util.List;

import com.accountservicesportal.model.SubscriptionTypes;

public interface SubscriptionTypesRepo {
	int save(SubscriptionTypes model);
	int update(SubscriptionTypes model);
	SubscriptionTypes findById(int id);
	int deleteById(int id);
	List<SubscriptionTypes> findAll();
	  List<SubscriptionTypes> findByType(String type);
	  List<SubscriptionTypes> findByModifiedBy(String modifiedBy);
	  int deleteAll();

}
