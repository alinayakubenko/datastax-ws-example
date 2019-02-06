package com.al.gradlespringboot.util;

import com.al.gradlespringboot.entity.ExampleEntity;
import com.al.gradlespringboot.model.ExampleResponse;
import com.datastax.driver.mapping.Result;

public class Utility {
	
	public static ExampleResponse convertToDTO(Result<ExampleEntity> results) {
		ExampleResponse result = new ExampleResponse();
		for (int i =0; i<results.all().size(); i++) {
		
			result.number.add(String.valueOf(results.all().get(i).getNumber()));
			result.number.add(results.all().get(i).getItem());
			result.number.add(String.valueOf(results.all().get(i).getPrice()));
		}
		return result;
	}
}
