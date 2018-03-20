package gov.dhs.daiml.gwt.client.gui;

import gov.dhs.daiml.gwt.client.GWTService;
import gov.dhs.daiml.gwt.client.GWTServiceAsync;
import gov.dhs.daiml.gwt.client.gui.dialog.MessageDialogBox;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;


public class LoginPanel extends DockLayoutPanel {
	
	// Hardcoded authentication for now
	private final String username = "daiml";
	private final String password = "dhs123?";

	// See Daiml.gwt.xml
	private Logger log = Logger.getLogger("LoginPanel");
	private final PushButton loginButton = new PushButton("LOGIN");
	private final TextBox userNameTextBox = new TextBox();
	private final PasswordTextBox passwordTextBox = new PasswordTextBox();
	private final GWTServiceAsync daimlService = GWT.create(GWTService.class);
	private MessageDialogBox messageDialogBox = null;
	private DockPanel dockPanel = null;
	private Grid grid = null;
	HTML progressHtml = null;

	public LoginPanel() {
		super(Unit.PX);
		setStyleName("myBackgroundImage");

		setSize("100%", "");

		final VerticalPanel centerVerticalPanel = new VerticalPanel();
		centerVerticalPanel
		.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
		centerVerticalPanel
		.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		add(centerVerticalPanel);
		centerVerticalPanel.setSize("100%", "80%");

		final DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.setStyleName("");
		centerVerticalPanel.add(decoratorPanel);
		centerVerticalPanel.setCellWidth(decoratorPanel, "100%");
		decoratorPanel.setSize("", "");
		centerVerticalPanel.setCellHorizontalAlignment(decoratorPanel,
				HasHorizontalAlignment.ALIGN_CENTER);
		centerVerticalPanel.setCellVerticalAlignment(decoratorPanel,
				HasVerticalAlignment.ALIGN_MIDDLE);

		dockPanel = new DockPanel();
		dockPanel.setStyleName("opacity6");
		dockPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		dockPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		decoratorPanel.setWidget(dockPanel);
		dockPanel.setSize("100%", "198px");

		final VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setStyleName("blackBackground");
		dockPanel.add(verticalPanel, DockPanel.NORTH);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel
		.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		dockPanel.setCellWidth(verticalPanel, "100%");
		dockPanel.setCellVerticalAlignment(verticalPanel,
				HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setSize("300px", "90px");

		HTML appvetLogoLoginHtml = new HTML(
				"<img alt=\"Login logo\" width=\"300px\" height=\"90px\"src=\"images/daiml_login.png\">",
				true);
		appvetLogoLoginHtml.setStyleName("");
		appvetLogoLoginHtml.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(appvetLogoLoginHtml);
		verticalPanel.setCellVerticalAlignment(appvetLogoLoginHtml,
				HasVerticalAlignment.ALIGN_MIDDLE);
		appvetLogoLoginHtml.setSize("300px", "90px");

		grid = new Grid(3, 2);
		grid.setStyleName("loginGrid");
		dockPanel.add(grid, DockPanel.CENTER);
		grid.setSize("300px", "68px");
		dockPanel.setCellVerticalAlignment(grid,
				HasVerticalAlignment.ALIGN_BOTTOM);
		dockPanel.setCellHorizontalAlignment(grid,
				HasHorizontalAlignment.ALIGN_CENTER);

		Label usernameLabel = new Label("USERNAME: ");
		usernameLabel.setStyleName("boldFont");
		usernameLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		grid.setWidget(1, 0, usernameLabel);
		grid.getCellFormatter().setStyleName(1, 0, "fontWeight");
		usernameLabel.setSize("90px", "12px");
		userNameTextBox.setAlignment(TextAlignment.LEFT);
		userNameTextBox.setStyleName("gwt-TextBox opacity1");
		grid.setWidget(1, 1, userNameTextBox);
		userNameTextBox.setSize("170px", "12px");
		userNameTextBox.setText(username);

		final Label passwordLabel = new Label("PASSWORD: ");
		passwordLabel.setStyleName("boldFont");
		passwordLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		grid.setWidget(2, 0, passwordLabel);
		passwordLabel.setWidth("90px");

		grid.setWidget(2, 1, passwordTextBox);
		passwordTextBox.setSize("170px", "12px");
		passwordTextBox.setText(password);
		grid.getCellFormatter().setVerticalAlignment(2, 1,
				HasVerticalAlignment.ALIGN_MIDDLE);
		grid.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		grid.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		grid.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
		grid.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_CENTER);
		grid.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		grid.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		grid.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_MIDDLE);

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		dockPanel.add(verticalPanel_1, DockPanel.SOUTH);
		verticalPanel_1.setHeight("43px");

