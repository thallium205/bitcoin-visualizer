package com.bitcoinvisualizer.shared.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import com.bitcoinvisualizer.server.datatypes.BlockType;
import com.google.appengine.repackaged.org.json.JSONException;

public class AdminBlockChainFetcher
{
	public static final String INITIAL = "initial";
	public static final String CONTINUE = "continue";
	
	public static final String LATESTHASHADDRESS = "http://blockexplorer.com/q/latesthash";
	public static final String RAWBLOCKADDRESS = "http://blockexplorer.com/rawblock/";

	private static final Logger log = Logger.getLogger(AdminBlockChainFetcher.class.getName());

	/**
	 * Given base address of blockexplorer rawblock api and the latest hash
	 * link, this function will download the entire blockchain and store it in
	 * the datastore.
	 * 
	 * @param rawBlockaddress
	 *            - The base address of the rawblock api from blockexplorer
	 * @param latestHashAddress
	 *            - The address of where the program can pull the latest hash
	 *            address from
	 */
	public AdminBlockChainFetcher()
	{
	}
	
	public BlockType getBlockFromHash(String hash) throws IOException, JSONException
	{		
		// Download the block from blockexplorer
		System.out.println("Downloading block from blockexplorer...");
		String rawBlock = getBlock(hash);

		// Parse the block
		System.out.println("Parsing block from blockexplorer...");
		return parseBlock(rawBlock);		
	}

	/**
	 * Will fetch the latest hash on the block chain and work backwards in the
	 * black chain, adding them only if they do not exist yet.
	 * 
	 * @param option
	 *            - Given INITIAL, this function will iterate through the entire
	 *            block chain, only skipping blocks if they already exist in the
	 *            datastore, but then will continue. Use this only when first
	 *            building the blockchain in the datastore, or if you suspect
	 *            the blockstore is incomplete. Given CONTINUE, it will only add
	 *            them backward until it finds a match, then stop. Use this when
	 *            the initial blockchain has been downloaded already.
	 * @throws SocketTimeoutException
	 */
	
	/*
	public boolean execute(String option) throws Exception
	{
		// String genesisBlock = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f";
		// Jdo db = new Jdo();

		BlockType block = new BlockType();
		String hash = null;
		String rawBlock = null;
		// Boolean keepDownloading = true;
		
		// int retry = 0;
		// int blockDownload = 0;

		System.out.println("Downloading latest hash...");
		hash = getLatestHash();
		// hash = "0000000000000485b942952876c9ed75d664a0819979dd6aa727f0f631072050";
		// This is the problem block we are having when memory is low -> hash = "0000000000000069e1222f40ce5cf8c6dc565d8caada2485516b2a29017da857";

		// Download the block from blockexplorer
		System.out.println("Downloading block from blockexplorer...");
		rawBlock = getBlock(hash);

		// Parse the block
		System.out.println("Parsing block from blockexplorer...");
		block = parseBlock(rawBlock);		
		
		// Return the bloack
		
		*/
		/*

		while (!block.getHash().equals(genesisBlock) && keepDownloading && retry < 10 && blockDownload < 100)
		{
			try
			{
				// Check to see if we have already stored it
				if (db.findBlockByHash(block.getHash()).isEmpty())
				{
					// if it returns empty we have not already stored it
					// Store the block in the database
					System.out.println("Block not stored yet.  Storing block: " + block.getHash());
					db.addBlock(block);

					// Fetch the next block
					System.out.println("Fetching next block...");
					rawBlock = getBlock(block.getPrev_block());

					// Reset retry to 0
					retry = 0;

					// Parse the newly fetched block
					System.out.println("Parsing block...");
					block = parseBlock(rawBlock);
					
					//TODO - temporary counter that will stop download after 100
					blockDownload++;					
					System.out.println("Block:" + blockDownload + "/100");
				}

				else
				{

					if (option.equals(CONTINUE))
					{
						// we are done, exit
						System.out.println("Duplicate block found.  Exiting.");
						keepDownloading = false;
						return true;
					}

					else if (option.equals(INITIAL))
					{
						System.out.println("Duplicate block found.  Checking the rest of the chain...");

						// Fetch the next block
						System.out.println("Fetching next block...");
						rawBlock = getBlock(block.getPrev_block());
						
						// Reset retry to 0
						retry = 0;

						// Parse the newly fetched block
						System.out.println("Parsing block...");
						block = parseBlock(rawBlock);
					}
				}
			}

			catch (SocketTimeoutException e)
			{
				retry++;
				log.warning(e.toString());
				System.out.println("Block Explorer timed out... retrying " + retry + "/10");
			}
			
			catch (Exception e)
			{
				retry++;
				log.warning(e.toString());
				System.out.println("A critical error occured.  Retrying:  " + retry + "/10");
			}

		}
		/*

		// download the genesis block if it hasnt already been stored already
		/*
		try
		{
			System.out.println("Download/store the genesis block");
			rawBlock = getBlock(genesisBlock);
			block = parseBlock(rawBlock);
			if (db.findBlockByHash(block.getHash()).isEmpty())
				db.addBlock(block);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		//return true;
	//}

	public static String getLatestHash() throws IOException
	{
		URL url = new URL(LATESTHASHADDRESS);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder builder = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null)
		{
			builder.append(line);
		}

		reader.close();
		return builder.toString();
	}

	/**
	 * 
	 * @param hash
	 *            - A bitcoin address hash.
	 * @return - A block.
	 * @throws IOException
	 */
	private String getBlock(String hash) throws IOException
	{
		URL url = new URL(RAWBLOCKADDRESS + hash);
		URLConnection connection;
		connection = url.openConnection();
		// 10 second timeout... the maximum
		connection.setConnectTimeout(10000);
		connection.setReadTimeout(10000);
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null)
		{
			builder.append(line + "\n");
		}

		reader.close();
		return builder.toString();
	}

	/**
	 * Converts a raw block in string form into a BlockType
	 * 
	 * @param block
	 *            A string returned from blockexplorer
	 * @return A blocktype
	 * @throws JSONException
	 */
	private BlockType parseBlock(String block) throws JSONException
	{
		BlockType b = new BlockType();
		b.parseBlock(block);
		return b;
	}
}
