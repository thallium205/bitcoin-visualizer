package com.bitcoinvisualizer.server.guice;

import com.bitcoinvisualizer.server.handler.AdminHandler;
import com.bitcoinvisualizer.shared.action.AdminAction;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule
{

	@Override
	protected void configureHandlers()
	{
		// Bind Action to Action Handler
	    bindHandler(AdminAction.class, AdminHandler.class);
	}
}