		progressHtml = new HTML(
				"<div style=\"background-color:black;\"><div style=\"background-color:#00ff00;height:5px;width:0%\"></div></div>");
		verticalPanel.add(progressHtml);

		progressHtml.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setCellVerticalAlignment(progressHtml, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setCellHorizontalAlignment(progressHtml, HasHorizontalAlignment.ALIGN_CENTER);
		progressHtml.setSize("300px", "5px");

		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		horizontalPanel_2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel_1.add(horizontalPanel_2);
		horizontalPanel_2.setHeight("30px");
		verticalPanel_1.setCellVerticalAlignment(horizontalPanel_2, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel_1.setCellHorizontalAlignment(horizontalPanel_2, HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel_2.add(loginButton);
		loginButton.setWidth("70px");
		loginButton.setStyleName("greenButton shadow opacity1");
		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doLogin();
			}
		});
		loginButton.setText("LOGIN");
		passwordTextBox.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event_) {
				final boolean enterPressed = KeyCodes.KEY_ENTER == event_
						.getNativeEvent().getKeyCode();
				if (enterPressed) {
					doLogin();
				}
			}
		});
	}

	public void doLogin() {
		loginButton.setEnabled(false);
		progressHtml.setHTML("<div style=\"background-color:black;\"><div style=\"background-color:#00ff00;height:5px;width:25%\"></div></div>");

		final String userName = userNameTextBox.getText();
		final String password = passwordTextBox.getText();

		authenticate(userName, password);
	}

	public void authenticate(final String username, final String password) {	
		

		progressHtml.setHTML("<div style=\"background-color:black;\"><div style=\"background-color:#00ff00;height:4px;width:50%\"></div></div>");

		daimlService.authenticate(username, password, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				log.severe("Authenticate failed");
				showMessageDialog("Login Error",
						"System is currently unavailable. Try again later.", true);
				loginButton.setEnabled(true);
				userNameTextBox.setEnabled(true);
				passwordTextBox.setEnabled(true);
				return;
			}

			@Override
			public void onSuccess(final Boolean result) {
				if (result == false) {
					log.warning("Unknown username or password");
					showMessageDialog("Login Error",
							"Unknown username or password", true);
					return;
				} else {
					progressHtml.setHTML("<div style=\"background-color:black;\"><div style=\"background-color:#00ff00;height:4px;width:75%\"></div></div>");
					displayDaiml();
				}

			}

		});
	}

	public void displayDaiml() {
		final DaimlPanel appVetPanel = new DaimlPanel();

		final RootLayoutPanel rootLayoutPanel = RootLayoutPanel
				.get();

		rootLayoutPanel.clear();

		rootLayoutPanel.add(appVetPanel);
	}

	private void showMessageDialog(String windowTitle, String message, boolean isError) {
		messageDialogBox = new MessageDialogBox(message, isError);
		messageDialogBox.setText(windowTitle);
		messageDialogBox.center();
		messageDialogBox.closeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				userNameTextBox.setText("");
				passwordTextBox.setText("");
				messageDialogBox.hide();
				messageDialogBox = null;
				progressHtml.setHTML("<div style=\"padding:6px;\"> </div>");
				loginButton.setEnabled(true);
			}
		});
	}

}
