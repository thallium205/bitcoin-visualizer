package com.bitcoinvisualizer;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class OutgoingAddressType
{
	@Persistent
	private TransactionType transactionType;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String value;
	
	@Persistent
	private String scriptPubKey;
	
	public OutgoingAddressType()
	{
		
	}
	
	public OutgoingAddressType(String value, String scriptPubKey)
	{
		this.value = value;
		this.scriptPubKey = scriptPubKey;
	}

	public Key getKey()
	{
		return key;
	}

	public void setKey(Key key)
	{
		this.key = key;
	}

	/**
	 * 
	 * @return	The BTC sent by this output.
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * 
	 * @param value The BTC sent by this output.
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * 
	 * @return	This script specifies the conditions that must be met by someone attempting to redeem this output. Usually it contains a hash160 (Bitcoin address) or a public key.
	 */
	public String getScriptPubKey()
	{
		return scriptPubKey;
	}

	/**
	 * 
	 * @param scriptPubKey This script specifies the conditions that must be met by someone attempting to redeem this output. Usually it contains a hash160 (Bitcoin address) or a public key.
	 */
	public void setScriptPubKey(String scriptPubKey)
	{
		this.scriptPubKey = scriptPubKey;
	}
	
	/**
	 * 
	 * @return The parent of this outgoing address type
	 */
	public TransactionType getTransactionType()
	{
		return transactionType;
	}

	/**
	 * 
	 * @param transactionType The parent of this outgoing address type
	 */
	public void setTransactionType(TransactionType transactionType)
	{
		this.transactionType = transactionType;
	}
	
	
}
