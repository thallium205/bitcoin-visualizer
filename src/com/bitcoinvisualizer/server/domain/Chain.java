package com.bitcoinvisualizer.server.domain;

import java.util.Date;

import javax.persistence.Id;

// The datastore is flat, and will therefore repeat much data for a block and incoming and outgoing transactions.  This object represents the actual "row" that is stored in the datastore.
public class Chain
{
	@Id
	Long id;

	// Block-level information
	private String blockHash;
	private String blockVersion;
	private String prev_block;
	private String mrkl_root;
	private Date time;
	private long bits;
	private long nonce;
	private int n_tx;
	private long blockSize;

	// Transaction-level information
	private String transactionHash;
	private int transactionVersion;
	private int vin_sz;
	private int vout_sz;
	private int lock_time;
	private long transactionSize;

	
	// Incoming transaction information
	private String incomingHash;
	private long n;
	private String scriptSig;
	private String coinbase;

	// Outgoing transaction information
	private String value;
	private String scriptPubKey;

	// Used by objectify for serialization
	@SuppressWarnings("unused")
	private Chain()	{}

	// Incoming-transaction constructor
	public Chain(String blockHash, String blockVersion, String prev_block, String mrkl_root, Date time, long bits, long nonce, int n_tx,
			long blockSize, String transactionHash, int transactionVersion, int vin_sz, int vout_sz, int lock_time, long transactionSize,
			String incomingHash, long n, String scriptSig, String coinbase)
	{
		this.blockHash = blockHash;
		this.blockVersion = blockVersion;
		this.prev_block = prev_block;
		this.mrkl_root = mrkl_root;
		this.time = time;
		this.bits = bits;
		this.nonce = nonce;
		this.n_tx = n_tx;
		this.blockSize = blockSize;
		this.transactionHash = transactionHash;
		this.transactionVersion = transactionVersion;
		this.vin_sz = vin_sz;
		this.vout_sz = vout_sz;
		this.lock_time = lock_time;
		this.transactionSize = transactionSize;
		this.incomingHash = incomingHash;
		this.n = n;
		this.scriptSig = scriptSig;
		this.coinbase = coinbase;	
	}

	// Outgoing-transaction constructor
	public Chain(String blockHash, String blockVersion, String prev_block, String mrkl_root, Date time, long bits, long nonce, int n_tx,
			long blockSize, String transactionHash, int transactionVersion, int vin_sz, int vout_sz, int lock_time, long transactionSize,
			String value, String scriptPubKey)
	{
		this.blockHash = blockHash;
		this.blockVersion = blockVersion;
		this.prev_block = prev_block;
		this.mrkl_root = mrkl_root;
		this.time = time;
		this.bits = bits;
		this.nonce = nonce;
		this.n_tx = n_tx;
		this.blockSize = blockSize;
		this.transactionHash = transactionHash;
		this.transactionVersion = transactionVersion;
		this.vin_sz = vin_sz;
		this.vout_sz = vout_sz;
		this.lock_time = lock_time;
		this.transactionSize = transactionSize;
		this.value = value;
		this.scriptPubKey = scriptPubKey;
	}
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getBlockHash()
	{
		return blockHash;
	}

	public void setBlockHash(String blockHash)
	{
		this.blockHash = blockHash;
	}

	public String getBlockVersion()
	{
		return blockVersion;
	}

	public void setBlockVersion(String blockVersion)
	{
		this.blockVersion = blockVersion;
	}

	public String getPrev_block()
	{
		return prev_block;
	}

	public void setPrev_block(String prev_block)
	{
		this.prev_block = prev_block;
	}

	public String getMrkl_root()
	{
		return mrkl_root;
	}

	public void setMrkl_root(String mrkl_root)
	{
		this.mrkl_root = mrkl_root;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public long getBits()
	{
		return bits;
	}

	public void setBits(long bits)
	{
		this.bits = bits;
	}

	public long getNonce()
	{
		return nonce;
	}

	public void setNonce(long nonce)
	{
		this.nonce = nonce;
	}

	public int getN_tx()
	{
		return n_tx;
	}

	public void setN_tx(int n_tx)
	{
		this.n_tx = n_tx;
	}

	public long getBlockSize()
	{
		return blockSize;
	}

	public void setBlockSize(long blockSize)
	{
		this.blockSize = blockSize;
	}

	public String getTransactionHash()
	{
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash)
	{
		this.transactionHash = transactionHash;
	}

	public int getTransactionVersion()
	{
		return transactionVersion;
	}

	public void setTransactionVersion(int transactionVersion)
	{
		this.transactionVersion = transactionVersion;
	}

	public int getVin_sz()
	{
		return vin_sz;
	}

	public void setVin_sz(int vin_sz)
	{
		this.vin_sz = vin_sz;
	}

	public int getVout_sz()
	{
		return vout_sz;
	}

	public void setVout_sz(int vout_sz)
	{
		this.vout_sz = vout_sz;
	}

	public int getLock_time()
	{
		return lock_time;
	}

	public void setLock_time(int lock_time)
	{
		this.lock_time = lock_time;
	}

	public long getTransactionSize()
	{
		return transactionSize;
	}

	public void setTransactionSize(long transactionSize)
	{
		this.transactionSize = transactionSize;
	}

	public String getIncomingHash()
	{
		return incomingHash;
	}

	public void setIncomingHash(String incomingHash)
	{
		this.incomingHash = incomingHash;
	}

	public long getN()
	{
		return n;
	}

	public void setN(long n)
	{
		this.n = n;
	}

	public String getScriptSig()
	{
		return scriptSig;
	}

	public void setScriptSig(String scriptSig)
	{
		this.scriptSig = scriptSig;
	}

	public String getCoinbase()
	{
		return coinbase;
	}

	public void setCoinbase(String coinbase)
	{
		this.coinbase = coinbase;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getScriptPubKey()
	{
		return scriptPubKey;
	}

	public void setScriptPubKey(String scriptPubKey)
	{
		this.scriptPubKey = scriptPubKey;
	}


}
