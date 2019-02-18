package com.al.gradlespringboot.config;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.dse.DseCluster;
import com.datastax.driver.dse.DseSession;

@RunWith(MockitoJUnitRunner.class)
public class CassandraConfigTest {

	CassandraConfig cassandraConfig;
	CassandraProperties cassandraProperties;
	
	@Mock
	private DseSession dseSession;
	
	@Before
	public void setup() {
		cassandraConfig = new CassandraConfig();
		cassandraProperties = new CassandraProperties();
		cassandraProperties.setHost("127.0.0.1");
		cassandraProperties.setKeyspaceName("keyspace");
		cassandraProperties.setPort(9042);
		ReflectionTestUtils.setField(cassandraConfig, "cassandraProperties", cassandraProperties, CassandraProperties.class);
		
	}
	
	@Test
	public void testCluster() {
		DseCluster cluster = cassandraConfig.dseCluster();
		assertNotNull(cluster);
	}
	
	@Test
	public void testDSESessionInitialization() {
		DseCluster cluster1 = Mockito.mock(DseCluster.class);
		DseSession session = Mockito.mock(DseSession.class);
		
		Metadata metadata = Mockito.mock(Metadata.class);
		Host host = Mockito.mock(Host.class);
		Set<Host> hostList = new HashSet<>();
		hostList.add(host);
		Mockito.when(cluster1.getMetadata()).thenReturn(metadata);
		Mockito.when(metadata.getAllHosts()).thenReturn(hostList);
		Mockito.when(cluster1.connect(Mockito.anyString())).thenReturn(session);
	}
	
	@Test
	public void testCassandraMapping() throws ClassNotFoundException {
		assertNotNull(cassandraConfig.mapper(cassandraConfig.dseSession(cassandraConfig.dseCluster())));
	}
}
