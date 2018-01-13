package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcherGWT implements EntryPoint {
 
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private FlexTable stocksFlexTable = new FlexTable();
	private TextBox newSymbolTextBox = new TextBox();
	private Button addStockButton = new Button("Add");
	private Label lastUpdatedLabel = new Label();
	private static final int REFRESH_INTERNAL = 5000;
	
	private ArrayList<String> stocks = new ArrayList<>();
	
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// TODO Create table for stock data.
		stocksFlexTable = createStocksTable();
	    // TODO Assemble Add Stock panel.
		addPanel.add(newSymbolTextBox);
		addPanel.add(addStockButton);
		addPanel.addStyleName("addPanel");
	    // TODO Assemble Main panel.
		
		mainPanel.add(stocksFlexTable);
		mainPanel.add(addPanel);
		mainPanel.add(lastUpdatedLabel);
	    // TODO Associate the Main panel with the HTML host page.
		RootPanel.get("stockList").add(mainPanel);
		
	    // TODO Move cursor focus to the input box.
		newSymbolTextBox.setFocus(true);
		
		
		// Setup timer to refresh list automtically
		 Timer refreshTimer = new Timer() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				refreshWatchList(); 				
			}
		};
		refreshTimer.scheduleRepeating(REFRESH_INTERNAL); //ms
			 
 
		
		// TODO: Listen for mouse events on the Add button.
		addStockButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				addStock();
				
			}
		});
		
		
		 // Listen for keyboard events in the input box.
		newSymbolTextBox.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				// TODO Auto-generated method stub
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					addStock();	
				}
				
			}
		});
		
		
		
		
	}
	
	private void refreshWatchList() {
		
		final double MAX_PRICE = 100.0; // $100.00
		final double MAX_PRICE_CHANGE = 0.02;  // +/- 2%
		StockPrice[] prices = new StockPrice[stocks.size()];
		for(int i=0; i < stocks.size() ; i++) {
			double price = Random.nextDouble() * MAX_PRICE;
			double change = price * MAX_PRICE_CHANGE * (Random.nextDouble() * 2.0 - 1.0); 
		       prices[i] = new StockPrice(stocks.get(i), price, change);
		}
		updateTable(prices);
	}
	
	 /**
     * Update the Price and Change fields all the rows in the stock table.
     *
     * @param prices 
     * Stock data for all rows.
     */
	private void updateTable(StockPrice[] prices) {
		 
		for (int i = 0; i < prices.length; i++) {
	        updateTable(prices[i]);
	      }
		
		// Display timestamp showing last refresh.
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM);
	      lastUpdatedLabel.setText("Last update : " 
	        + dateFormat.format(new Date()));
		
	}
	
	private void updateTable(StockPrice price) {
		if(!stocks.contains(price.getSymbol())) {
			return;
		}
		
		int row = stocks.indexOf(price.getSymbol()) + 1;
		String priceText =NumberFormat.getFormat("#,##0.00").format(
		         price.getPrice());
		NumberFormat changeFormat =NumberFormat.getFormat("+#,##0.00;-#,##0.00");
		String changeText = changeFormat.format(price.getChange());
		String changePercentText = changeFormat.format(price.getChangePercent());

		//Populate the Price and Change fields with new data.
	     stocksFlexTable.setText(row, 1, priceText);
	     Label changeWidget = (Label) stocksFlexTable.getWidget(row, 2);
	     changeWidget.setText( changeText + " (" + changePercentText
		         + "%)");
	     //stocksFlexTable.setText(row, 2, changeText + " (" + changePercentText  + "%)");
		
	  // Change the color of text in the Change field based on its value.
	     String changeStyleName = "noChange";
	     if (price.getChangePercent() < -0.1f) {
	       changeStyleName = "negativeChange";
	     }
	     else if (price.getChangePercent() > 0.1f) {
	       changeStyleName = "positiveChange";
	     }

	     changeWidget.setStyleName(changeStyleName);
	     
	}
	
	
	
	/**
	   * Add stock to FlexTable. Executed when the user clicks the addStockButton or
	   * presses enter in the newSymbolTextBox.
	   */
	private void addStock() {
		final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
		newSymbolTextBox.setFocus(true);
		// Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
		if(!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
			Window.alert("'" + symbol + "' is not a valid symbol.");
			newSymbolTextBox.selectAll();
			return; 
		}
		newSymbolTextBox.setText("");  
		
		 
	      // TODO Add the stock to the table		
		int row = stocksFlexTable.getRowCount();
		stocks.add(symbol);		
		stocksFlexTable.setWidget(row, 2, new Label());
		stocksFlexTable.setText(row, 0, symbol);
		stocksFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
		stocksFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
		stocksFlexTable.getCellFormatter().addStyleName(row, 1, "watchListRemoveColumn");
		
		// TODO Add a button to remove this stock from the table.
		Button removeStockButton = new Button("X");
		removeStockButton.addStyleDependentName("remove");
		removeStockButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				int removeIndex = stocks.indexOf(symbol);
				stocks.remove(removeIndex);
				stocksFlexTable.removeRow(removeIndex + 1);
				
			}
		});
		stocksFlexTable.setWidget(row, 3, removeStockButton);
		 // TODO Get the stock price.
		refreshWatchList();  
		
		// TODO Don't add the stock if it's already in the table.
		
		if(stocks.contains(symbol));
			return; 
	}
	 
	
	
	
	private FlexTable createStocksTable() { 
		FlexTable stocksTable = new FlexTable(); 
		stocksTable.setText(0, 0,"Symbol");
		stocksTable.setText(0, 1,"Price");
		stocksTable.setText(0, 2,"Change");
		stocksTable.setText(0, 3,"Remove");
		stocksTable.getRowFormatter().addStyleName(0,"watchListHeader");
		stocksTable.addStyleName("watchList");
		stocksTable.getCellFormatter().addStyleName(0,  1,"watchListNumericColumn");
		stocksTable.getCellFormatter().addStyleName(0,  2,"watchListNumericColumn");
		stocksTable.getCellFormatter().addStyleName(0,  3,"watchListRemoveColumn");
		stocksTable.setCellPadding(6);
		return stocksTable;  
	}
}
