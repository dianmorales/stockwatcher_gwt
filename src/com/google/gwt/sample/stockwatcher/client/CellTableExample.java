package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.RootPanel;

public class CellTableExample implements EntryPoint{

	private static List<Contact> CONTACTS = Arrays.asList(new Contact("John", "123 Fourth Road"), new Contact("Mary", "222 Lancer Lane"));
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		CellTable<Contact> table = new CellTable<>();
		TextColumn<Contact> nameColumn =  new TextColumn<Contact>() {

			@Override
			public String getValue(Contact contact) {
					// TODO Auto-generated method stub
				return contact.name;
			}  
		};
		 
		TextColumn<Contact> addressColumn = new TextColumn<Contact>() {

			@Override
			public String getValue(Contact contact) {
				// TODO Auto-generated method stub
				return contact.address;
			}
		};
		
	table.addColumn(nameColumn, "Name");
	table.addColumn(nameColumn, "Address");
	
	table.setRowCount(CONTACTS.size(), true);
	table.setRowData(0, CONTACTS);
	RootPanel.get().add(table);  
 	}  	
	  
	
	private static class Contact {
		private final String address;
		private final String name;
		
		
		public Contact(String name, String address) {
			this.address = address;
			this.name = name; 
		} 
	}
	
	

}
