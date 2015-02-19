package org.openid.hs.qos;

/**
 * CommunicationParameter provide constants which are : BROKER_URL and QUEUE (SI BO)
 * @version R2
 * @author Victor
 **/
public interface CommunicationParameter 
{
	public static final String BROKER_TIS = "tcp://178.33.41.35:61616";
	public static final String BROKER_BC = "tcp://178.33.40.149:61616"; 
	public static final String QUEUE_TIS = "Queue_IN_ISIDIS";
	public static final String QUEUE_BC = "Supervision";
}