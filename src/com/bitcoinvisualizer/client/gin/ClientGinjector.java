package com.bitcoinvisualizer.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.bitcoinvisualizer.client.gin.ClientModule;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.inject.Provider;
import com.bitcoinvisualizer.client.presenter.MainPagePresenter;
import com.bitcoinvisualizer.client.presenter.HomePresenter;
import com.google.gwt.inject.client.AsyncProvider;
import com.bitcoinvisualizer.client.presenter.AdminPresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector
{

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	Provider<MainPagePresenter> getMainPagePresenter();

	Provider<HomePresenter> getHomePresenter();

	AsyncProvider<AdminPresenter> getAdminPresenter();
}
