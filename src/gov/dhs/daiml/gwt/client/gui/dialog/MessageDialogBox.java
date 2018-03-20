/* This software was developed by employees of the National Institute of
 * Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 15 United States Code Section 105, works of NIST
 * employees are not subject to copyright protection in the United States
 * and are considered to be in the public domain.  As a result, a formal
 * license is not needed to use the software.
 * 
 * This software is provided by NIST as a service and is expressly
 * provided "AS IS".  NIST MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED
 * OR STATUTORY, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT
 * AND DATA ACCURACY.  NIST does not warrant or make any representations
 * regarding the use of the software or the results thereof including, but
 * not limited to, the correctness, accuracy, reliability or usefulness of
 * the software.
 * 
 * Permission to use this software is contingent upon your acceptance
 * of the terms of this agreement.
 */
package gov.dhs.daiml.gwt.client.gui.dialog;

import com.google.gwt.aria.client.Roles;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author steveq@nist.gov
 */
public class MessageDialogBox extends DialogBox {
	public PushButton closeButton;
	//public Label messageLabel = null;
	private HTML messageLabel_1;

	public MessageDialogBox(String message, boolean error) {
		super(false, true);
		setWidth("335px");
		setAnimationEnabled(false);
		final VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.setWidget(dialogVPanel);
		dialogVPanel.setSize("307px", "132px");
		messageLabel_1 = new HTML(message);
		dialogVPanel.add(messageLabel_1);
		dialogVPanel.setCellVerticalAlignment(messageLabel_1, HasVerticalAlignment.ALIGN_BOTTOM);
		messageLabel_1.setHeight("");
		if (error) {
			messageLabel_1.setStyleName("errorMessageDialog");
		} else {
			messageLabel_1.setStyleName("messageDialog");
		}
		messageLabel_1
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		dialogVPanel.setCellHorizontalAlignment(messageLabel_1,
				HasHorizontalAlignment.ALIGN_CENTER);
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("buttonPanel");
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		dialogVPanel.add(horizontalPanel);
		dialogVPanel.setCellHorizontalAlignment(horizontalPanel,
				HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel.setSize("320px", "49px");
		closeButton = new PushButton("Ok");
		Roles.getButtonRole().setAriaLabelProperty(closeButton.getElement(),
				"Close Button");

		//closeButton.setTitle("Close");
		closeButton.setStyleName("greenButton shadow");
		horizontalPanel.add(closeButton);
		horizontalPanel.setCellVerticalAlignment(closeButton, HasVerticalAlignment.ALIGN_BOTTOM);
		dialogVPanel.setCellWidth(closeButton, "100%");
		closeButton.setSize("70px", "18px");
		dialogVPanel.setCellVerticalAlignment(closeButton,
				HasVerticalAlignment.ALIGN_MIDDLE);
		dialogVPanel.setCellHorizontalAlignment(closeButton,
				HasHorizontalAlignment.ALIGN_CENTER);
	}

	/** This fixes focus for dialog boxes in Firefox and IE browsers */
	@Override
	public void show() {
		super.show();
		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				closeButton.setFocus(true);
			}
		});
	}
}
