package org.openid.hs.wc.scenario;

import org.openid.hs.core.Profile;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.wc.KernelService;
import org.openid.hs.wc.WCStatus;
import org.openid.hs.wc.WorkerComponent;

public class mock implements WorkerComponent {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Profile getProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startService(String pServiceName) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopService(String pServiceName) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParameter(String pServiceName, String pKey, Object pValue)
			throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getParameter(String pServiceName, String pKey)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNetwork() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumericId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WCStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KernelService getKernel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameter(String pParameterPath, Object pValue)
			throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getParameter(String pParameterPath) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
