package com.bitcoinvisualizer.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Composite;


public class TopMenuBarView extends Composite
{

	// private final Widget widget;
	private static TopMenuBarUiBinder uiBinder = GWT.create(TopMenuBarUiBinder.class);

	public interface TopMenuBarUiBinder extends UiBinder<Widget, TopMenuBarView>
	{
	}
	
	
	public TopMenuBarView() 
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	//public TopMenuBar(final Binder binder)
	//{
		
		
		
		// widget = binder.createAndBindUi(this);
		// initWidget(uiBinder.)
		//
		// bindCustomUiHandlers();
	//}

	//@Override
	//public Widget asWidget()
//	{
	//	return widget;
//	}
	
	// Registers handlers for all the ui elements
	// TODO - could violate binding seperation?
	/*
	protected void bindCustomUiHandlers()
	{
		// register the admin menuitem
		admin.setCommand(new Command()
		{
			@Override
			public void execute()
			{
				PlaceRequest placeRequest = new PlaceRequest(NameTokens.admin);
				getPlaceManager().revealPlace(placeRequest);
				
			}
			
		});
		
	}
	*/
	
	

}
