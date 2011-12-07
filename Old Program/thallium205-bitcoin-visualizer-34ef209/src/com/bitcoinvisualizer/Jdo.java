package com.bitcoinvisualizer;

import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;

public class Jdo implements Interface
{
	private static final PersistenceManagerFactory instance = JDOHelper.getPersistenceManagerFactory("optional-transactions");

	public static PersistenceManagerFactory getPersistenceManagerFactory()
	{
		return instance;
	}

	@Override
	public void addBlock(BlockType block)
	{
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		try
		{
			pm.makePersistent(block);
		} catch (Exception e)
		{
			System.out.println(e);
		} finally
		{
			pm.flush();
			pm.close();
		}

	}

	@Override
	public void removeBlock(BlockType block)
	{
		// TODO Auto-generated method stub
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		try
		{
			pm.currentTransaction().begin();

			// We don't have a reference to the selected Product.
			// So we have to look it up first,
			block = pm.getObjectById(BlockType.class, block.getKey());
			pm.deletePersistent(block);
			pm.currentTransaction().commit();
		} catch (Exception ex)
		{
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally
		{
			pm.close();
		}

	}

	@Override
	public void updateBlock(BlockType block)
	{
		// TODO Auto-generated method stub
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		Key key = block.getKey();
		String hash = block.getHash();
		String version = block.getVersion();
		String prev_block = block.getPrev_block();
		String mrkl_root = block.getMrkl_root();
		Date date = block.getTime();
		long bits = block.getBits();
		long nonce = block.getNonce();
		int n_tx = block.getN_tx();
		long size = block.getSize();

		try
		{
			pm.currentTransaction().begin();
			// We don't have a reference to the selected Product.
			// So we have to look it up first,
			block = pm.getObjectById(BlockType.class, block.getKey());
			block.setKey(key);
			block.setHash(hash);
			block.setVersion(version);
			block.setPrev_block(prev_block);
			block.setMrkl_root(mrkl_root);
			block.setTime(date);
			block.setBits(bits);
			block.setNonce(nonce);
			block.setN_tx(n_tx);
			block.setSize(size);
			pm.makePersistent(block);
			pm.currentTransaction().commit();
		} catch (Exception ex)
		{
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally
		{
			pm.close();
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BlockType> getBlocks()
	{
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();

		String query = "select from " + BlockType.class.getName();
		return (List<BlockType>) pm.newQuery(query).execute();
	}

	/**
	 * Given a blockhash, this will return it
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BlockType> findBlockByHash(String s)
	{
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		List<BlockType> results = null;

		Query query = pm.newQuery(BlockType.class);
		query.setFilter("hash == hashParam");
		query.declareParameters("String hashParam");

		try
		{
			results = (List<BlockType>) query.execute(s);
		} finally
		{
			query.closeAll();
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransactionType> findTransactionByHash(String s)
	{
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		List<TransactionType> results = null;

		Query query = pm.newQuery(TransactionType.class);
		query.setFilter("hash == hashParam");
		query.declareParameters("String hashParam");

		try
		{
			results = (List<TransactionType>) query.execute(s);
		} finally
		{
			query.closeAll();
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IncomingAddressType> findIncomingAddressesByAncestor(Key ancestorKey)
	{
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		List<IncomingAddressType> results = null;

		Query query = pm.newQuery();

		try
		{
			results = (List<IncomingAddressType>) query.execute();
		} finally
		{
			query.closeAll();
		}
		;
		query.execute();

		return results;
	}

	/**
	 * Returns a list of outgoingaddress type keys
	 */
	@Override
	public List<Key> findOutgoingAddressKeysByAncestory(Key ancestorKey)
	{
		PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();
		List<Key> results = null;

		Query query = pm.newQuery(ancestorKey);

		return null;
	}

}
