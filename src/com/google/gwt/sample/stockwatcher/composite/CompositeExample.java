package com.google.gwt.sample.stockwatcher.composite;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CompositeExample implements EntryPoint{

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		OptionalTextBox otb = new OptionalTextBox("Check this to enable me");
		RootPanel.get().add(otb);
		
	}
	
	private static class OptionalTextBox extends Composite implements ClickHandler{
		private TextBox textBox = new TextBox();
		private CheckBox checkBox = new CheckBox();
		
		public OptionalTextBox(String caption){
			VerticalPanel panel = new VerticalPanel();
			panel.add(checkBox);
			panel.add(textBox);
			
			checkBox.setText(caption);
			checkBox.setValue(true);
			checkBox.addClickHandler(this);
			
			// All composites must call initWidget() in their constructors.
			initWidget(panel);
			setStyleName("example-OptionalCheckBox"); 
		}
		

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Object sender = event.getSource();
			if(sender == checkBox) {
				textBox.setEnabled(checkBox.getValue());
			}
			
		}
		
		public void setCaption(String caption) {
			checkBox.setText(caption);
		}
		

		public void getCaption() {
			checkBox.getText();
		}
		
	}

}
