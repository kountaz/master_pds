package org.openid.hs.enodeb.bean;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.SocketHelper;
import org.openid.hs.datagrid.Datagrid;
import org.openid.hs.enodeb.controller.ReceiverEnodeb;
import org.openid.hs.simulation.CallBeginBean;
import org.openid.hs.simulation.CallEndBean;
import org.openid.hs.simulation.Receiver;
import org.openid.hs.simulation.SimulationWebservice;

/**
 * enodeb webservice.
 * @see EnodebWebservice.
 * @version R3
 * @author Fanta
 *
 */
@Path("/calls")
public class EnodebWebservice implements SimulationWebservice {
	
	
	private CallBeginBean acallbegin;
	
	private CallEndBean acallend;
	
	//private ReceiverEnodeb ro;
	
	private Pattern DEFAULT_PHONE_PATTERN = Pattern.compile("\\d{10}");
	
	private Pattern DEFAULT_BYTE_PATTERN = Pattern.compile("\\d{2}");

	private Matcher matcher1;
	
	private Matcher matcher2;
	
	private ReceiverEnodeb receiver;
	
	private Datagrid grid;
	
	public EnodebWebservice(Datagrid pGrid) {
		grid = pGrid;
		receiver = new ReceiverEnodeb();
	}
	@Override
	public Receiver getReceiver() {
		return receiver;
	}
	@Override
	public int getPortReceiver() {
		return receiver.getPort();
	}
	@Override
	public void setPortReceiver(int pPortReceiver) {
		receiver.setPort(pPortReceiver);
	}
	@Override
	public String getTarget() {
		return receiver.getTarget();
	}
	@Override
	public void setTarget(String pTarget) {
		receiver.setTarget(pTarget);
	}

	@GET
	@Path("/callbegin/{phonenumber1}/{phonenumber2}")
	public Response createCallBegin(
		@PathParam("phonenumber1") String phone1,
		@PathParam("phonenumber2") String phone2) {
		
		try {
			/*
			 * Test syntax for numbers phone.
			 * */
				matcher1 = DEFAULT_PHONE_PATTERN.matcher(phone1);
				matcher2 = DEFAULT_PHONE_PATTERN.matcher(phone2);
			
				if(matcher1.matches() == false || matcher2.matches() == false){
					LoggerHelper.error("enodeb ws Call begin received bad parameter..");
					throw new Exception();
				}
			
			LoggerHelper.info("enodeb ws call begin request received..");
			
			/*
			 * Create the object to send entity layer.
			 * */
			acallbegin = new CallBeginBean(phone1,phone2);
			grid.set(UUID.randomUUID().toString(), acallbegin);
		/*
			 * Send the object to entity layer.
			 * */
			//SenderEnodeb.send(acallbegin, receiver.getPort());
			SocketHelper.send(acallbegin, receiver.getTarget());
			
			ResponseBuilder rb = Response.status(200);
			return rb.build();
		} catch (Exception e) {
			LoggerHelper.error("Bad request or parameter. "+e.getMessage());
			e.printStackTrace();
			ResponseBuilder rb = Response.status(400);
			return rb.build();
		}
	}

	@GET
	@Path("/callend/{phonenumber1}/{phonenumber2}/{emittedbytes}")
	public Response createCallEnd(
			@PathParam("phonenumber1") String phone1,
			@PathParam("phonenumber2") String phone2,
			@PathParam("emittedbytes") String emittedBytes) 
	{		
		LoggerHelper.info("[enodeb ws] Call end request received..");
		try
		{
			/*
			 * Test syntax for numbers phone.
			 * */
				matcher1 = DEFAULT_PHONE_PATTERN.matcher(phone1);
				matcher2 = DEFAULT_PHONE_PATTERN.matcher(phone2);
			
				if(matcher1.matches() == false || matcher2.matches() == false){
					LoggerHelper.error("Bad parameter received by createCallEnd");
					throw new Exception();
					
				}
				
			/*
			 * Test syntax for bytes.
			 * */
				matcher1 = DEFAULT_BYTE_PATTERN.matcher(emittedBytes);
				
				if(matcher1.matches() == false ){
					throw new Exception();
				}
			
			/*
			 * Convert byte to int.
			 * */
				
			int emittedBytesInt = Integer.parseInt(emittedBytes.toString().trim());
			
			
			/*
			 * Create the object to send entity layer.
			 * */
			acallend = new CallEndBean(phone1,phone2,emittedBytesInt);
			grid.set(UUID.randomUUID().toString(), acallend);
			
			/*
			 * Send the object to entity layer.
			 * */
			//SenderEnodeb.send(acallend, receiver.getPort());
			SocketHelper.send(acallend, receiver.getTarget());
		
			ResponseBuilder rb = Response.status(200);
		
			return rb.build();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			ResponseBuilder rb = Response.status(400);	
			return rb.build();
		}
	}
	
	@GET
	@Path("/callbegin/{phonenumber1}/{phonenumber2}/{step}")
	public Response createCallBegin(
		@PathParam("phonenumber1") String phone1,
		@PathParam("phonenumber2") String phone2,
		@PathParam("step") int step) {
		
		ResponseBuilder rb = null;
		try {
			/*
			 * Test syntax for numbers phone.
			 * */
				matcher1 = DEFAULT_PHONE_PATTERN.matcher(phone1);
				matcher2 = DEFAULT_PHONE_PATTERN.matcher(phone2);
			
				if(matcher1.matches() == false || matcher2.matches() == false){
					LoggerHelper.error("enodeb ws Call begin received bad parameter..");
					throw new Exception();
				}
			
			LoggerHelper.info("enodeb ws call begin request received..");
			
			for(int i=0; i < step; i++)
			{
				/*
				 * Create the object to send entity layer.
				 * */
				acallbegin = new CallBeginBean(phone1,phone2);
				grid.set(UUID.randomUUID().toString(), acallbegin);
			/*
				 * Send the object to entity layer.
				 * */
				//SenderEnodeb.send(acallbegin, receiver.getPort());
				SocketHelper.send(acallbegin, receiver.getTarget());
				
				rb = Response.status(200);
			}
		} catch (Exception e) {
			LoggerHelper.error("Bad request or parameter. "+e.getMessage());
			e.printStackTrace();
			rb = Response.status(400);
			return rb.build();
		}
		return rb.build();
	}
	
}
	
	

