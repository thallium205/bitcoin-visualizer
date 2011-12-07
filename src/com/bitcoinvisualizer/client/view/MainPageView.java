package com.bitcoinvisualizer.client.view;

import com.bitcoinvisualizer.client.presenter.MainPagePresenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainPageView extends ViewImpl implements MainPagePresenter.MyView
{

	private final Widget widget;

	@UiField
	FlowPanel mainContentPanel;

	public interface Binder extends UiBinder<Widget, MainPageView>
	{
	}

	@Inject
	public MainPageView(final Binder binder)
	{
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget()
	{
		return widget;
	}

	// GWTP will call setInSlot when a child presenter asks to be added under
	// this view. To support inheritance in your views it is good practice to
	// call super.setInSlot when you can't handle the call. Who knows, maybe the
	// parent class knows what to do with this slot.
	@Override
	public void setInSlot(Object slot, Widget content)
	{
		if (slot == MainPagePresenter.TYPE_SetMainContent)
		{			
			setMainContent(content);
		}
		
		else
		{
			super.setInSlot(slot, content);
		}
	}
	
	private void setMainContent(Widget content) 
	{
	    mainContentPanel.clear();
	    if (content != null) 
	    {
	      mainContentPanel.add(content);
	    }
	}
}
