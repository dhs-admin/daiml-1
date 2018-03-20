package gov.dhs.daiml.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("daimlservice")
public interface GWTService extends RemoteService {
	
	Boolean initService() throws IllegalArgumentException;
	
	Boolean authenticate(String name, String pwd) throws IllegalArgumentException;
	
	String predictPrice(String sqftStr, String lotSizeStr, String storiesStr, 
			String bedroomsStr, String bathroomsStr, String selectedGarageText, String selectedPoolText, 
			String selectedIslandText, String selectedFloordingText, String selectedGraniteText, String ageStr) throws IllegalArgumentException;
	
	String getDigit(String[] imageArray) throws IllegalArgumentException;
}
