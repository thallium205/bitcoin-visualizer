package com.bitcoinvisualizer.shared.action;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.shared.ActionImpl;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
// Given an ID which tells the handler what to do and a blockHash which tells the handler what block to download,
// this function will return the downloadedBlockHash and the nextBlockHash.
@GenDispatch(isSecure = false, serviceName = ActionImpl.DEFAULT_SERVICE_NAME)

public class Admin
{
	@In(1)
	int id;
	@In(2)
	String blockHash;
	@Out(1)
	String downloadedBlockHash;
	@Out(2)
	String nextBlockHash;

}
