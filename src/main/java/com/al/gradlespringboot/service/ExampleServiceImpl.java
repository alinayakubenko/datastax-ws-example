package com.al.gradlespringboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.al.gradlespringboot.entity.ExampleEntity;
import com.al.gradlespringboot.model.ExampleResponse;
import com.al.gradlespringboot.repository.ExampleMappingRepository;
import com.al.gradlespringboot.util.Utility;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

@Component
public class ExampleServiceImpl implements ExampleService{
	
	@Value ("${item.name}")
	private String itemName;
	
	@Autowired
	ExampleMappingRepository exampleMappingRepository;
	
	@Autowired
	MappingManager mappingManager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleServiceImpl.class);
	
	@Override
	public ExampleResponse getData() {
		ExampleResponse returnRes;
		Result<ExampleEntity> results;
		results = exampleMappingRepository.getAllData();
		LOGGER.debug("getData() result is: "+results);
		returnRes = Utility.convertToDTO(results);
		return returnRes;
	}

	@Override
	public void saveData(ExampleEntity exampleEntity) {
		exampleMappingRepository.save(exampleEntity);
		
	}

	@Override
	public void deleteData(ExampleEntity exampleEntity) {
		exampleMappingRepository.delete(exampleEntity.getItem());
		
	}

}
