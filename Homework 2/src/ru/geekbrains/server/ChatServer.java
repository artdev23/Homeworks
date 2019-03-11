package ru.geekbrains.server;


import java.io.IOException;
import java.net.ServerSocket;

import static java.lang.System.out;


public class ChatServer
{

  private final ServerSocket sock;
  private SessionManager manager;

  public static final String HOST = "localhost";
  public static final int PORT = 7777;


  public ChatServer()
  throws IOException
  {
	sock = new ServerSocket(PORT);
  }


  public void start()
  throws IOException
  {
	out.println("Server started!");

	manager = new SessionManager(sock);
	manager.start();
  }


  public void close()
  {
	try
	{
	  sock.close();
	  manager.close();
	}
	catch (IOException e)
	{
	  e.printStackTrace();
	}
  }


  public static void main(String[] args)
  throws IOException
  {
	ChatServer chatServer = new ChatServer();
	try
	{
	  chatServer.start();
	}
	catch (IOException e)
	{
	  e.printStackTrace();
	}
	finally
	{
	  chatServer.close();
	}
  }

}