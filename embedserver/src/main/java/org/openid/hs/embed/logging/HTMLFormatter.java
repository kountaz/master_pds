package org.openid.hs.embed.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class HTMLFormatter extends Formatter 
{

	
	public String format(LogRecord record) 
	{
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append("<span>");

		return buffer.toString();
	}
}
