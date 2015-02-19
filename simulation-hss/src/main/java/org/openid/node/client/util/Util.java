package org.openid.node.client.util;

public class Util {

	
	public String pathCreator(String...component){
		String path = "";
		for(String str: component){
			path +=  String.format("%s/", str);
		}
		return path;
	}
	
	
	public String uriCreator(String uri){
		
		return "http://"+uri;
	}
}
