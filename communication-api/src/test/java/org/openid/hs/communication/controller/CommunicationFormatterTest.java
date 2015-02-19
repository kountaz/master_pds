package org.openid.hs.communication.controller;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openid.hs.communication.CommunicationFormatter;
import org.openid.hs.communication.exception.FormatObjectException;

public class CommunicationFormatterTest {
	public static final String TEST_OUTPUT_OBJECT_FORMATTED = "x";
	public static final String TEST_INPUT_OBJECT_FORMATTED = "y";
	public static final String TEST_TYPE_FORMATTED = "Z";
	private static CommunicationFormatter instance;
	@BeforeClass
	public static void setUp() throws FormatObjectException {
		
		class FormatterClass extends CommunicationFormatter {
			public FormatterClass() {
				super();
			}
			@SuppressWarnings("unused")
			public String outputFormatZ(Object pObject) {
				return TEST_OUTPUT_OBJECT_FORMATTED;
			}
			@SuppressWarnings("unused")
			public String inputFormatZ(Object pObject) {
				return TEST_INPUT_OBJECT_FORMATTED;
			}
		}
		instance = new FormatterClass();
	}
	@AfterClass
	public static void tearDown() {
		instance = null;
	}
	@Test
	public void testCanOutputFormat() throws Exception {
		assertTrue(instance.canOutputFormat(TEST_TYPE_FORMATTED) && !instance.canOutputFormat(null));
	}
	@Test
	public void testCanInputFormat() throws Exception {
		assertTrue(instance.canOutputFormat(TEST_TYPE_FORMATTED) && !instance.canInputFormat(null));
	}
	@Test
	public void testOutputFormat() throws Exception {
		assertTrue(instance.outputFormat(TEST_TYPE_FORMATTED, null).equals(TEST_OUTPUT_OBJECT_FORMATTED));
	}
	@Test
	public void testInputFormat() throws Exception {
		assertTrue(instance.inputFormat(TEST_TYPE_FORMATTED, null).equals(TEST_INPUT_OBJECT_FORMATTED));
	}
	@Test(expected=FormatObjectException.class)
	public void testOutputFormatFailed() throws Exception {
		instance.outputFormat(null, null);
	}
	@Test(expected=FormatObjectException.class)
	public void testInputFormatFailed() throws Exception {
		instance.outputFormat(null, null);
	}
}
