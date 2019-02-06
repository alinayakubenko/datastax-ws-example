package com.al.gradlespringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.dse.DseCluster;
import com.datastax.driver.dse.DseSession;
import com.datastax.driver.mapping.MappingManager;

@Configuration
@EnableCassandraRepositories(basePackages = {"com.al.gradlespringboot.repository"})
public class CassandraConfig {
	
	private DseSession dseSession;
	
	@Autowired
	private CassandraProperties cassandraProperties;
	
	@Bean
	public DseCluster dseCluster() {

		DseCluster.Builder dseClusterBuilder = DseCluster.builder().addContactPoints(cassandraProperties.getHost().split(","))
				.withLoadBalancingPolicy(new TokenAwarePolicy(
						DCAwareRoundRobinPolicy.builder()
						.build()))
				.withPort(cassandraProperties.getPort());
		
		DseCluster dseCluster = dseClusterBuilder.build();
	     return dseCluster;
	}
	
	
	@Bean
	public DseSession dseSession(DseCluster dseCluster) {
		DseSession session = dseCluster.connect(cassandraProperties.getKeyspaceName());
		
		dseSession = session;
			return dseSession;
		    }
	
	@Bean
	public MappingManager mapper(DseSession dseSession) {
		return new MappingManager(dseSession);
		
	}
	
}
