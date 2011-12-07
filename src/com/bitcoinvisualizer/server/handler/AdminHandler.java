package com.bitcoinvisualizer.server.handler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.bitcoinvisualizer.server.dao.ChainDAO;
import com.bitcoinvisualizer.server.datatypes.BlockType;
import com.bitcoinvisualizer.server.datatypes.IncomingAddressType;
import com.bitcoinvisualizer.server.datatypes.OutgoingAddressType;
import com.bitcoinvisualizer.server.datatypes.TransactionType;
import com.bitcoinvisualizer.server.domain.Chain;
import com.bitcoinvisualizer.shared.action.AdminAction;
import com.bitcoinvisualizer.shared.action.AdminBlockChainFetcher;
import com.bitcoinvisualizer.shared.action.AdminResult;
import com.bitcoinvisualizer.shared.exception.AdminException;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

// This is where the party happens.
// TODO - How do I make this send multiple updates back down to the client?  I'm going to have to figure this out for the visualizer itself too...

public class AdminHandler implements ActionHandler<AdminAction, AdminResult> 
{
	
	private final Provider<HttpServletRequest> requestProvider;

	@Inject
	AdminHandler(final ServletContext servletContext, final Provider<HttpServletRequest> requestProvider)
	{
		this.requestProvider = requestProvider;
	}

	@Override
	public AdminResult execute(AdminAction action, ExecutionContext context) throws ActionException
	{
		AdminResult result = null;
		AdminBlockChainFetcher blockFetcher = new AdminBlockChainFetcher();
		BlockType block;
		ChainDAO chainDAO = new ChainDAO();

		// The presenter is going to be responsible for retrieving the next
		// block hash. This adminAction and handler will only store one
		// block at a time.
		// THis is a good idea as app engine has a 30 second timeout for
		// requests.
		try
		{
			switch (action.getActionID())
			{
			case AdminAction.STORE_SINGLE_BLOCK:
				block = blockFetcher.getBlockFromHash(action.getBlockHash());

				// Now that we have the block, we need to make sure it does not
				// already exist in the database. If it does, we will throw an
				// AdminException
				if (chainDAO.isBlockStored(block.getHash()))
				{
					// throw an exception because it is already stored
					throw new AdminException("Block: " + block.getHash() + " already exists in the datastore.");
				}

				else
				{
					// There is no record of this block hash in the database.
					// Therefore, we will store it.
					for (int i = 0; i < block.getTransactions().size(); i++)
					{
						TransactionType transaction = block.getTransactions().get(i);

						// We will first store all inbound transactions
						for (int j = 0; j < transaction.getIncoming_address().size(); j++)
						{
							IncomingAddressType incomingAddress = transaction.getIncoming_address().get(j);

							chainDAO.put(new Chain(block.getHash(), block.getVersion(), block.getPrev_block(), block.getMrkl_root(), block.getTime(),
									block.getBits(), block.getNonce(), block.getN_tx(), block.getSize(), transaction.getHash(), transaction
											.getVersion(), transaction.getVin_sz(), transaction.getVout_sz(), transaction.getLock_time(), transaction
											.getSize(), incomingAddress.getHash(), incomingAddress.getN(), incomingAddress.getScriptSig(),
									incomingAddress.getCoinbase()));
						}

						// We will then store all outbound transactions
						for (int j = 0; j < block.getTransactions().get(i).getOutgoing_address().size(); j++)
						{
							OutgoingAddressType outgoingAddress = transaction.getOutgoing_address().get(j);

							chainDAO.put(new Chain(block.getHash(), block.getVersion(), block.getPrev_block(), block.getMrkl_root(), block.getTime(),
									block.getBits(), block.getNonce(), block.getN_tx(), block.getSize(), transaction.getHash(), transaction
											.getVersion(), transaction.getVin_sz(), transaction.getVout_sz(), transaction.getLock_time(), transaction
											.getSize(), outgoingAddress.getValue(), outgoingAddress.getScriptPubKey()));
						}
					}

					result = new AdminResult(block.getHash(), block.getPrev_block());
				}
				break;

			default:
				break;
			}
		}

		catch (Exception e)
		{
			throw new ActionException(e);
		} 
		
		return result;
	}

	@Override
	public Class<AdminAction> getActionType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo(AdminAction arg0, AdminResult arg1, ExecutionContext arg2) throws ActionException
	{
		// TODO Auto-generated method stub

	}

}
