package org.openid.hs.hss.bean;

import java.util.UUID;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SocketHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.hss.controller.ReceiverHss;
import org.openid.hs.simulation.CallAuthorizationBean;
import org.openid.hs.simulation.CallEndBean;
import org.openid.hs.simulation.Receiver;
import org.openid.hs.simulation.SimulationWebservice;

/**
 * hss webservice.
 * 
 * @see HssWebservice.
 * @version R2
 * @author Fanta
 * 
 */
@Path("/autorizations")
public class HssWebservice implements SimulationWebservice {
	/**
	 * Default toString() pattern.
	 */

	private CallEndBean cab;

	private CallAuthorizationBean c;

	private ReceiverHss ro;
	
	private Datagrid grid;

	//private SenderHss so;

	public HssWebservice(Datagrid pGrid) {
		grid = pGrid;
		ro = new ReceiverHss();
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
	public String getTarget() {
		return ro.getTarget();
	}
	@Override
	public void setTarget(String pTarget) {
		ro.setTarget(pTarget);
	}
	@Override
	public void setPortReceiver(int pPortReceiver) {
		ro.setPort(pPortReceiver);
	}
	
	@POST
	@Path("/call/begin/{phonenumber1}/{mmeresponse}")
	public Response createCallAuthorization(
			@PathParam("phonenumber1") String phone1,
			@PathParam("mmeresponse") String mmeValidation) {

		try {
			LoggerHelper
					.info("hssWebservice call authorization request received..");

			/*
			 * Create the object to send entity layer.
			 */
			c = new CallAuthorizationBean(phone1, mmeValidation);
			grid.set(UUID.randomUUID().toString(), c);

			/*
			 * Send the object to entity layer.
			 */
			SocketHelper.send(c, this.ro.getTarget());
			LoggerHelper.info("send to entity layer hss");
			ResponseBuilder rb = Response.status(200);
			return rb.build();

		} catch (Exception e) {

			LoggerHelper.error("Bad request or parameter.");
			ResponseBuilder rb = Response.status(400);
			return rb.build();
		}
	}

	@POST
	@Path("/call/end/{phonenumber1}/{phonenumber2}/{emittedByte}/{mmeresponse}")
	public Response createCallEnd(@PathParam("phonenumber1") String phone1,
			@PathParam("phonenumber1") String phone2,
			@PathParam("emittedByte") String emittedbyte,
			@PathParam("mmeresponse") String mmeValidation) {

		try {
			LoggerHelper.info("hssWebservice call end request received..");

			/*
			 * Create the object to send entity layer.
			 */
			cab = new CallEndBean(phone1, phone2, Integer.parseInt(emittedbyte));
			grid.set(UUID.randomUUID().toString(), cab);
			cab.getEntityResponseM();

			/*
			 * Send the object to entity layer.
			 */
			//SocketHelper.send(cab, this.ro.getTarget());

			ResponseBuilder rb = Response.status(200);
			return rb.build();

		} catch (Exception e) {

			LoggerHelper.error("Bad request or parameter.");
			ResponseBuilder rb = Response.status(400);
			return rb.build();
		}
	}

}
