package com.al.gradlespringboot.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.al.gradlespringboot.model.ExampleResponse;
import com.al.gradlespringboot.service.ExampleService;

@Component
public class ExampleBusinessImpl implements ExampleBusiness {

	@Autowired
	ExampleService exampleService;
	
	@Override
	public ExampleResponse fetchData() {
		ExampleResponse exampleResponse;
		exampleResponse = exampleService.getData();
		return exampleResponse;
	}

	@Override
	public void saveEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deledeEntity() {
		// TODO Auto-generated method stub
		
	}

}
