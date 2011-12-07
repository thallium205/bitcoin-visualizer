package com.bitcoinvisualizer.shared.action;

import com.gwtplatform.dispatch.shared.Result;

public class AdminResult implements Result
{
	private String downloadedBlockHash;
	private String previousBlockHash;
	protected AdminResult()
	{
		// serialization.
	}

	public AdminResult(String downloadedBlockHash, String previousBlockHash)
	{
		this.downloadedBlockHash = downloadedBlockHash;
		this.previousBlockHash = previousBlockHash;	
	}

	public String getDownloadedBlockHash()
	{
		return downloadedBlockHash;
	}

	public void setDownloadedBlockHash(String downloadedBlockHash)
	{
		this.downloadedBlockHash = downloadedBlockHash;
	}

	public String getPreviousBlockHash()
	{
		return previousBlockHash;
	}

	public void setPreviousBlockHash(String previousBlockHash)
	{
		this.previousBlockHash = previousBlockHash;
	}
	
	
}
