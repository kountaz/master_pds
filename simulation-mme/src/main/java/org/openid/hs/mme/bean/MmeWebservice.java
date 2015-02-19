package org.openid.hs.mme.bean;

import java.util.UUID;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SocketHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.mme.controller.ReceiverMme;
import org.openid.hs.simulation.CallBeginBean;
import org.openid.hs.simulation.CallEndBean;
import org.openid.hs.simulation.Receiver;
import org.openid.hs.simulation.SimulationWebservice;


/**
 * mme webservice.
 * @see MmeWebservice.
 * @version R2
 * @author Fanta
 *
 */
@Path("/connexions")
public class MmeWebservice implements SimulationWebservice {
	
	
	private CallBeginBean cc;
	
	
	private CallEndBean cab;
	
	private ReceiverMme ro;
	
	//private SenderMme so;
	
	private Datagrid grid;
	
	public MmeWebservice(Datagrid pGrid) {
		grid = pGrid;
		/*
		 * Activate the receiver of Mme.
		 * */
		ro = new ReceiverMme();
	}
	@Override
	public Receiver getReceiver() {
		return ro;
	}
	@Override
	public int getPortReceiver() {
		return ro.getPort();
	}
	@Override
	public void setPortReceiver(int pPortReceiver) {
		ro.setPort(pPortReceiver);
	}
	@Override
	public String getTarget() {
		return ro.getTarget();
	}
	@Override
	public void setTarget(String pTarget) {
		ro.setTarget(pTarget);
	}
	@POST
	@Path("/connexion/start/{phonenumber1}/{phonenumber2}/")
	public Response createCallConnexionBegin(
		@PathParam("phonenumber1") String phone1,
		@PathParam("phonenumber2") String phone2) {
		/*
		 * Bean to send and to receive to entity's layer.
		 * */
		try{
			cc = new CallBeginBean(phone1,phone2);
			grid.set(UUID.randomUUID().toString(), cc);
			LoggerHelper.info("mme ws call begin request received..");
			/*
			 * Send the object to entity layer.
			 * */
			SocketHelper.send(cc,ro.getTarget());				
			ResponseBuilder rb = Response.status(200);
			return rb.build();
		} catch (Exception ex) {
			LoggerHelper.error("Bad request or parameter.");
			ResponseBuilder rb = Response.status(400);
			return rb.build();	
		}
	}


	@POST
	@Path("/connexion/stop/{phonenumber1}/{phonenumber2}/{emittedbytes}/{responseE}")
	public Response createCallConnexionEnd(
			@PathParam("phonenumber1") String phone1,
			@PathParam("phonenumber2") String phone2,
			@PathParam("emittedbytes") String emittedBytes,
			@PathParam("respE") String responseE) {
		
		try {
			/*
			 * Bean to send and to receive to entity's layer.
			 * */
			cab = new CallEndBean(phone1, phone2,Integer.parseInt(emittedBytes));
			grid.set(UUID.randomUUID().toString(), cab);
			cab.setEntityResponseE(responseE);
			/*
			 * Send the object to entity layer.
			 * */
			SocketHelper.send(cab,this.ro.getTarget());
			
			ResponseBuilder rb = Response.status(200);
			return rb.build();
		} catch (Exception e) {
			LoggerHelper.error("Bad request or parameter.");
			ResponseBuilder rb = Response.status(400);
			return rb.build();
		}
	}
}
