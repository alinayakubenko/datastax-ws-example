package com.al.gradlespringboot.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.al.gradlespringboot.model.ExampleResponse;
import com.al.gradlespringboot.service.ExampleService;

@RunWith(MockitoJUnitRunner.class)
public class ExampleBusinessImplTest {
	
	ExampleBusinessImpl exampleBusinessImpl;
	
	@InjectMocks
	//@Autowired
	ExampleService exampleService;
	
	@Mock
	ExampleResponse exampleResponse; 
	
	@Before
	public void setUp() {
		//MockitoAnnotations.initMocks(this);
	    exampleResponse = new ExampleResponse();
		
	}
	
	@Test
	public void testFetchDataNull() {
		Mockito.when(exampleService.getData()).thenReturn(null);
		Assert.assertNull("Test passed ", exampleBusinessImpl.fetchData());
	}
	
	@Test
	public void testFetchData() {
		//ExampleResponse exampleResponse = new ExampleResponse();
		exampleResponse.item.add("pen");
		exampleResponse.number.add("1");
		exampleResponse.price.add("24.53");
		Mockito.when(exampleService.getData()).thenReturn(exampleResponse);
		Assert.assertNull("Test passed ", exampleBusinessImpl.fetchData());
	}
}
