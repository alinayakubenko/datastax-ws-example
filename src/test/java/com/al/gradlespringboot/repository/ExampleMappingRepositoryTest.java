package com.al.gradlespringboot.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.al.gradlespringboot.config.CassandraConfig;
import com.al.gradlespringboot.config.CassandraProperties;
import com.al.gradlespringboot.entity.ExampleEntity;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

public class ExampleMappingRepositoryTest {
	
	ExampleMappingRepository exampleMappingRepository;
	
	@Autowired
	private CassandraConfig cassandraConfig;
	
	CassandraProperties cassandraProperties;
	
	ExampleEntity exampleEntity;
	
	private static MappingManager mappingManager;
	
	@Before
	public void setup() {
		cassandraConfig = new CassandraConfig();
		cassandraProperties = new CassandraProperties();
		cassandraProperties.setHost("127.0.0.1");
		cassandraProperties.setKeyspaceName("keyspace");
		cassandraProperties.setPort(9042);	
		ReflectionTestUtils.setField(cassandraConfig, "cassandraProperties", cassandraProperties, CassandraProperties.class);
		
		mappingManager = new MappingManager(cassandraConfig.dseCluster().connect(cassandraProperties.getKeyspaceName()));
		
		exampleMappingRepository = new ExampleMappingRepository(mappingManager);
		exampleEntity = new ExampleEntity();
		exampleEntity.setItem("pencil");
		exampleEntity.setNumber(1);
		exampleEntity.setPrice((float) 0.56);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		mappingManager.getSession().execute("truncate keyspace.table");
		
	}
	
	@Test
	public void getAllDataTest() {
		assertTrue(exampleMappingRepository.getAllData().isFullyFetched());
	}
	
	@Test
	public void saveTest() {
		exampleMappingRepository.save(exampleEntity);
		Result<ExampleEntity> receiveExampleEntity;
		Mapper<ExampleEntity> mapper = mappingManager.mapper(ExampleEntity.class);
		ResultSet results = mappingManager.getSession().execute("Select * from "+ cassandraProperties.getKeyspaceName()+ ".table where id = 1;");
		receiveExampleEntity = mapper.map(results);
		ExampleEntity exampleEntityFromDB = receiveExampleEntity.all().get(0);
		assertEquals("pencil", exampleEntityFromDB.getItem());
	}
}
