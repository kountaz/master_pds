package org.openid.hs.UniformCommunicationFramework.MsgSender;
import org.openid.hs.UniformCommunicationFramework.Msg.*;
import org.openid.hs.UniformCommunicationFramework.Msg.bean.MessageImpl;
public interface ISender {
	public void SendMessage(MessageImpl msg);
}
