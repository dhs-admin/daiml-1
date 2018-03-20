package gov.dhs.daiml.gwt.client.gui;

import java.util.logging.Logger;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.event.dom.client.MouseDownEvent;

public class DrawPanel extends SimplePanel {

	private final Logger log = Logger.getLogger("DrawPanel");
	public Canvas canvas = null;
	public boolean mouseDown = false;
	public Context2d context = null;
	public int brushWidth = 20;
	public int brushHeight = 20;


	public DrawPanel() {
		canvas = Canvas.createIfSupported();
		if (canvas == null) {
			log.severe("Canvas could not be created for this browser. Try another browser.");
		}
		
		context = canvas.getContext2d();
		initCanvas();

		add(canvas);
	}
	
	public void initCanvas() {
		mouseDown = false;

		canvas.setCoordinateSpaceHeight(308);
		canvas.setCoordinateSpaceWidth(308);
		canvas.setSize("308px", "308px");
		
		context.clearRect(0, 0, 308, 308);
		
		// Draw frame 
		drawFrame();
		
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event)
			{
				//log.info("mouse move: " + event.getX() + ", " + event.getY());
				if(mouseDown)
				{
					//log.info("Mouse move");
					context.fillRect(event.getX() - canvas.getAbsoluteLeft() + 360, 
							event.getY(), brushWidth, brushHeight);
				}
			}
		});
		canvas.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event)
			{
				//log.info("mouse down: " + event.getX() +  ", " + event.getY());
				mouseDown = true;
			}
		});
		canvas.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event)
			{
				//log.info("mouse up: " + event.getX() +  ", " + event.getY());
				mouseDown = false;
			}
		});
	}
	
	public void drawFrame() {
		context.fillRect(0, 0, 308, 50);   // Top 
		context.fillRect(0, 50, 50, 208);   // Left 
		context.fillRect(258, 50, 50, 208);  // Right
		context.fillRect(0, 258, 308, 50);  // Bottom
	}
	
	public void clearFrame() {
		context.clearRect(0, 0, 308, 50);   // Top 
		context.clearRect(0, 50, 50, 208);   // Left 
		context.clearRect(258, 50, 50, 208);  // Right
		context.clearRect(0, 258, 308, 50);  // Bottom
	}
}
