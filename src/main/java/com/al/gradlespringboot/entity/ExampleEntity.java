package com.al.gradlespringboot.entity;

import com.al.gradlespringboot.util.Constants;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table (name = Constants.CASSANDRA_TABLE)
public class ExampleEntity {
	@PartitionKey
	@Column
	private int number;
	
	@Column
	private String item;
	
	@Column
	private float price;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ExampleEntity [" + Constants.CASSANDRA_TABLE_COLUMN_NBR + " = " +number+ " , "+ Constants.CASSANDRA_TABLE_COLUMN_ITEM +" = "+item+ " , "+ Constants.CASSANDRA_TABLE_COLUMN_PRICE +" = " +price +"]";
	}
}
