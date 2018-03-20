package gov.dhs.daiml.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTServiceAsync {
	
	void initService(AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	
	void authenticate(String username, String pwd, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	
	void predictPrice(String sqftStr, String lotSizeStr, String storiesStr, 
			String bedroomsStr, String bathroomsStr, String selectedGarageText, String selectedPoolText, 
			String selectedIslandText, String selectedFloordingText, String selectedGraniteText, String ageStr, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void getDigit(String[] imageArray, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
