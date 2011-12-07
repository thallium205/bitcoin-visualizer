package com.bitcoinvisualizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class Bitcoin_VisualizerServlet extends HttpServlet
{
	ArrayList<String> transactionHashes, previousTransactionHashes;
	Query q;

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		
		/*
		
		  BlockChainFetcher fetcher = new
		  BlockChainFetcher("http://blockexplorer.com/rawblock/",
		  "http://blockexplorer.com/q/latesthash"); 
		  try { 		  
		  fetcher.execute(BlockChainFetcher.INITIAL); }
		  
		 catch (Exception e) 
		 { 		 

		  try
		{
			response.getWriter().println("This error will occur when BlockExplorer has not parsed the latest block.  Try refreshing the page in a minute or so...");
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		  
		 }  

		*/
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		// Lets get a big block key first
		Key blockKey;
		q = new Query("BlockType");
		q.addFilter("hash", Query.FilterOperator.EQUAL, "000000000000076bcb7238c1b380393d0e13c4a2929d69222985a7a76e15ea82");
		q.setKeysOnly();
		blockKey = datastore.prepare(q).asSingleEntity().getKey();
		
		
		

		// Getting forward linkage
		q = new Query("TransactionType");
		q.setAncestor(blockKey);
		transactionHashes = new ArrayList<String>();
		for (Entity result : datastore.prepare(q).asIterable())				
			transactionHashes.add((String) result.getProperty("hash"));		

		for (int i = 0; i < transactionHashes.size(); i++)
		{
			q = new Query("IncomingAddressType");
			q.addFilter("hash", Query.FilterOperator.EQUAL, transactionHashes.get(i));
			q.setKeysOnly();
			try
			{
				System.out.println("Foward linkage found on: " + transactionHashes.get(i) + "  and it belongs to: " + datastore.prepare(q).asSingleEntity().getKey());	
			}
			catch (Exception e)
			{
				System.out.println("This limited database does not have this forward linkage, or it doesn't exist.");
			}
		}		
			
		// Getting reverse linkage
		q = new Query("IncomingAddressType");
		q.setAncestor(blockKey);
		previousTransactionHashes = new ArrayList<String>();
		for (Entity result : datastore.prepare(q).asIterable())					
			previousTransactionHashes.add((String) result.getProperty("hash"));				

		for (int i = 0; i < previousTransactionHashes.size(); i++)
		{
			q = new Query("TransactionType");
			q.addFilter("hash", Query.FilterOperator.EQUAL, previousTransactionHashes.get(i));
			q.setKeysOnly();			
			try
			{
				System.out.println("Reverse linkage found on: " + previousTransactionHashes.get(i) + "  and it belongs to: " + datastore.prepare(q).asSingleEntity().getKey());
			}
			catch (Exception e)
			{
				System.out.println("This limited database does not have this reverse linkage, or this is the creation.");
			}						
		}
		
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // Rudimentary implementation of blockchain traversal. TODO - Iinstead of
	 * // transaction level transaction data // retrieval, use block level. Put
	 * every single transaction of a block // in a single batch api call.
	 * Asynchronously call multiple block // batches. Destroy the datastore with
	 * computations and pump out results like a boss. Stack<String> stack = new
	 * Stack<String>(); Query q;
	 * 
	 * // A sample key with a large amount incoming addresses DatastoreService
	 * datastore = DatastoreServiceFactory.getDatastoreService();
	 * stack.push("1fb18e1cd009798cb84c8dcbc4164d9334a359d3aaa4d9e929b183844f27e9a7"
	 * );
	 * 
	 * while (!stack.isEmpty()) { q = new Query("TransactionType"); // Get the
	 * latest transaction key q.addFilter("hash", Query.FilterOperator.EQUAL,
	 * stack.pop()); q.setKeysOnly();
	 * System.out.println("Getting the key for the next transaction!"); Entity
	 * entity = datastore.prepare(q).asSingleEntity(); if (entity == null) {
	 * System.out.println(
	 * "This incomplete test database does not have that transaction stored at the moment!  Start the next one!"
	 * ); }
	 * 
	 * else { Key transactionKey = entity.getKey();
	 * 
	 * // Retrieve all outgoing transaction KEYS only q = new
	 * Query("OutgoingAddressType"); q.setAncestor(transactionKey);
	 * q.setKeysOnly(); for (Entity result : datastore.prepare(q).asIterable())
	 * System.out.println(result.getKey());
	 * 
	 * // Retrieve all incoming transactions q = new
	 * Query("IncomingAddressType"); q.setAncestor(transactionKey);
	 * 
	 * for (Entity result : datastore.prepare(q).asIterable()) { String incoming
	 * = (String) result.getProperty("hash");
	 * System.out.println("Next transaction hash to map: " + incoming);
	 * stack.push(incoming); } } }
	 */

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}



}
