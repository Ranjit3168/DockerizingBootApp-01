package com.bpl.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.entity.Touriest;
import com.bpl.error.TouriestNotFoundException;
import com.bpl.service.ITouriestMgmtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Touriest",description="The Touriest Operation API")
public class TouriestOperationController {
	
    @Autowired
	private ITouriestMgmtService touriestService;
    
    @PostMapping("/save")
    @Operation(
    		summary="Save the touriest",
    		description="Insert the touriest"
    		)
    @ApiResponses(value= {
    		@ApiResponse(responseCode = "200",description = "Sucessful Operation")
    		})
	public ResponseEntity<String> saveTouriest(@RequestBody Touriest touriest){
		try {
			String resultMsg=touriestService.registerTouriest(touriest);
			return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @GetMapping("/show")
    public ResponseEntity<?> showReport(){
    	
    	try {
			List<Touriest> list=touriestService.showTouriest();
			return new ResponseEntity<List<Touriest>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
    	
    }
    
    
    @GetMapping("/report/{start}/{end}")
    public ResponseEntity<?> showReportBuBudget(@PathVariable double start,@PathVariable double end){
    	
    	try {
			List<Touriest> list=touriestService.showTouriestByBudgetRange(start, end);
			return new ResponseEntity<List<Touriest>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
    	
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTouriestById(@PathVariable int id){
    	try {
			Touriest touriest=touriestService.showTouriestById(id);
			return new ResponseEntity<Touriest>(touriest,HttpStatus.OK);
		} catch (TouriestNotFoundException e) {
		 return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> updateTouriest(@RequestBody Touriest touriest){
    	
    	try {
			String msg=touriestService.modifyTouriest(touriest);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PatchMapping("/pupdate/{id}/{percentage}")
    public ResponseEntity<?> updateTouriestBudget(@PathVariable int id,@PathVariable double percentage){
    	
    	try {
			String msg=touriestService.updateTouriestBudgetById(id, percentage);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTouriest(@PathVariable int id){
    try {
		String msg=touriestService.removeTouriestById(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
    @DeleteMapping("/remove/{type}")
    public ResponseEntity<?> deleteTouriestByPackage(@PathVariable String type){
    try {
		String msg=touriestService.deleteTouriestByPackageType(type);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
