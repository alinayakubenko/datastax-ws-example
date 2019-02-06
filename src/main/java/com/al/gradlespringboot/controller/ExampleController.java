package com.al.gradlespringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.al.gradlespringboot.business.ExampleBusiness;
import com.al.gradlespringboot.model.ExampleResponse;
import com.al.gradlespringboot.util.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@Api
@RestController
public class ExampleController {
	
	@Autowired
	ExampleBusiness exampleBusiness;
	
	@GetMapping(path = Constants.GET_DATA_URI)
	@ApiOperation(httpMethod = "GET", value = "Return data from the table", notes = "Retrieves data items and price", response = ExampleResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success. OK and JSON", response = ExampleResponse.class),
			@ApiResponse(code = 204, message = "No content.", response = ExampleResponse.class),
			@ApiResponse(code = 404, message = "Not Found."),
			@ApiResponse(code = 500, message = "Internal Server Error."),
			@ApiResponse(code = 503, message = "Service Unavailable.") })
	public ResponseEntity<Object> retrieveData(){
		ExampleResponse responseJson = exampleBusiness.fetchData();
		
		if (responseJson == null) {
			return new ResponseEntity<>(responseJson, HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(responseJson, HttpStatus.OK);
	}

}
