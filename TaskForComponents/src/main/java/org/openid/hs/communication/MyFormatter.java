package org.openid.hs.communication;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.openid.hs.feature.bean.ObjectForBRAS;
import org.openid.hs.feature.bean.ObjectForCOMMUTATOR;
import org.openid.hs.feature.bean.ObjectForDSLAM;
import org.openid.hs.feature.bean.ObjectForHSS;
import org.openid.hs.feature.bean.ObjectForMME;
import org.openid.hs.feature.bean.ObjectForNOTIFY;
import org.openid.hs.feature.bean.ObjectForSIGNALSTOP;
import org.openid.hs.feature.formater.FormatBRAS;
import org.openid.hs.feature.formater.FormatCOMMUTATOR;
import org.openid.hs.feature.formater.FormatDSLAM;
import org.openid.hs.feature.formater.FormatHSS;
import org.openid.hs.feature.formater.FormatMME;
import org.openid.hs.feature.formater.FormatNOTIFY;
import org.openid.hs.feature.formater.FormatSIGNALSTOP;
import org.openid.hs.wc.controller.SuperFormatter;

public class MyFormatter extends SuperFormatter {
	
	public ObjectForNOTIFY  inputFormatNOTIFY(String pMessage) throws DocumentException {
		FormatNOTIFY notify = new FormatNOTIFY();

		return notify.inputFormatNOTIFY(pMessage);
		
	}

	public String outputFormatNOTIFY(ObjectForNOTIFY notify) throws DocumentException {
		
		FormatNOTIFY s = new FormatNOTIFY();

		return s.outputFormatNOTIFY(notify);
	}
	
	public ObjectForMME inputFormatMME(String pMessage) throws DocumentException {
		FormatMME s = new FormatMME();

		return s.inputFormatMME(pMessage);
	}
	public String outputFormatMME(ObjectForMME hss) {
		FormatMME s = new FormatMME();

		return s.outputFormatMME(hss);
	}

	public ObjectForHSS inputFormatHSS(String pMessage) throws DocumentException {
		FormatHSS s = new FormatHSS();

		return s.inputFormatHSS(pMessage);
	}
	public String outputFormatHSS(ObjectForHSS hss) {
		FormatHSS s = new FormatHSS();

		return s.outputFormatHSS(hss);
	}

	public ObjectForBRAS inputFormatBRAS(String pMessage) throws DocumentException {
		FormatBRAS s = new FormatBRAS();

		return s.inputFormatBRAS(pMessage);

	}
	public String outputFormatDSLAM(ObjectForDSLAM dslam) {
		FormatDSLAM s = new FormatDSLAM();

		return s.outputFormatDSLAM(dslam);
	}
	
	public ObjectForDSLAM inputFormatDSLAM(String pMessage) throws DocumentException {
		FormatDSLAM s = new FormatDSLAM();

		return s.inputFormatDSLAM(pMessage);
		//return new ObjectForDSLAM();

	}
	public String outputFormatBRAS(ObjectForBRAS bras) {
		FormatBRAS s = new FormatBRAS();

		return s.outputFormatBRAS(bras);
	}

	public ObjectForCOMMUTATOR inputFormatCOMMUTATOR(String pMessage) throws DocumentException {
		FormatCOMMUTATOR s = new FormatCOMMUTATOR();

		return s.inputFormatCOMMUTATOR(pMessage);
 
	}
	public String outputFormatCOMMUTATOR(ObjectForCOMMUTATOR bras) {
		FormatCOMMUTATOR s = new FormatCOMMUTATOR();

		return s.outputFormatCOMMUTATOR(bras);
	}

	
	public ObjectForSIGNALSTOP inputFormatSIGNALSTOP(String pMessage) throws DocumentException {
		FormatSIGNALSTOP s = new FormatSIGNALSTOP();

		return s.inputFormatSIGNALSTOP(pMessage);
		//return new ObjectForSIGNALSTOP();
	}

	public String outputFormatSIGNALSTOP(ObjectForSIGNALSTOP stop) {
		FormatSIGNALSTOP s = new FormatSIGNALSTOP();

		return s.outputFormatSIGNALSTOP(stop);
	}
	
	
	
}
