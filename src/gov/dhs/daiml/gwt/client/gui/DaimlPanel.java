package gov.dhs.daiml.gwt.client.gui;


import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import gov.dhs.daiml.gwt.client.gui.dialog.MessageDialogBox;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class DaimlPanel extends DockLayoutPanel {

	private final Logger log = Logger.getLogger("HousePricePredictionPanel");
	private gov.dhs.daiml.gwt.client.gui.dialog.MessageDialogBox messageDialogBox = null; 

	public DaimlPanel() {
		super(Unit.PX);
		//log.info("Starting DAIML panel");
		setStyleName("blackPanel");
		setSize("", "");

		final HousePricePredictionPanel housePricePredictionPanel = new HousePricePredictionPanel();
		final MnistPanel mnistPanel = new MnistPanel();
		mnistPanel.setStyleName("blackBackground");
		
		final VerticalPanel mainVerticalPanel = new VerticalPanel();
		mainVerticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		add(mainVerticalPanel);
		mainVerticalPanel.setSize("100%", "");

		HTMLPanel logoPanel = new HTMLPanel("<img alt=\"Login logo\" width=\"300px\" height=\"90px\"src=\"images/daiml_login.png\">");
		logoPanel.setStyleName("blackBackground");
		mainVerticalPanel.add(logoPanel);
		logoPanel.setWidth("100%");
		mainVerticalPanel.setCellVerticalAlignment(logoPanel, HasVerticalAlignment.ALIGN_MIDDLE);

		HorizontalPanel demoSelectPanel = new HorizontalPanel();
		mainVerticalPanel.add(demoSelectPanel);
		mainVerticalPanel.setCellWidth(demoSelectPanel, "100%");
		mainVerticalPanel.setCellVerticalAlignment(demoSelectPanel, HasVerticalAlignment.ALIGN_MIDDLE);
		demoSelectPanel.setStyleName("grayPanel");
		demoSelectPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		demoSelectPanel.setSize("100%", "40px");

		Label lblNewLabel_1 = new Label(" Demo: ");
		lblNewLabel_1.setStyleName("demo-Label");
		demoSelectPanel.add(lblNewLabel_1);
		demoSelectPanel.setCellWidth(lblNewLabel_1, "80px");
		demoSelectPanel.setCellVerticalAlignment(lblNewLabel_1, HasVerticalAlignment.ALIGN_MIDDLE);
		lblNewLabel_1.setWidth("80px");
		
		final ListBox comboBox = new ListBox();
		comboBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				int selectedDemo = comboBox.getSelectedIndex();
				if (selectedDemo == 0) {
					mainVerticalPanel.remove(2);
					mainVerticalPanel.add(housePricePredictionPanel);
					mainVerticalPanel.setCellWidth(housePricePredictionPanel, "100%");
					mainVerticalPanel.setCellHeight(housePricePredictionPanel, "100%");
					mnistPanel.setSize("700px", "600px");
				} else {
					mainVerticalPanel.remove(2);
					mainVerticalPanel.add(mnistPanel);
					mainVerticalPanel.setCellWidth(mnistPanel, "100%");
					mainVerticalPanel.setCellHeight(mnistPanel, "100%");
					mnistPanel.setSize("700px", "600px");
				}
			}
		});
		comboBox.setStyleName("demoListBox");
		demoSelectPanel.add(comboBox);
		comboBox.setHeight("");
		demoSelectPanel.setCellVerticalAlignment(comboBox, HasVerticalAlignment.ALIGN_MIDDLE);
		comboBox.addItem("Home Price Prediction");
		comboBox.addItem("MNIST");

		mainVerticalPanel.add(housePricePredictionPanel);
		mainVerticalPanel.setCellWidth(housePricePredictionPanel, "100%");
		housePricePredictionPanel.setHeight("550px");
		
		//log.info("main panel elements: " + mainVerticalPanel.getWidgetIndex(housePricePredictionPanel));

	}

	public boolean isInt(String s) {
		if (s == null || s.isEmpty() || !isNumeric(s) || s.indexOf(".") > -1) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isFloat(String s) {
		if (s == null || s.isEmpty() || !isNumeric(s)) {
			return false;
		} else if (s.indexOf(".") > -1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBoolean(String s) {
		if (s == null || s.isEmpty() || (!s.equals("yes") && !s.equals("no"))) {
			return false;
		} else {
			return false;
		}
	}

	public boolean isNumeric(String s) {
		return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}

	public void showMessageDialog(String windowTitle, String message,
			boolean isError) {
		messageDialogBox = new MessageDialogBox(message, isError);
		messageDialogBox.setText(windowTitle);
		messageDialogBox.center();
		messageDialogBox.closeButton.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				killDialogBox(messageDialogBox);
			}
		});
	}

	public void killDialogBox(DialogBox dialogBox) {
		if (dialogBox != null) {
			dialogBox.hide();
			dialogBox = null;
		}
	}

}
