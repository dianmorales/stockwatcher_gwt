package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;

public class MyFoo extends Composite {
	@UiField Button button;
	
	public MyFoo() {
		initWidget(button);
	}
	
	@UiHandler("button")
	void handleClikc(ClickEvent e) {
		Window.alert("Hello, Ajax");
	}

}
