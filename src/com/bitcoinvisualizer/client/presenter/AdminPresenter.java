package com.bitcoinvisualizer.client.presenter;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.bitcoinvisualizer.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.bitcoinvisualizer.client.presenter.MainPagePresenter;
import com.bitcoinvisualizer.client.view.handlers.AdminUiHandlers;
import com.bitcoinvisualizer.shared.action.AdminAction;
import com.bitcoinvisualizer.shared.action.AdminResult;


public class AdminPresenter extends Presenter<AdminPresenter.MyView, AdminPresenter.MyProxy> implements AdminUiHandlers
{
	private final EventBus eventBus;
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	public interface MyView extends View, HasUiHandlers<AdminUiHandlers>
	{
		void setConsoleText(String text);
		String getLatestHash();
		boolean isRecursiveEnabled();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.admin)
	public interface MyProxy extends ProxyPlace<AdminPresenter>
	{
	}

	@Inject
	public AdminPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy, final DispatchAsync dispatcher,
			final PlaceManager placeManager)
	{
		super(eventBus, view, proxy);

		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;	
		
		getView().setUiHandlers(this);
	}

	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}

	@Override
	protected void onBind()
	{
		super.onBind();
	}

	@Override
	public void onButtonUpdateDatabase()
	{		
		// The client downloads the latest block hash
		try
		{
			//String blockHash = AdminBlockChainFetcher.getLatestHash();
			
			String blockHash = getView().getLatestHash();
			
			// We download the whole chain, stopping only when we reach the genesis block if recursive checkbox is enabled			
			getDispatcher().execute(new AdminAction(AdminAction.STORE_SINGLE_BLOCK, blockHash), new AsyncCallback<AdminResult>()
			{

				@Override
				public void onFailure(Throwable caught)
				{
					// getView().setConsoleText((caught.toString()));
					System.out.println(caught.toString());
				}

				@Override
				public void onSuccess(AdminResult result)
				{
					System.out.println(result.toString());			
					// if recursive is enabled, get the next block hash.	  TODO - hokey method implemented in the view.
					getView().setConsoleText(result.getPreviousBlockHash());								
				}

			});
		} 
		
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onButtonSearchBlock()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onButtonSearchTransaction()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onbuttonSearchAddress()
	{
		// TODO Auto-generated method stub
		
	}

	private DispatchAsync getDispatcher()
	{
		return dispatcher;
	}

	private PlaceManager getPlaceManager()
	{
		return placeManager;
	}


}
