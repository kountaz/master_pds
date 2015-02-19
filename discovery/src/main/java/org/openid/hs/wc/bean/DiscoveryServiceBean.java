package org.openid.hs.wc.bean;

import org.openid.hs.core.AbstractService;
import org.openid.hs.core.exception.ServiceException;
import org.openid.hs.discovery.bean.LoaderMaterialReferentiel;
import org.openid.hs.discovery.exception.InvalidParameters;
import org.openid.hs.wc.DiscoveryService;

import com.mongodb.DBCursor;

public class DiscoveryServiceBean extends AbstractService implements DiscoveryService {

	public static final String SERVICE_NAME = "discovery";
	private String referentialFile;
	private LoaderMaterialReferentiel referentiel;

	public DiscoveryServiceBean() {
		super(SERVICE_NAME);
	}

	@Override
	public void start() throws ServiceException {
		try {
			LoaderMaterialReferentiel referentiel = new LoaderMaterialReferentiel();
			if (referentialFile == null) {
				referentiel.dataMaterialDefaultLoaded();
			} else {
				referentiel.dataMaterialLoadedFromProperties(referentialFile);
			}
			super.start();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void stop() throws ServiceException {
		referentiel = null;
		
		super.stop();
	}

	//@Override
	public void dataMaterialDefaultLoaded() {
		referentiel.dataMaterialDefaultLoaded();
		
	}

	//@Override
	public void dataMaterialLoadedFromProperties(String pFilePath) {
		referentiel.dataMaterialLoadedFromProperties(pFilePath);
	}

	//@Override
	public DBCursor selectDataMaterial(String... pFilter)
			throws InvalidParameters {

		return referentiel.selectDataMaterial(pFilter);
	}

	@Override
	public void setReferential(String pFilePath) {
		referentialFile = pFilePath;
	}

	@Override
	public String getReferential() {
		return referentialFile;
	}
}
