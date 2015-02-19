package org.openid.hs.datagrid.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import biz.source_code.base64Coder.Base64Coder;

/**
 * Serialization utils.
 * @version R3
 * @author Steven, Victor
 *
 */
public final class DataSerializer {
	/**
	 * Serializes object.
	 * @param o Object to serialize.
	 * @return Serialized object.
	 * @throws IOException
	 */
	public static String toString(Serializable o) throws IOException {
		ByteArrayOutputStream array = new ByteArrayOutputStream();
		ObjectOutput output = new ObjectOutputStream(array);
		output.writeObject(o);
		String text = new String(Base64Coder.encode(array.toByteArray()));
		output.close();
		array.close();
		return text;
	}
	/**
	 * Unserializes object.
	 * @param s Serialized object.
	 * @return Unserialized object.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object fromString(String s) throws IOException, ClassNotFoundException {
		byte[] data = Base64Coder.decode(s);
		InputStream array = new ByteArrayInputStream(data);
		ObjectInput input = new ObjectInputStream(array);
		Object o = input.readObject();
		input.close();
		array.close();
		return o;
	}
	/**
	 * Serializes object and write in file.
	 * @param o Object to serialize.
	 * @param pFilePath File to write serialized object.
	 * @throws IOException
	 */
	public static void writeInto(Serializable o, String pFilePath) throws IOException {
		OutputStream file = new FileOutputStream(pFilePath);
		PrintWriter writer = new PrintWriter(file);
		
		writer.print(toString(o));
		
		writer.close();
		file.close();
	}
	/**
	 * Read and unserializes object.
	 * @param pFilePath File to read serialized object.
	 * @return Unserialized object.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readFrom(String pFilePath) throws IOException, ClassNotFoundException {
		File file = new File(pFilePath);
		Scanner scanner = new Scanner(file);
		
		String text = scanner.useDelimiter("\\A").next();
		Object content = fromString(text);
		
		scanner.close();
		
		return content;
	}
	private DataSerializer() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
}
