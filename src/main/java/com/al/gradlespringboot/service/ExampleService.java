package com.al.gradlespringboot.service;

import com.al.gradlespringboot.entity.ExampleEntity;
import com.al.gradlespringboot.model.ExampleResponse;

public interface ExampleService {
	
		public ExampleResponse getData();
		public void deleteData(ExampleEntity exampleEntity);
		public void saveData(ExampleEntity exampleEntity);
}
