package com.bitcoinvisualizer.client.view.handlers;

import com.gwtplatform.mvp.client.UiHandlers;

public interface AdminUiHandlers extends UiHandlers
{
	void onButtonUpdateDatabase();
	void onButtonSearchBlock();
	void onButtonSearchTransaction();
	void onbuttonSearchAddress();	
}
