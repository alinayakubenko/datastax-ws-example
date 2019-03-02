package com.al.gradlespringboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.al.gradlespringboot.entity.ExampleEntity;
import com.al.gradlespringboot.service.ExampleServiceImpl;
import com.al.gradlespringboot.util.Constants;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Mapper.Option;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

@Repository
public class ExampleMappingRepository {

	public ExampleMappingRepository(MappingManager mappingManager) {
		this.mappingManager = mappingManager;
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleServiceImpl.class);
	
	@Value ("${spring.cassandra.keyspace-name}")
	private String keyspaceName;
	
	@Autowired
	private MappingManager mappingManager;
	
	public Result<ExampleEntity> getAllData() {
		Result<ExampleEntity> results = null;
		ResultSet getData = null;
		Mapper<ExampleEntity> mapper = mappingManager.mapper(ExampleEntity.class);	
		Statement statement = QueryBuilder.select().all().from(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_TABLE);
		try {
			getData = mappingManager.getSession().execute(statement);
			results = mapper.map(getData);
		} catch (Exception e) {
			LOGGER.error("Error while retrieving data ", e);
		}
		
		return results;
	}
	
	public void delete(String item) {
		Statement statement = QueryBuilder.delete().all().from(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_TABLE).where(QueryBuilder.eq(Constants.CASSANDRA_TABLE_COLUMN_ITEM, item));
		mappingManager.getSession().execute(statement);
	}
	
	public void save(ExampleEntity exampleEntity) {
		Mapper<ExampleEntity> mapper = mappingManager.mapper(ExampleEntity.class);
		mapper.save(exampleEntity, Option.saveNullFields(false));
	}
}
