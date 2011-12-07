package com.bitcoinvisualizer;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface Interface
{
	void addBlock(BlockType block);
	void removeBlock(BlockType block);
	void updateBlock(BlockType block);
	List<BlockType> getBlocks();	
	List<BlockType> findBlockByHash(String s);
	List<TransactionType> findTransactionByHash(String s);

	List<IncomingAddressType> findIncomingAddressesByAncestor(Key ancestorKey);
	List<Key> findOutgoingAddressKeysByAncestory(Key ancestorKey);
}