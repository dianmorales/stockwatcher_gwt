package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface StockWatchResources extends ClientBundle{
	public interface MyCss extends CssResource{
		String blackText();
	} 
	
	@Source("StockWatcher.css")
	MyCss style();
	
}
