package ru.geekbrains.client.logic;


public class ServerConnectException
		extends RuntimeException
{

  public ServerConnectException(String msg, Throwable cause)
  {
    super(msg, cause);
  }


  public ServerConnectException(String msg)
  {
	this(msg, null);
  }

}