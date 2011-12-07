package com.bitcoinvisualizer.server.dao;

import com.bitcoinvisualizer.server.domain.Chain;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;


// This class will primarily be used for very specific kinds of queries.  The basic put/get stuff is handled in the superclass ObjectifyDAO
public class ChainDAO extends ObjectifyDAO<Object>
{
	static
	{
		ObjectifyService.register(Chain.class);
	}
	
	/**
	 * Method to return if a block has already been stored in the datastore.  This is much faster than the convenience class
	 * 
	 * @param blockHash
	 * @return true if exists, false if it does not
	 * @throws TooManyResultsException
	 */
	public boolean isBlockStored(String blockHash)
	{
		Iterable<Key<Chain>> q = ofy().query(Chain.class).filter("blockHash", blockHash).limit(1).fetchKeys();
		if (!q.iterator().hasNext())
			return false;
		else
			return true;
	}
}
