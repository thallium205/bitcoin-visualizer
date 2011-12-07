package com.bitcoinvisualizer.client.view;

import com.bitcoinvisualizer.client.presenter.AdminPresenter;
import com.bitcoinvisualizer.client.view.handlers.AdminUiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.RichTextArea;

public class AdminView extends ViewWithUiHandlers<AdminUiHandlers> implements AdminPresenter.MyView
{

	private final Widget widget;
	
	@UiField 
	Button btnUpdateDatabase;
	@UiField 
	TextBox txtLatestHash;
	@UiField 
	CheckBox checkRecursive;
	@UiField 
	RichTextArea txtConsole;
	@UiField 
	Button btnSearchBlock;
	@UiField 
	Button btnSearchTransaction;
	@UiField 
	Button btnSearchAddress;
	@UiField 
	TextBox txtBlockHash;
	@UiField 
	TextBox txtTransactionHash;
	@UiField 
	TextBox txtAddressHash;

	public interface Binder extends UiBinder<Widget, AdminView>
	{
	}


	@Inject
	public AdminView(final Binder binder)
	{
		widget = binder.createAndBindUi(this);
		
		bindCustomUiHandlers();
	}

	@Override
	public Widget asWidget()
	{
		return widget;
	}
	
	private void bindCustomUiHandlers()
	{
		btnUpdateDatabase.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (getUiHandlers() != null)
				{
					getUiHandlers().onButtonUpdateDatabase();
				}

			} 
		});		
		
		btnSearchBlock.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (getUiHandlers() != null)
				{
					getUiHandlers().onButtonSearchBlock();
				}

			} 
		});	
		
		btnSearchTransaction.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (getUiHandlers() != null)
				{
					getUiHandlers().onButtonSearchTransaction();
				}

			} 
		});	
		
		btnSearchAddress.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (getUiHandlers() != null)
				{
					getUiHandlers().onbuttonSearchAddress();
				}

			} 
		});			
	
	}
	
	public void setConsoleText(String text)
	{
		// String currentText = txtConsole.getText();		
		// txtConsole.setText("Success.  Next download: " + currentText + "\n" + text);
		
		// Get next download
		if (checkRecursive.getValue())
		{
			txtLatestHash.setText(text);
			getUiHandlers().onButtonUpdateDatabase();
		}
	}
	
	public String getLatestHash()
	{
		return txtLatestHash.getText();
	}
	
	public boolean isRecursiveEnabled()
	{
		return checkRecursive.getValue();
	}
}
