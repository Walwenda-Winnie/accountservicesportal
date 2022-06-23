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

import com.accountservicesportal.Interface.TransactionTypesRepo;
import com.accountservicesportal.model.ResponseModel;
import com.accountservicesportal.model.TransactionTypes;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="Transactions")
@RestController
public class TransactionTypesController {
	
	@Autowired
	TransactionTypesRepo transactiontypesRepo;
	
	
	@RequestMapping(value="/v1/savetransactions",method=RequestMethod.POST)
 ResponseEntity<?> addTransactionTypes(@RequestBody TransactionTypes model) {
		try {
		transactiontypesRepo.save(new TransactionTypes(model.getName()) );
		return ResponseEntity.ok(new ResponseModel("00","Transactions has been saved succesfully"));
		}catch(Exception p) {
			p.printStackTrace();
			return ResponseEntity.ok(new ResponseModel("01","Transactions not saved successfully!!"));
		}
		
	}
	
	
	@RequestMapping(value="/v1/alltransactions",method=RequestMethod.GET)
	  public ResponseEntity<List<TransactionTypes>> getAllTransactionTypes(@RequestParam(required = false) String name) {
	    try {
	      List<TransactionTypes> model = new ArrayList<TransactionTypes>();
	      if (name == null)
	        transactiontypesRepo.findAll().forEach(model::add);
	      else
	        transactiontypesRepo.findByName(name).forEach(model::add);
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/transactions/{id}",method=RequestMethod.GET)
	  public ResponseEntity<TransactionTypes> getTransactionTypesById(@PathVariable("id") int id) {
	    TransactionTypes model = transactiontypesRepo.findById(id);
	    if (model != null) {
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/transaction/{id}",method=RequestMethod.PUT)
	  public ResponseEntity<String> updateTransactionTypes(@PathVariable("id") int id, @RequestBody TransactionTypes model) {
	   TransactionTypes _model = transactiontypesRepo.findById(id);
	    if (_model != null) {
	      
	      _model.setName(model.getName());
	       
	      transactiontypesRepo.update(model);
	      return new ResponseEntity<>("Transactions was updated successfully.", HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Cannot find Transactions with id=" + id, HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	  @RequestMapping(value="/v1/transactions/{id}",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteTransactionTypes(@PathVariable("id") int id) {
	    try {
	      int result = transactiontypesRepo.deleteById(id);
	      if (result == 0) {
	        return new ResponseEntity<>("Cannot find Transaction with id=" + id, HttpStatus.OK);
	      }
	      return new ResponseEntity<>("Transactions was deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete transaction.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @RequestMapping(value="/v1/transactions",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteAllTransactionTypes() {
	    try {
	      int numRows = transactiontypesRepo.deleteAll();
	      return new ResponseEntity<>("Deleted " + numRows + " TransactionType(s) successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete transactiontypes.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 

	  @RequestMapping(value="/v1/transactions/name",method=RequestMethod.GET)
	  public ResponseEntity<List<TransactionTypes>> findByName() {
	    try {
	      List<TransactionTypes> model = transactiontypesRepo.findByName("String");
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	


}
