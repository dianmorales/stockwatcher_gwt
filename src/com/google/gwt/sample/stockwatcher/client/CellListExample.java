package com.google.gwt.sample.stockwatcher.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.RootPanel;
 

public class CellListExample implements EntryPoint {

	private static final List<String> DAYS = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday","Friday", "Saturday");
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
		TextCell textCell = new TextCell();
		CellList<String> cellList = new CellList<>(textCell);
		cellList.setRowCount(DAYS.size());
		cellList.setRowData(0, DAYS);
		RootPanel.get().add(cellList); 
	}
	
	

}
