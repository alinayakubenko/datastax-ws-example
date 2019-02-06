package com.al.gradlespringboot.util;

public class Constants {
		private Constants() {
		}
		
		public static final String GET_DATA_URI = "data";
		
		public static final String SELECT_CQL_TOKEN = "SELECT ";
		public static final String FROM_CQL_TOKEN = " FROM ";
		public static final String SEPARATOR_CQL_TOKEN = ", ";
		public static final String WHERE_CQL_TOKEN = " WHERE ";
		public static final String END_CQL_TOKEN = " ;";
		public static final String EQUALS_CQL_TOKEN = " = ";
		
		public static final String CASSANDRA_KEYSPACE = "test_service";
		public static final String CASSANDRA_TABLE = "test_table";
		public static final String CASSANDRA_TABLE_COLUMN_NBR = "nbr";
		public static final String CASSANDRA_TABLE_COLUMN_ITEM = "item";
		public static final String CASSANDRA_TABLE_COLUMN_PRICE = "price";
}
