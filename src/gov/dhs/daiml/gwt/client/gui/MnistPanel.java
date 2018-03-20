package gov.dhs.daiml.gwt.client.gui;

import com.google.gwt.dom.client.ImageElement;
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
import com.google.gwt.user.client.ui.VerticalPanel;

import gov.dhs.daiml.gwt.client.GWTService;
import gov.dhs.daiml.gwt.client.GWTServiceAsync;
import gov.dhs.daiml.gwt.client.gui.dialog.MessageDialogBox;

import java.util.logging.Logger;

import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class MnistPanel extends DockLayoutPanel {

	// Generate training data from UI-drawn digits
	private boolean GENERATE_TRAINING_DATA = false;

	private final Logger log = Logger.getLogger("MnistPanel");
	private gov.dhs.daiml.gwt.client.gui.dialog.MessageDialogBox messageDialogBox = null; 
	private final GWTServiceAsync daimlService = GWT.create(GWTService.class);

	public MnistPanel() {
		super(Unit.PX);

		//final SimplePanel housePricePredictionPanel = new SimplePanel();
		this.setStyleName("housePricePanel");

		// Panel
		final VerticalPanel mnistDemoPanel = new VerticalPanel();
		mnistDemoPanel.setStyleName("blackBackground");
		Label lblMnisthoai = new Label("MNIST (H2O.ai)");
		lblMnisthoai.setStyleName("demoTitle");
		mnistDemoPanel.add(lblMnisthoai);

		VerticalPanel sourcePanel = new VerticalPanel();

		HTMLPanel panel_1 = new HTMLPanel("<a href=\"https://github.com/AppVet/H2O.ai\">H2O-Generated Java (https://github.com/AppVet/H2O.ai)</a>");
		panel_1.setStyleName("githubUrl");
		sourcePanel.add(panel_1);
		panel_1.setWidth("100%");
		sourcePanel.setCellVerticalAlignment(panel_1, HasVerticalAlignment.ALIGN_MIDDLE);
		mnistDemoPanel.setCellVerticalAlignment(panel_1, HasVerticalAlignment.ALIGN_MIDDLE);

		Label mnistPredict = new Label("https://github.com/AppVet/mnist");
		mnistPredict.setStyleName("mnistURL");
		mnistDemoPanel.add(mnistPredict);

		Label trainingModeLabel = new Label("Training ON");
		//		if (GENERATE_TRAINING_DATA) {
		//			trainingModeLabel.setStyleName("redLabel");
		//			mnistDemoPanel.add(trainingModeLabel);
		//		}

		HorizontalPanel digitPanel = new HorizontalPanel();
		mnistDemoPanel.add(digitPanel);
		mnistDemoPanel.setCellWidth(digitPanel, "600px");
		mnistDemoPanel.setCellVerticalAlignment(digitPanel, HasVerticalAlignment.ALIGN_MIDDLE);
		digitPanel.setWidth("524px");

		Image image = new Image("images/num0-9.png");
		image.setStyleName("mnist-image");
		digitPanel.add(image);
		image.setSize("", "350px");

		VerticalPanel verticalPanel = new VerticalPanel();
		digitPanel.add(verticalPanel);
		digitPanel.setCellVerticalAlignment(verticalPanel, HasVerticalAlignment.ALIGN_MIDDLE);
		digitPanel.setCellWidth(verticalPanel, "600px");
		verticalPanel.setWidth("320px");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setWidth("100%");
		verticalPanel.setCellVerticalAlignment(horizontalPanel, HasVerticalAlignment.ALIGN_MIDDLE);

		Label lblNewLabel = new Label("Using a mouse, draw a digit below.");
		horizontalPanel.add(lblNewLabel);
		lblNewLabel.setWidth("");
		horizontalPanel.setCellVerticalAlignment(lblNewLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		lblNewLabel.setStyleName("mnistDirectionsLabel");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		PushButton clearButton = new PushButton("Clear");

		clearButton.setStyleName("clearButton");
		horizontalPanel.add(clearButton);
		horizontalPanel.setCellVerticalAlignment(clearButton, HasVerticalAlignment.ALIGN_MIDDLE);
		clearButton.setSize("70px", "");
		horizontalPanel.setCellHorizontalAlignment(clearButton, HasHorizontalAlignment.ALIGN_CENTER);

		final DrawPanel drawPanel = new DrawPanel();
		drawPanel.setStyleName("mnistCanvas");
		verticalPanel.add(drawPanel);
		drawPanel.setSize("308px", "308px");

		final Label resultPanel = new Label("");

		clearButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				drawPanel.initCanvas();
				resultPanel.setText("");
			}
		});

		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		mnistDemoPanel.add(horizontalPanel_1);
		mnistDemoPanel.setCellHeight(horizontalPanel_1, "100%");
		horizontalPanel_1.setHeight("100%");
		mnistDemoPanel.setCellVerticalAlignment(horizontalPanel_1, HasVerticalAlignment.ALIGN_MIDDLE);



		Button submitButton = new Button("Submit");
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Clear frame before scaling image
				drawPanel.clearFrame();
				
				//ImageData canvasImageData = drawPanel.context.getImageData(0, 0, 308, 308);
				//log.info("Submit selected");
				ImageData scaledImage = scaleImage(drawPanel, 0.09);
				//log.info("Scaled image: " + scaledImage.getWidth() + ", " + scaledImage.getHeight());
				String[] imageArray = getImageArray(scaledImage);
				//log.info("imageArray defined");

				// Submit criteria and get predictions
				daimlService.getDigit(imageArray, 
						new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						//log.severe("Prediction failed");
						showMessageDialog("Prediction Error",
								"An error occurred predicting the price.", true);
						return;
					}

					@Override
					public void onSuccess(final String result) {
						//log.info("Authentication successful");
						resultPanel.setText("Result: " + result.toString());
						
						drawPanel.initCanvas();
					}
				});
			}
		});
		submitButton.setText("SUBMIT");
		submitButton.setStyleName("bigBlueButton");
		horizontalPanel_1.add(submitButton);
		submitButton.setSize("100px", "38px");
		horizontalPanel_1.setCellVerticalAlignment(submitButton, HasVerticalAlignment.ALIGN_MIDDLE);

		resultPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		horizontalPanel_1.add(resultPanel);
		resultPanel.setSize("300px", "");
		horizontalPanel_1.setCellVerticalAlignment(resultPanel, HasVerticalAlignment.ALIGN_MIDDLE);
		resultPanel.setStyleName("resultLabel");

		final DrawPanel drawCanvas = new DrawPanel();
		drawCanvas.setStyleName("blackBackground");
		mnistDemoPanel.add(horizontalPanel_1);


		this.add(mnistDemoPanel);
		mnistDemoPanel.setWidth("700px");
	}

	private String[] getImageArray(ImageData imageData) {
		//log.info("imageData.getHeight(): " + imageData.getHeight());
		//log.info("imageData.getWidth(): " + imageData.getWidth());
		
		//log.info("trace 10");

		String[] imageArray = new String[imageData.getHeight() * imageData.getWidth()];
		int arrayIndex = 0;
		//log.info("trace 11");

		// For generating training data
		StringBuffer strbuf = new StringBuffer();

		for (int y=0; y < imageData.getHeight(); y++) {
			for (int x=0; x < imageData.getWidth(); x++) {

				int a = imageData.getAlphaAt(x, y);
				int r = imageData.getRedAt(x, y);  // Not used for grayscale
				int g = imageData.getGreenAt(x, y); // Not used for grayscale
				int b = imageData.getBlueAt(x, y);  // Not used for grayscale

				//				if (a != 0 || r != 0 || g != 0 || b != 0) {
				//					//log.info("Color at " + x + ", " + y + ": " + a + ", " + r + ", " + g + ", " + b);
				//					
				//					//imageData.setRedAt(255, x, y);
				//				}

				// Only using grayscale, so use alpha value
				//log.info("alpha: " + a);
				if (a > 0) {
					a = 255; // Set all grayscale to black
				}
				String alphaStr = Integer.toString(a);
				if (GENERATE_TRAINING_DATA) {
					strbuf.append(alphaStr + " ");
				}
				imageArray[arrayIndex] = alphaStr;
				arrayIndex++;

			}
		}
		log.info("trace 12");

		// Generate training data to the browser console (and manually copy to 
		// text file)
		if (GENERATE_TRAINING_DATA) {
			log.info(strbuf.toString());
		}
		log.info("trace 13");

		log.info("imageArray.length: " + imageArray.length);
		log.info("trace 14");

		return imageArray;
	}

	private ImageData scaleImage(DrawPanel drawPanel, double scaleToRatio) {

		//log.info("trace 1");
		Image image = new Image(drawPanel.canvas.toDataUrl());
		//log.info("trace 2");

		//Canvas canvasTmp = Canvas.createIfSupported();
		//Context2d context = canvasTmp.getContext2d();

		double ch = (image.getHeight() * scaleToRatio) + 100;
		double cw = (image.getWidth() * scaleToRatio) + 100;
		//log.info("trace 3");

		//canvasTmp.setCoordinateSpaceHeight((int) ch);
		drawPanel.canvas.setCoordinateSpaceHeight((int) ch);
		//canvasTmp.setCoordinateSpaceWidth((int) cw);
		drawPanel.canvas.setCoordinateSpaceWidth((int) cw);

		//log.info("trace 4");

		ImageElement imageElement = ImageElement.as(image.getElement());
		//log.info("trace 5");

		// s = source
		// d = destination 
		double sx = 0;
		double sy = 0;
		
		//double sw = imageElement.getWidth();
		//double sh = imageElement.getHeight();
		double sw = 308;
		double sh = 308;

		double dx = 0;
		double dy = 0;
		//double dw = imageElement.getWidth();
		//double dh = imageElement.getHeight();
		double dw = 308;
		double dh = 308;
		
		//log.info("trace 6");

		// tell it to scale image
		//context.scale(scaleToRatio, scaleToRatio);
		drawPanel.context.scale(scaleToRatio, scaleToRatio);
		//log.info("trace 7");

		// draw image to canvas
		//context.drawImage(imageElement, sx, sy, sw, sh, dx, dy, dw, dh);
		drawPanel.context.drawImage(imageElement, sx, sy, sw, sh, dx, dy, dw, dh);
		//log.info("trace 8");

		// get image data
		//log.info("w = " + dw + " * " + scaleToRatio);
		//log.info("h = " + dh + " * " + scaleToRatio);

		double w = dw * scaleToRatio;
		double h = dh * scaleToRatio;
		//log.info("w: " + w);
		//log.info("h: " + h);
		//ImageData imageData = context.getImageData(0, 0, w, h);
		ImageData imageData = drawPanel.context.getImageData(0, 0, w, h);
		//log.info("trace 9");

		return imageData;
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
