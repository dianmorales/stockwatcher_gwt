package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class CellTreeExample implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		TreeViewModel model = new CustomTreeModel();
		CellTree tree = new CellTree(model, "Item 1");
		RootLayoutPanel.get().add(tree);
		
		
	}
	
	
	private static class CustomTreeModel implements TreeViewModel{

		@Override
		public <T> NodeInfo<?> getNodeInfo(T value) {
			// TODO Auto-generated method stub 
			
			 ListDataProvider<String> dataProvider = new ListDataProvider<String>();
		      for (int i = 0; i < 2; i++) {
		        dataProvider.getList().add(value + "." + String.valueOf(i));
		      } 
		      // Return a node info that pairs the data with a cell.
		      return new DefaultNodeInfo<String>(dataProvider, new TextCell());
		}

		@Override
		public boolean isLeaf(Object value) {
			// TODO Auto-generated method stub
			return value.toString().length() > 10;
		}
		
	}

}
