package gov.dhs.daiml.gwt.client.gui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;

import gov.dhs.daiml.gwt.client.GWTService;
import gov.dhs.daiml.gwt.client.GWTServiceAsync;
import gov.dhs.daiml.gwt.client.gui.dialog.MessageDialogBox;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HousePricePredictionPanel extends DockLayoutPanel {
	
	private final Logger log = Logger.getLogger("HousePricePredictionPanel");
	private gov.dhs.daiml.gwt.client.gui.dialog.MessageDialogBox messageDialogBox = null; 
	private final GWTServiceAsync daimlService = GWT.create(GWTService.class);

	public HousePricePredictionPanel() {
		super(Unit.PX);
		setSize("", "100%");
		
		//final SimplePanel housePricePredictionPanel = new SimplePanel();
		this.setStyleName("housePricePanel");

		// Panel
		final VerticalPanel housingDemoPanel = new VerticalPanel();
		housingDemoPanel.setStyleName("blackBackground");
		Label label = new Label("Housing Price Prediction (H2O.ai)");
		label.setStyleName("demoTitle");
		housingDemoPanel.add(label);
		
		VerticalPanel sourcePanel = new VerticalPanel();

		HTMLPanel panel_1 = new HTMLPanel("<a href=\"https://github.com/AppVet/H2O.ai\">H2O-Generated Java (https://github.com/AppVet/H2O.ai)</a>");
		panel_1.setStyleName("githubUrl");
		sourcePanel.add(panel_1);
		panel_1.setWidth("100%");
		sourcePanel.setCellVerticalAlignment(panel_1, HasVerticalAlignment.ALIGN_MIDDLE);
		housingDemoPanel.setCellVerticalAlignment(panel_1, HasVerticalAlignment.ALIGN_MIDDLE);
		
		Label housePriceUrl = new Label("https://github.com/AppVet/housepricepredict");
		housePriceUrl.setStyleName("housePriceUrl");
		housingDemoPanel.add(housePriceUrl);
		
		HorizontalPanel sqftPanel = new HorizontalPanel();
		housingDemoPanel.add(sqftPanel);
		housingDemoPanel.setCellVerticalAlignment(sqftPanel, HasVerticalAlignment.ALIGN_MIDDLE);
		sqftPanel.setWidth("100%");
		
		Label lblNewLabel_2 = new Label("SQFT: ");
		lblNewLabel_2.setStyleName("col-label");
		sqftPanel.add(lblNewLabel_2);
		lblNewLabel_2.setWidth("160px");
		sqftPanel.setCellVerticalAlignment(lblNewLabel_2, HasVerticalAlignment.ALIGN_MIDDLE);
		
		final TextBox sqftTextBox = new TextBox();
		sqftTextBox.setStyleName("textBoxes");
		sqftTextBox.setText("2342");
		sqftTextBox.setTextAlignment(TextBoxBase.ALIGN_RIGHT);
		sqftPanel.add(sqftTextBox);
		sqftPanel.setCellHeight(sqftTextBox, "100%");
		sqftTextBox.setSize("224px", "");
		sqftPanel.setCellVerticalAlignment(sqftTextBox, HasVerticalAlignment.ALIGN_MIDDLE);
		
		Label lblNewLabel_4 = new Label("(integer: 1000-5000)");
		sqftPanel.add(lblNewLabel_4);
		sqftPanel.setCellVerticalAlignment(lblNewLabel_4, HasVerticalAlignment.ALIGN_MIDDLE);
		lblNewLabel_4.setWidth("600px");
		
		HorizontalPanel lotSizePanel = new HorizontalPanel();
		housingDemoPanel.add(lotSizePanel);
		housingDemoPanel.setCellVerticalAlignment(lotSizePanel, HasVerticalAlignment.ALIGN_MIDDLE);
		lotSizePanel.setWidth("100%");
		
		Label lblLotSize = new Label("Lot Size: ");
		lblLotSize.setStyleName("col-label");
		lotSizePanel.add(lblLotSize);
		lotSizePanel.setCellVerticalAlignment(lblLotSize, HasVerticalAlignment.ALIGN_MIDDLE);
		lblLotSize.setWidth("160px");
		
		final TextBox lotSizeTextBox = new TextBox();
		lotSizeTextBox.setStyleName("textBoxes");
		lotSizeTextBox.setText("0.4");
		lotSizeTextBox.setTextAlignment(TextBoxBase.ALIGN_RIGHT);
		lotSizePanel.add(lotSizeTextBox);
		lotSizeTextBox.setSize("224px", "");
		
		Label lblNewLabel_3 = new Label("(float: 0.1-1.0)");
		lblNewLabel_3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		lotSizePanel.add(lblNewLabel_3);
		lotSizePanel.setCellVerticalAlignment(lblNewLabel_3, HasVerticalAlignment.ALIGN_MIDDLE);
		lblNewLabel_3.setWidth("600px");
		
		HorizontalPanel storiesPanel = new HorizontalPanel();
		housingDemoPanel.add(storiesPanel);
		storiesPanel.setWidth("100%");
		
		Label lblStories = new Label("Stories: ");
		lblStories.setStyleName("col-label");
		storiesPanel.add(lblStories);
		storiesPanel.setCellVerticalAlignment(lblStories, HasVerticalAlignment.ALIGN_MIDDLE);
		lblStories.setWidth("160px");
		
		final TextBox storiesTextBox = new TextBox();
		storiesTextBox.setStyleName("textBoxes");
		storiesTextBox.setText("1");
		storiesTextBox.setTextAlignment(TextBoxBase.ALIGN_RIGHT);
		storiesPanel.add(storiesTextBox);
		storiesTextBox.setSize("224px", "");
		
		Label lblNewLabel_5 = new Label("(integer, 1-3)");
		storiesPanel.add(lblNewLabel_5);
		storiesPanel.setCellVerticalAlignment(lblNewLabel_5, HasVerticalAlignment.ALIGN_MIDDLE);
		lblNewLabel_5.setWidth("600px");
		
		HorizontalPanel bedroomsPanel = new HorizontalPanel();
		housingDemoPanel.add(bedroomsPanel);
		bedroomsPanel.setWidth("100%");
		
		Label lblNoBedrooms = new Label("Bedrooms: ");
		lblNoBedrooms.setStyleName("col-label");
		bedroomsPanel.add(lblNoBedrooms);
		lblNoBedrooms.setWidth("160px");
		
		final TextBox bedRoomsTextBox = new TextBox();
		bedRoomsTextBox.setStyleName("textBoxes");
		bedRoomsTextBox.setText("2");
		bedRoomsTextBox.setTextAlignment(TextBoxBase.ALIGN_RIGHT);
		bedroomsPanel.add(bedRoomsTextBox);
		bedRoomsTextBox.setSize("224px", "");
		
		Label lblNewLabel_6 = new Label("(integer, 1-5)");
		bedroomsPanel.add(lblNewLabel_6);
		lblNewLabel_6.setWidth("600px");
		bedroomsPanel.setCellVerticalAlignment(lblNewLabel_6, HasVerticalAlignment.ALIGN_MIDDLE);
		
		HorizontalPanel bathroomsPanel = new HorizontalPanel();
		housingDemoPanel.add(bathroomsPanel);
		bathroomsPanel.setWidth("100%");
		
		Label lblBathrooms = new Label("Bathrooms: ");
		lblBathrooms.setStyleName("col-label");
		bathroomsPanel.add(lblBathrooms);
		bathroomsPanel.setCellVerticalAlignment(lblBathrooms, HasVerticalAlignment.ALIGN_MIDDLE);
		lblBathrooms.setWidth("160px");
		
		final TextBox bathroomsTextBox = new TextBox();
		bathroomsTextBox.setStyleName("textBoxes");
		bathroomsTextBox.setText("2");
		bathroomsTextBox.setTextAlignment(TextBoxBase.ALIGN_RIGHT);
		bathroomsPanel.add(bathroomsTextBox);
		bathroomsTextBox.setSize("224px", "");
		
		Label lblNewLabel_7 = new Label("(integer, 1-6)");
		bathroomsPanel.add(lblNewLabel_7);
		bathroomsPanel.setCellVerticalAlignment(lblNewLabel_7, HasVerticalAlignment.ALIGN_MIDDLE);
		lblNewLabel_7.setWidth("600px");
		
		HorizontalPanel garagePanel = new HorizontalPanel();
		housingDemoPanel.add(garagePanel);
		housingDemoPanel.setCellVerticalAlignment(garagePanel, HasVerticalAlignment.ALIGN_MIDDLE);
		garagePanel.setWidth("100%");
		
		Label lblGarage = new Label("Garage: ");
		lblGarage.setStyleName("col-label");
		garagePanel.add(lblGarage);
		garagePanel.setCellVerticalAlignment(lblGarage, HasVerticalAlignment.ALIGN_MIDDLE);
		lblGarage.setWidth("160px");
		
		final ListBox garageComboBox = new ListBox();
		garageComboBox.setStyleName("textBoxes");
		garageComboBox.addItem("yes");
		garageComboBox.addItem("no");
		garageComboBox.setMultipleSelect(false);
		garagePanel.add(garageComboBox);
		garagePanel.setCellVerticalAlignment(garageComboBox, HasVerticalAlignment.ALIGN_MIDDLE);
		garageComboBox.setSize("228px", "");
		
		Label lblNewLabel_8 = new Label("(boolean, t/f)");
		garagePanel.add(lblNewLabel_8);
		lblNewLabel_8.setWidth("600px");
		garagePanel.setCellVerticalAlignment(lblNewLabel_8, HasVerticalAlignment.ALIGN_MIDDLE);
		
		HorizontalPanel poolPanel = new HorizontalPanel();
		housingDemoPanel.add(poolPanel);
		poolPanel.setWidth("100%");
		
		Label lblPool = new Label("Pool: ");
		lblPool.setStyleName("col-label");
		poolPanel.add(lblPool);
		poolPanel.setCellVerticalAlignment(lblPool, HasVerticalAlignment.ALIGN_MIDDLE);
		lblPool.setWidth("160px");
		
		final ListBox poolComboBox = new ListBox();
		poolComboBox.setStyleName("textBoxes");
		poolComboBox.addItem("yes");
		poolComboBox.addItem("no");
		poolComboBox.setMultipleSelect(false);
		poolPanel.add(poolComboBox);
		poolPanel.setCellVerticalAlignment(poolComboBox, HasVerticalAlignment.ALIGN_MIDDLE);
		poolComboBox.setSize("228px", "");
		
		Label lblNewLabel_9 = new Label("(boolean, y/n)");
		poolPanel.add(lblNewLabel_9);
		lblNewLabel_9.setWidth("600px");
		poolPanel.setCellVerticalAlignment(lblNewLabel_9, HasVerticalAlignment.ALIGN_MIDDLE);
		
		HorizontalPanel islandPanel = new HorizontalPanel();
		housingDemoPanel.add(islandPanel);
		islandPanel.setWidth("100%");
		
		Label lblKitchenIsland = new Label("Kitchen Island: ");
		lblKitchenIsland.setStyleName("col-label");
		islandPanel.add(lblKitchenIsland);
		islandPanel.setCellVerticalAlignment(lblKitchenIsland, HasVerticalAlignment.ALIGN_MIDDLE);
		lblKitchenIsland.setWidth("160px");
		
		final ListBox islandComboBox = new ListBox();
		islandComboBox.setStyleName("textBoxes");
		islandComboBox.addItem("yes");
		islandComboBox.addItem("no");
		islandComboBox.setMultipleSelect(false);
		islandPanel.add(islandComboBox);
		islandPanel.setCellVerticalAlignment(islandComboBox, HasVerticalAlignment.ALIGN_MIDDLE);
		islandComboBox.setSize("228px", "");
		
		Label lblNewLabel_10 = new Label("(boolean, y/n)");
		islandPanel.add(lblNewLabel_10);
		lblNewLabel_10.setWidth("600px");
		islandPanel.setCellVerticalAlignment(lblNewLabel_10, HasVerticalAlignment.ALIGN_MIDDLE);
		
		HorizontalPanel flooringPanel = new HorizontalPanel();
		housingDemoPanel.add(flooringPanel);
		flooringPanel.setWidth("100%");
		
		Label lblFlooringType = new Label("Flooring Type: ");
		lblFlooringType.setStyleName("col-label");
		flooringPanel.add(lblFlooringType);
		flooringPanel.setCellVerticalAlignment(lblFlooringType, HasVerticalAlignment.ALIGN_MIDDLE);
		lblFlooringType.setWidth("160px");
		
		final ListBox flooringComboBox = new ListBox();
		flooringComboBox.setStyleName("textBoxes");
		flooringComboBox.setMultipleSelect(false);
		flooringComboBox.addItem("carpet");
		flooringComboBox.addItem("tile");
		flooringComboBox.addItem("hardwood");

		flooringPanel.add(flooringComboBox);
		flooringPanel.setCellVerticalAlignment(flooringComboBox, HasVerticalAlignment.ALIGN_MIDDLE);
		flooringComboBox.setSize("228px", "");
		
		Label lblNewLabel_11 = new Label("(enum: {carpet, tile, hardwood})");
		flooringPanel.add(lblNewLabel_11);
		lblNewLabel_11.setWidth("600px");
		flooringPanel.setCellVerticalAlignment(lblNewLabel_11, HasVerticalAlignment.ALIGN_MIDDLE);
		
		HorizontalPanel granitePanel = new HorizontalPanel();
		housingDemoPanel.add(granitePanel);
		granitePanel.setWidth("100%");
		
		Label lblGraniteCounter = new Label("Granite Counter: ");
		lblGraniteCounter.setStyleName("col-label");
		granitePanel.add(lblGraniteCounter);
		granitePanel.setCellVerticalAlignment(lblGraniteCounter, HasVerticalAlignment.ALIGN_MIDDLE);
		lblGraniteCounter.setWidth("160px");
		
		final ListBox graniteComboBox = new ListBox();
		graniteComboBox.setStyleName("textBoxes");
		graniteComboBox.setMultipleSelect(false);
		graniteComboBox.addItem("yes");
		graniteComboBox.addItem("no");
		granitePanel.add(graniteComboBox);
		granitePanel.setCellVerticalAlignment(graniteComboBox, HasVerticalAlignment.ALIGN_MIDDLE);
		graniteComboBox.setSize("228px", "");
		
		Label lblNewLabel_12 = new Label("(boolean, y/n)");
		granitePanel.add(lblNewLabel_12);
		lblNewLabel_12.setWidth("600px");
		granitePanel.setCellVerticalAlignment(lblNewLabel_12, HasVerticalAlignment.ALIGN_MIDDLE);
		
		HorizontalPanel agePanel = new HorizontalPanel();
		agePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		housingDemoPanel.add(agePanel);
		agePanel.setWidth("100%");
		
		Label lblAge = new Label("Age: ");
		lblAge.setStyleName("col-label");
		agePanel.add(lblAge);
		agePanel.setCellVerticalAlignment(lblAge, HasVerticalAlignment.ALIGN_MIDDLE);
		lblAge.setWidth("160px");
		
		final TextBox ageTextBox = new TextBox();
		ageTextBox.setStyleName("textBoxes");
		ageTextBox.setText("0");
		ageTextBox.setTextAlignment(TextBoxBase.ALIGN_RIGHT);
		agePanel.add(ageTextBox);
		ageTextBox.setSize("224px", "");
		
		Label lblNewLabel_13 = new Label("(integer: 0-100)");
		agePanel.add(lblNewLabel_13);
		lblNewLabel_13.setWidth("600px");
		agePanel.setCellVerticalAlignment(lblNewLabel_13, HasVerticalAlignment.ALIGN_MIDDLE);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		housingDemoPanel.add(horizontalPanel_1);
		horizontalPanel_1.setHeight("");
		housingDemoPanel.setCellVerticalAlignment(horizontalPanel_1, HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button btnNewButton = new Button("Generate Estimate");
		btnNewButton.setText("SUBMIT");
		btnNewButton.setStyleName("bigBlueButton");
		horizontalPanel_1.add(btnNewButton);
		btnNewButton.setSize("100px", "40px");
		horizontalPanel_1.setCellVerticalAlignment(btnNewButton, HasVerticalAlignment.ALIGN_MIDDLE);
		
		final Label resultPanel = new Label("Estimated Price: N/A");
		resultPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		horizontalPanel_1.add(resultPanel);
		resultPanel.setSize("300px", "");
		horizontalPanel_1.setCellVerticalAlignment(resultPanel, HasVerticalAlignment.ALIGN_MIDDLE);
		resultPanel.setStyleName("resultLabel");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Validate SQFT
				String sqftStr = sqftTextBox.getText();
				if (!isInt(sqftStr)) {
					showMessageDialog("Error", "SQFT must be between 1000-5000", true);
					return;
				} else {
					int sqft = new Integer(sqftStr).intValue();
					if (sqft < 1000 || sqft > 5000) {
						// show Error
						showMessageDialog("Error", "SQFT must be between 1000-5000", true);
						return;
					}	
				}
				
				// Validate Lot Size
				String lotSizeStr = lotSizeTextBox.getText();
				if (!isFloat(lotSizeStr)) {
					showMessageDialog("Error", "Lot size must be between 0.1-1.0", true);
					return;
				} else {
					float lotSize = new Float(lotSizeStr).floatValue();
					if (lotSize < 0.1 || lotSize > 1.0) {
						// show Error
						showMessageDialog("Error", "Lot size must be between 0.1-1.0", true);
						return;
					}	
				}
				
				// Validate Stories
				String storiesStr = storiesTextBox.getText();
				if (!isInt(storiesStr)) {
					showMessageDialog("Error", "Stories must be between 1-3", true);
					return;
				} else {
					int stories = new Integer(storiesStr).intValue();
					if (stories < 1 || stories > 3) {
						// show Error
						showMessageDialog("Error", "Stories must be between 1-3", true);
						return;
					}	
				}
				
				// Validate Bedrooms
				String bedroomsStr = bedRoomsTextBox.getText();
				if (!isInt(bedroomsStr)) {
					showMessageDialog("Error", "Bedrooms must be between 1-5", true);
					return;
				} else {
					int bedrooms = new Integer(bedroomsStr).intValue();
					if (bedrooms < 1 || bedrooms > 5) {
						// show Error
						showMessageDialog("Error", "Bedrooms must be between 1-5", true);
						return;
					}	
				}
				
				// Validate Bathrooms
				String bathroomsStr = bathroomsTextBox.getText();
				if (!isInt(bathroomsStr)) {
					showMessageDialog("Error", "Bathrooms must be between 1-3", true);
					return;
				} else {
					int bathrooms = new Integer(bathroomsStr).intValue();
					if (bathrooms < 1 || bathrooms > 3) {
						// show Error
						showMessageDialog("Error", "Bathrooms must be between 1-3", true);
						return;
					}	
				}
				
				// Get Garage
				int selectedGarage = garageComboBox.getSelectedIndex();
				String selectedGarageText = garageComboBox.getItemText(selectedGarage);
				boolean garage = new Boolean(selectedGarageText).booleanValue();


				// Get Pool
				int selectedPool = poolComboBox.getSelectedIndex();
				String selectedPoolText = poolComboBox.getItemText(selectedPool);
				boolean pool = new Boolean(selectedPoolText).booleanValue();


				// Get Island
				int selectedIsland = islandComboBox.getSelectedIndex();
				String selectedIslandText = islandComboBox.getItemText(selectedIsland);
				boolean island = new Boolean(selectedIslandText).booleanValue();


				// Get Flooring
				int selectedFlooring = flooringComboBox.getSelectedIndex();
				String selectedFloordingText = flooringComboBox.getItemText(selectedFlooring);

				// Get Granite
				int selectedGranite = graniteComboBox.getSelectedIndex();
				String selectedGraniteText = graniteComboBox.getItemText(selectedGranite);
				
				// Validate Age
				String ageStr = ageTextBox.getText();
				if (!isInt(ageStr)) {
					showMessageDialog("Error", "Age must be between 0-100", true);
					return;
				} else {
					int age = new Integer(ageStr).intValue();
					if (age < 0 || age > 100) {
						// show Error
						showMessageDialog("Error", "Age must be between 0-100", true);
						return;
					}	
				}
				
				// Submit criteria and get predictions
				daimlService.predictPrice(sqftStr, lotSizeStr, storiesStr, 
						bedroomsStr, bathroomsStr, selectedGarageText, selectedPoolText, 
						selectedIslandText, selectedFloordingText, selectedGraniteText, ageStr, 
						new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						log.severe("Prediction failed");
						showMessageDialog("Prediction Error",
								"An error occurred predicting the price.", true);
						return;
					}

					@Override
					public void onSuccess(final String result) {
						resultPanel.setText("Estimated Price: " + result.toString());
					}

				});
			}
		});
		
		this.add(housingDemoPanel);
		housingDemoPanel.setHeight("");
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
