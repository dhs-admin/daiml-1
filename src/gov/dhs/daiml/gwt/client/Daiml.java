package gov.dhs.daiml.gwt.client;

import gov.dhs.daiml.gwt.client.gui.LoginPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Daiml implements EntryPoint {

	@Override
	public void onModuleLoad() {
		displayLogin();
	}

	public void displayLogin() {
		final LoginPanel loginPanel = new LoginPanel();
		final RootLayoutPanel rootPanel = RootLayoutPanel.get();
		rootPanel.add(loginPanel);
	}
}