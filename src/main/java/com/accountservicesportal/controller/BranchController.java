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

import com.accountservicesportal.Interface.BranchRepo;
import com.accountservicesportal.model.Branch;
import com.accountservicesportal.model.ResponseModel;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="Branches")
@RestController
public class BranchController {
	@Autowired
	BranchRepo branchRepo;
	@RequestMapping(value="/v1/savebranch",method=RequestMethod.POST)
ResponseEntity<?>trysave(@RequestBody Branch model){
	try {
	branchRepo.save(new Branch(model.getBranchName(),model.getBranchCode()));
	return ResponseEntity.ok(new ResponseModel("00","Record has been succesfully updated"));
	}catch(Exception l) {
		l.printStackTrace();
		return ResponseEntity.ok(new ResponseModel("01","Could not save record"));
	}
}
	
	@RequestMapping(value="/v1/allbranches",method=RequestMethod.GET)
	  public ResponseEntity<List<Branch>> getAllBranch(@RequestParam(required = false) String branchName) {
	    try {
	      List<Branch> model = new ArrayList<Branch>();
	      if (branchName == null)
	        branchRepo.findAll().forEach(model::add);
	      else
	        branchRepo.findByBranchName(branchName).forEach(model::add);
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/branches/{id}",method=RequestMethod.GET)
	  public ResponseEntity<Branch> getBranchById(@PathVariable("id") int id) {
	    Branch model = branchRepo.findById(id);
	    if (model != null) {
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 @RequestMapping(value="/v1/branch/{id}",method=RequestMethod.PUT)
	  public ResponseEntity<String> updateBranch(@PathVariable("id") int id, @RequestBody Branch model) {
	    Branch _model = branchRepo.findById(id);
	    if (_model != null) {
	      
	      _model.setBranchName(model.getBranchName());
	      _model.setBranchCode(model.getBranchCode());
	      _model.setModifiedBy(model.getModifiedBy());
	      branchRepo.update(_model);
	      return new ResponseEntity<>("Branches was updated successfully.", HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Cannot find Branches with id=" + id, HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	  @RequestMapping(value="/v1/branches/{id}",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteBranch(@PathVariable("id") int id) {
	    try {
	      int result = branchRepo.deleteById(id);
	      if (result == 0) {
	        return new ResponseEntity<>("Cannot find Branches with id=" + id, HttpStatus.OK);
	      }
	      return new ResponseEntity<>("Branches was deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete branch.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @RequestMapping(value="/v1/branches",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteAllBranch() {
	    try {
	      int numRows = branchRepo.deleteAll();
	      return new ResponseEntity<>("Deleted " + numRows + " Branch(s) successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Cannot delete branches.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 

	  @RequestMapping(value="/v1/branches/branchName",method=RequestMethod.GET)
	  public ResponseEntity<List<Branch>> findByBranchName() {
	    try {
	      List<Branch> model = branchRepo.findByBranchName("String");
	      if (model.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(model, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	

}
