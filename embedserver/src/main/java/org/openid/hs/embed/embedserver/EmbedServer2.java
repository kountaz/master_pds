package org.openid.hs.embed.embedserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.openid.hs.bootstrap.WCConsoleAPI;
import org.openid.hs.core.helper.ResourceHelper;
import org.openid.hs.wc.WCType;


@Path("/test/admin")
public class EmbedServer2 {
	@GET
	@Path("/console")
	public String console() throws FileNotFoundException {
		return ResourceHelper.readFrom("/index.html");
	}
	@GET
	@Path("/start/{bootstrap}")
	public void startNode(@PathParam("bootstrap") String pBootstrapAddr) {
		System.out.println(String.format("WCConsoleAPI.start(%s);", pBootstrapAddr));
		WCConsoleAPI.start(pBootstrapAddr);
	}
	@GET
	@Path("/stop/{bootstrap}")
	public void stopNode(@PathParam("bootstrap") String pBootstrapAddr) {
		System.out.println(String.format("WCConsoleAPI.stop(%s);", pBootstrapAddr));
		WCConsoleAPI.stop(pBootstrapAddr);
	}
	@GET
	@Path("/scalability/{type}/{n}")
	public String scalability(@PathParam("type") String type, @PathParam("n") int n) {
		try {
			int PORT = 5678;
			StringBuffer url = new StringBuffer("http://192.168.0.");
			if (type.equals(WCType.ENB)) {
				url.append("38");
			} else if (type.equals(WCType.MME)) {
				url.append("37");
			} else if (type.equals(WCType.HSS)) {
				url.append("36");
			} else {
				return "Type node is unknown";
			}
			url.append(":"+PORT);
			url.append("/test/scalability/actions/"+type+"/"+n);
			URL concreteUrl = new URL(url.toString());
			concreteUrl.openStream();
		} catch (Exception e) {
			return "Scalability actions failures";
		}
		return "Scalability actions has been successfully sent!";
	}
}