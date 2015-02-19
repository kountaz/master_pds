package org.openid.hs.wc.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openid.hs.core.exception.BuildingException;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.core.helper.LoggerHelper;
import org.openid.hs.core.helper.StringHelper;
import org.openid.hs.wc.WorkerComponent;
import org.openid.hs.wc.helper.WCHelper;

public abstract class WCBuilder {
	public static final String BUILDER_CLASS_PATTERN = "%sBuilder";
	public static WCBuilder getBuilder(String pName) throws BuildingException {
		if (!WCHelper.checkName(pName)) {
			throw new BuildingException("The given name is invalid");
		}

		String type = WCHelper.getTypeFromName(pName);
		String builderClassName = WCBuilder.class.getPackage().getName()
				+ "."
				+ String.format(BUILDER_CLASS_PATTERN,
						StringHelper.upperCaseFirstLetter(type));

		try {
			Class<?> builderClass = Class.forName(builderClassName);
			WCBuilder builder = (WCBuilder) builderClass.newInstance();
			builder.setName(pName);
			return builder;
		} catch (Exception e) {
			throw new BuildingException(e);
		}
	}
	private String name;
	private Map<String, Object> parameters;
	protected WorkerComponent wc;
	public WCBuilder() {
		parameters = new HashMap<String, Object>();
	}
	private void setName(String pName) {
		name = pName;
	}
	public String getName() {
		return name;
	}
	public final void addParameter(String pKey, String pValue) {
		parameters.put(pKey, pValue);
	}
	public final void removeParameter(String pKey) {
		parameters.remove(pKey);
	}
	public final void setParameters(Map<String, Object> pParams) {
		for (Entry<String, Object> entry : pParams.entrySet()) {
			addParameter(entry.getKey(), entry.getValue().toString());
		}
	}
	public final Map<String, Object> getParameters() {
		return parameters;
	}
	protected void applyParameters() {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			try {
				wc.setParameter(entry.getKey(), entry.getValue());
			} catch (ServiceException e) {
				try {
					wc.setParameter(entry.getKey(),
							Integer.parseInt(entry.getValue().toString()));
				} catch (ServiceException ex) {
					LoggerHelper.warn(String.format(
							"Parameter %s is unknown. It was ignored",
							entry.getKey()));
				}
			}
		}
	}
	public abstract WorkerComponent build() throws BuildingException;
}
