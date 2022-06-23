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

import com.accountservicesportal.Interface.ChequeBookPagesRepo;
import com.accountservicesportal.model.ChequeBookPages;
import com.accountservicesportal.model.ResponseModel;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="ChequeBookPages")
@RestController
public class ChequeBookPagesController {
	
	@Autowired
	ChequeBookPagesRepo chequebookpagesRepo;
	
	
	@RequestMapping(value="/v1/savechequebooks",method=RequestMethod.POST)
 ResponseEntity<?> addChequeBookPages(@RequestBody ChequeBookPages model) {
		try {
		chequebookpagesRepo.save(new ChequeBookPages(model.getAccountType(),model.getCurrency(),model.getPages()) );
		return ResponseEntity.ok(new ResponseModel("00","ChequeBooks has been saved succesfully"));
		}catch(Exception p) {
			p.printStackTrace();
			return ResponseEntity.ok(new ResponseModel("01","ChequeBooks not saved successfully!!"));
		}
		
	}
	
	
	@RequestMapping(value="/v1/allchequebookpages",method=RequestMethod.GET)
	  public ResponseEntity<List<ChequeBookPages>> getAllChequeBookPages(@RequestParam(required = false) String accountType) {
	    try {
	      List<ChequeBookPages> model = new ArrayList<ChequeBookPages>();
	      if (accountType == null)
	        chequebookpagesRepo.findAll().forEach(model::add);
	      else
	        chequebookpagesRepo.findByAccountType(accountType).forEach(model::add);
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/chequebookpages/{id}",method=RequestMethod.GET)
	  public ResponseEntity<ChequeBookPages> getChequeBookPagesById(@PathVariable("id") int id) {
	    ChequeBookPages model = chequebookpagesRepo.findById(id);
	    if (model != null) {
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/chequebookpage/{id}",method=RequestMethod.PUT)
	  public ResponseEntity<String> updateChequeBookPages(@PathVariable("id") int id, @RequestBody ChequeBookPages model) {
	    ChequeBookPages _model = chequebookpagesRepo.findById(id);
	    if (_model != null) {
	      
	      _model.setAccountType(model.getAccountType());
	      _model.setCurrency(model.getCurrency());
	      _model.setPages(model.getPages());
	      chequebookpagesRepo.update(_model);
	      return new ResponseEntity<>("ChequeBook was updated successfully.", HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Cannot find ChequeBookPages with id=" + id, HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	  @RequestMapping(value="/v1/chequebooks/{id}",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteChequeBookPages(@PathVariable("id") int id) {
	    try {
	      int result = chequebookpagesRepo.deleteById(id);
	      if (result == 0) {
	        return new ResponseEntity<>("Cannot find ChequeBookPagess with id=" + id, HttpStatus.OK);
	      }
	      return new ResponseEntity<>("Chequebookpages was deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete chequebookpage.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @RequestMapping(value="/v1/chequebookpages",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteAllChequeBookPages() {
	    try {
	      int numRows = chequebookpagesRepo.deleteAll();
	      return new ResponseEntity<>("Deleted " + numRows + " ChequeBookPage(s) successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete chequebookpages.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 

	  @RequestMapping(value="/v1/chequebookpages/accountTypes",method=RequestMethod.GET)
	  public ResponseEntity<List<ChequeBookPages>> findByAccountType() {
	    try {
	      List<ChequeBookPages> model = chequebookpagesRepo.findByAccountType("String");
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


}
