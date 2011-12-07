package com.bitcoinvisualizer.shared.exception;

public class AdminException extends Exception
{
	private static final long serialVersionUID = -3520458397957806676L;

	public AdminException()
	{
	}

	public AdminException(String message)
	{
		super(message);
	}

	public AdminException(String message, Throwable cause)
	{
		super(message + " (" + cause.getMessage() + ")");
	}

	public AdminException(Throwable cause)
	{
		super(cause.getMessage());
	}

}
