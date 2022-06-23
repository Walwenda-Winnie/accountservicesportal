package com.accountservicesportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accountservicesportal.Interface.SubscriptionTypesRepo;
import com.accountservicesportal.model.ResponseModel;
import com.accountservicesportal.model.SubscriptionTypes;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="Subscriptions")
@RestController
public class SubscriptionTypesController {
	
	@Autowired
	SubscriptionTypesRepo subscriptiontypesRepo;
	
	
	@RequestMapping(value="/v1/savesubscriptions",method=RequestMethod.POST)
 ResponseEntity<?> addSubscriptionTypes(@RequestBody SubscriptionTypes model) {
		try {
		subscriptiontypesRepo.save(new SubscriptionTypes(model.getType()) );
		return ResponseEntity.ok(new ResponseModel("00","Subscriptions has been saved succesfully"));
		}catch(Exception p) {
			p.printStackTrace();
			return ResponseEntity.ok(new ResponseModel("01","Subscriptions not saved successfully!!"));
		}
		
	}
	
	
	@RequestMapping(value="/v1/allsubscriptions",method=RequestMethod.GET)
	  public ResponseEntity<List<SubscriptionTypes>> getAllSubscriptionTypes(@RequestParam(required = false) String type) {
	    try {
	      List<SubscriptionTypes> model = new ArrayList<SubscriptionTypes>();
	      if (type == null)
	        subscriptiontypesRepo.findAll().forEach(model::add);
	      else
	        subscriptiontypesRepo.findByType(type).forEach(model::add);
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/subscriptions/{id}",method=RequestMethod.GET)
	  public ResponseEntity<SubscriptionTypes> getSubscriptionTypesById(@PathVariable("id") int id) {
	    SubscriptionTypes model = subscriptiontypesRepo.findById(id);
	    if (model != null) {
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/subscription/{id}",method=RequestMethod.PUT)
	  public ResponseEntity<String> updateSubscriptionTypes(@PathVariable("id") int id, @RequestBody SubscriptionTypes model) {
	    SubscriptionTypes _model = subscriptiontypesRepo.findById(id);
	    if (_model != null) {
	      
	      _model.setType(model.getType());
	      _model.setModifiedBy(model.getModifiedBy());
	      
	      subscriptiontypesRepo.update(_model);
	      return new ResponseEntity<>("Subscriptions was updated successfully.", HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Cannot find Subscriptions with id=" + id, HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	  @RequestMapping(value="/v1/subscriptions/{id}",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteSubscriptionTypes(@PathVariable("id") int id) {
	    try {
	      int result = subscriptiontypesRepo.deleteById(id);
	      if (result == 0) {
	        return new ResponseEntity<>("Cannot find Subscriptions with id=" + id, HttpStatus.OK);
	      }
	      return new ResponseEntity<>("Subscriptions was deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete subscription.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @RequestMapping(value="/v1/subscriptions",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteAllSubscriptionTypes() {
	    try {
	      int numRows = subscriptiontypesRepo.deleteAll();
	      return new ResponseEntity<>("Deleted " + numRows + " SubscriptionType(s) successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete subscriptiontypes.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 

	  @RequestMapping(value="/v1/subscriptions/type",method=RequestMethod.GET)
	  public ResponseEntity<List<SubscriptionTypes>> findByType() {
	    try {
	      List<SubscriptionTypes> model = subscriptiontypesRepo.findByType("String");
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	  }
	  }
