package org.openid.hs.datagrid.bean;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openid.hs.datagrid.Data;
import org.openid.hs.datagrid.bean.DataBean;

public class DataBeanTest {
	@Test
	public void dataTest() {
		String testKey = "k1";
		Integer testValue = 1;
		int testExpiration = 2;
		
		Data data = new DataBean(testKey, testValue, testExpiration);
		
		assertTrue(
					data.getKey().equals(testKey) 
					&& data.getValue().equals(testValue) 
					&& data.getExpiration() == testExpiration
				);
	}
}
