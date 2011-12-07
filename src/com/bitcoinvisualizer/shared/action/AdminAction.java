package com.bitcoinvisualizer.shared.action;

import com.gwtplatform.dispatch.shared.Action;

public class AdminAction implements Action<AdminResult>
{
	public static final int STORE_SINGLE_BLOCK = 1; 
	
	int actionID;
	String blockHash;
	
	protected AdminAction()
	{
		// Serialization
	}	
	
	/**
	 * 
	 * @param id - Used to determine what kind of action is needed.
	 * @param blockHash - What blockchain hash to store to the database
	 */
	public AdminAction(int id, String blockHash)
	{
		this.actionID = id;	
		this.blockHash = blockHash;
	}
	
	// Different ID's will do different things depending on what tools are available on the admin page
	public int getActionID()
	{
		return actionID;
	}
	
	/**
	 * The current block hash to be downloaded as given from the AdminPresenter	 * 
	 */
	public String getBlockHash()
	{
		return blockHash;
	}	
	

	@Override
	public String getServiceName()
	{
		return "dispatch/";
	}

	// No need to secure this
	@Override
	public boolean isSecured()
	{
		// TODO Auto-generated method stub
		return false;
	}
	

	
	

}
