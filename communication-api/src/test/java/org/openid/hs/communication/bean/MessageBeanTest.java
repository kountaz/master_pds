package org.openid.hs.communication.bean;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openid.hs.communication.bean.MessageBean;

public class MessageBeanTest {
	public static final String TEST_TYPE = "PSTNCallStart";
	public static final String TEST_GROUP = "pstn.exchange";
	public static final long TEST_EXPIRATION = 123;
	public static final int TEST_PRIORITY = 3;
	public static final Object TEST_CONTENT = new Object();
	private static MessageBean instance;
	@BeforeClass
	public static void setUp() {
		instance = new MessageBean(TEST_TYPE, TEST_GROUP, TEST_EXPIRATION, TEST_PRIORITY, TEST_CONTENT);
	}
	@AfterClass
	public static void tearDown() {
		instance = null;
	}
	@Test
	public void testFullConstructor() {
		assertTrue(
				instance.getType().equals(TEST_TYPE) && instance.getGroup().equals(TEST_GROUP)
				&& instance.getExpiration() == TEST_EXPIRATION && instance.getPriority() == TEST_PRIORITY
				&& instance.getContent().equals(TEST_CONTENT)
			);
	}
	@Test
	public void testSetters() {
		instance = new MessageBean(null, null);
		instance.setType(TEST_TYPE);
		instance.setGroup(TEST_GROUP);
		instance.setExpiration(TEST_EXPIRATION);
		instance.setPriority(TEST_PRIORITY);
		instance.setContent(TEST_CONTENT);
		assertTrue(
				instance.getType().equals(TEST_TYPE) && instance.getGroup().equals(TEST_GROUP)
				&& instance.getExpiration() == TEST_EXPIRATION && instance.getPriority() == TEST_PRIORITY
				&& instance.getContent().equals(TEST_CONTENT)
			);
	}
}
