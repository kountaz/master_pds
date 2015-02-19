package org.openid.hs.qos.measure.dialog;

import java.util.ArrayList;

import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.qos.EnumParameter;
import org.openid.hs.qos.bean.NodeRules;
import org.openid.hs.qos.bean.Rules;
import org.openid.hs.qos.bean.Stimulus;
import org.openid.hs.qos.bean.ThresholdRules;

public class MeasureHelper 
{
	
	private MeasureHelper()
	{
		
	}
	
	/**
	 * Navigate into list of node and do comparison with stimulus data
	 * @return void
	 */
	public static void measureQos(ArrayList<NodeRules> lstNodes, Stimulus stimulus)
	{
		ArrayList<Rules> list_rules = lstNodes.get(0).getLstServiceRules().get(0).getLstRules();
		for(Rules tr : list_rules)
		{
			switch(tr.getName())
			{
				case EnumParameter.R1 : calculBitRate(tr, stimulus.getBit_rate()); 
					break;
				case EnumParameter.R2 : calculDelay(tr, stimulus.getDelay());
					break;
				case EnumParameter.R3 : calculPacketLossRate(tr,stimulus.getPacket_loss_rate());
					break;
				case EnumParameter.R4 : calculJitter(tr,stimulus.getJitter());
					break;
			}
		}
	}
	
	/**
	 * Compare the bit_rate between stimulus and our levels of bit_rate
	 * @return void
	 */
	public static void calculBitRate(Rules rules, int bit_rate)
	{
		for(ThresholdRules tr : rules.getList())
		{
			if(bit_rate < tr.getValue())
			{
				LoggerHelper.info("Qos " + "service : " + EnumParameter.S1 + " " + EnumParameter.R1 + " " + tr.getValue() + " " + rules.getUnit() + " Threshold : " + tr.getName());	
				break;
			}
		}
	}
	/**
	 * Compare the delay between stimulus and our levels of delay
	 * @return void
	 */
	public static void calculDelay(Rules rules, int delay)
	{
		for(ThresholdRules tr : rules.getList())
		{
			if(delay < tr.getValue())
			{
				LoggerHelper.info("Qos " + "service : " + EnumParameter.S1 + " " + EnumParameter.R2 + " " + tr.getValue() + " " + rules.getUnit() + " Threshold : " + tr.getName());	
				break;
			}
		}
	}
	/**
	 * Compare the packet_loss_rate between stimulus and our packet_loss_rate
	 * @return void
	 */
	public static void calculPacketLossRate(Rules rules, float packet_loss_rate)
	{
		for(ThresholdRules tr : rules.getList())
		{
			if(packet_loss_rate < tr.getValue())
			{
				LoggerHelper.info("Qos " + "service : " + EnumParameter.S1 + " " + EnumParameter.R3 + " " + tr.getValue() + " " + rules.getUnit() + " Threshold : " + tr.getName());
				break;
			}
		}
	}
	/**
	 * Compare the jitter between stimulus and our levels of jitter
	 * @return void
	 */
	public static void calculJitter(Rules rules, int jitter)
	{
		for(ThresholdRules tr : rules.getList())
		{
			if(jitter < tr.getValue())
			{
				LoggerHelper.info("Qos " + "service : " + EnumParameter.S1 + " " + EnumParameter.R4 + " " + tr.getValue() + " " + rules.getUnit() + " Threshold : " + tr.getName());
				break;
			}
		}
	}
}