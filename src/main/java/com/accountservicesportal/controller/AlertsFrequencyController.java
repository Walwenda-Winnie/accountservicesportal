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

import com.accountservicesportal.Interface.AlertsFrequencyRepo;
import com.accountservicesportal.model.AlertsFrequency;
import com.accountservicesportal.model.ResponseModel;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="AlertsFrequency")
@RestController
public class AlertsFrequencyController {
	
	@Autowired
	AlertsFrequencyRepo frequencyint;
	
	
	@RequestMapping(value="/v1/savefrequency",method=RequestMethod.POST)
 ResponseEntity<?> addAlertsFrequency(@RequestBody AlertsFrequency model) {
		try {
		frequencyint.save(new AlertsFrequency(model.getFrequency(),model.getDescription()) );
		return ResponseEntity.ok(new ResponseModel("00","Frequency has been saved succesfully"));
		}catch(Exception p) {
			p.printStackTrace();
			return ResponseEntity.ok(new ResponseModel("01","Frequency not saved successfully!!"));
		}
		
	}
	
	
	@RequestMapping(value="/v1/allfrequencies",method=RequestMethod.GET)
	  public ResponseEntity<List<AlertsFrequency>> getAllAlertsFrequency(@RequestParam(required = false) String frequency) {
	    try {
	      List<AlertsFrequency> model = new ArrayList<AlertsFrequency>();
	      if (frequency == null)
	        frequencyint.findAll().forEach(model::add);
	      else
	        frequencyint.findByFrequency(frequency).forEach(model::add);
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/frequency/{id}",method=RequestMethod.GET)
	  public ResponseEntity<AlertsFrequency> getFrequencyById(@PathVariable("id") int id) {
	    AlertsFrequency model = frequencyint.findById(id);
	    if (model != null) {
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/frequency/{id}",method=RequestMethod.PUT)
	  public ResponseEntity<String> updateAlertsFrequency(@PathVariable("id") int id, @RequestBody AlertsFrequency model) {
	    AlertsFrequency _model = frequencyint.findById(id);
	    if (_model != null) {
	      
	      _model.setFrequency(model.getFrequency());
	      _model.setDescription(model.getDescription());
	      frequencyint.update(_model);
	      return new ResponseEntity<>("Frequency was updated successfully.", HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Cannot find Frequency with id=" + id, HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	  @RequestMapping(value="/v1/frequency/{id}",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteAlertsFrequency(@PathVariable("id") int id) {
	    try {
	      int result = frequencyint.deleteById(id);
	      if (result == 0) {
	        return new ResponseEntity<>("Cannot find Frequency with id=" + id, HttpStatus.OK);
	      }
	      return new ResponseEntity<>("Frequency was deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete frequency.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @RequestMapping(value="/v1/frequency",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteAllFrequency() {
	    try {
	      int numRows = frequencyint.deleteAll();
	      return new ResponseEntity<>("Deleted " + numRows + " Frequency(s) successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete frequency.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  
	  }


}
