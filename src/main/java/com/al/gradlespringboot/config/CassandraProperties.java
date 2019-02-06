package com.al.gradlespringboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CassandraProperties {
	
	@Value ("${spring.cassandra.host}")
	private String host;
	
	@Value ("${spring.cassandra.port}")
	private int port;
	
	@Value ("${spring.cassandra.keyspace-name}")
	private String keyspaceName;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int i) {
		this.port = i;
	}

	public String getKeyspaceName() {
		return keyspaceName;
	}

	public void setKeyspaceName(String keyspaceName) {
		this.keyspaceName = keyspaceName;
	}
	

}
