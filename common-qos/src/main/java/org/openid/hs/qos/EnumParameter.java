package org.openid.hs.qos;

/**
 * EnumParameter provide constant for multiple treatment
 * @version R2
 * @author Victor
 **/
public interface EnumParameter 
{
	public static final String R1 = "bit_rate";
	public static final String R2 = "delay";
	public static final String R3 = "packet_loss_rate";
	public static final String R4 = "jitter";
	public static final String LOW = "low";
	public static final String MEDIUM = "medium";
	public static final String WARNING = "warning";
	public static final String CRITICAL = "critical";
	public static final String MME = "MME";
	public static final String ENODEB = "ENODEB";
	public static final String DSLAM = "DSLAM";
	public static final String S1 = "call";
	public static final String S2 = "sms";
	public static final String S3 = "mms";
	public static final String G1 = "rules";
}