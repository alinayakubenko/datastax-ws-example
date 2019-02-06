package com.al.gradlespringboot.business;

import com.al.gradlespringboot.model.ExampleResponse;

public interface ExampleBusiness {
	
		public ExampleResponse fetchData();
		public void saveEntity();
		public void deledeEntity();
}
