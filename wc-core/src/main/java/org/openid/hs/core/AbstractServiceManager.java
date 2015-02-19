package org.openid.hs.core;

import java.lang.reflect.Method;

import org.openid.hs.core.exception.ProfileException;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.StringHelper;

/**
 * Abstract implementation of ServiceManager which defines default 
 * method implementations.
 * @see ServiceManager
 * @version R2
 * @author Steven
 *
 */
public abstract class AbstractServiceManager implements ServiceManager {
	/**
	 * Default set method key.
	 */
	public static final String SETTER_METHOD_KEYWORD = "set";
	/**
	 * Default get method key.
	 */
	public static final String GETTER_METHOD_KEYWORD = "get";
	
	/**
	 * Profile of this service manager.
	 */
	private Profile profile;
	
	public AbstractServiceManager(Profile pProfile) {
		profile = pProfile;
	}
	@Override
	public Profile getProfile() {
		return profile;
	}
	@Override
	public void startService(String pServiceName) 
			throws ServiceException {
		
		Service service;
		try {
			service = profile.getService(pServiceName);
			service.start();
		} catch (ProfileException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void stopService(String pServiceName) 
			throws ServiceException {

		Service service;
		try {
			service = profile.getService(pServiceName);
			service.stop();
		} catch (ProfileException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void setParameter(String pServiceName, String pKey, Object pValue) 
			throws ServiceException {
		
		String setMethodName = 
				SETTER_METHOD_KEYWORD+StringHelper.upperCaseFirstLetter(pKey);
		try {
			Service service = profile.getService(pServiceName);
			Method m;
			m = service.getClass().getMethod(
					setMethodName, 
					pValue.getClass()
				);
			m.invoke(service, pValue);
		} catch (Exception e) {
			throw new ServiceException(String.format(
					"Setting parameter has failed: verify that service '%s' exists and contains method %s(%s)",
					pServiceName,
					setMethodName,
					pValue.getClass().getSimpleName()
				), e);
		}
	}
	@Override
	public Object getParameter(String pServiceName, String pKey) 
			throws ServiceException {
		
		String getMethodName = 
				GETTER_METHOD_KEYWORD+StringHelper.upperCaseFirstLetter(pKey);
		
		try {
			Service service = profile.getService(pServiceName);
			Method m = service.getClass().getMethod(
					getMethodName
				);
			return m.invoke(service);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
