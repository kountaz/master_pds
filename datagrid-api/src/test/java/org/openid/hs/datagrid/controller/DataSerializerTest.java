package org.openid.hs.datagrid.controller;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;
import org.openid.hs.datagrid.controller.DataSerializer;

public class DataSerializerTest {
	public static String TEST_FILE_PATH = String.format("test.%d.ser", System.currentTimeMillis());
	@AfterClass
	public static void tearDown() {
		File f = new File(TEST_FILE_PATH);
		f.delete();
	}
	@Test
	public void toStringAndFromString() throws Exception {
		String content = new String("test");
		String serializedContent = DataSerializer.toString(content);
		assertTrue(DataSerializer.fromString(serializedContent).equals(content));
	}
	@Test
	public void writeIntoAndReadFromTest() throws Exception {
		String content = "test";
		DataSerializer.writeInto(content, TEST_FILE_PATH);
		String contentRead = (String) DataSerializer.readFrom(TEST_FILE_PATH);
		assertTrue(contentRead.equals(content));
	}
}
