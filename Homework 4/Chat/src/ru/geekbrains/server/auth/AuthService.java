package ru.geekbrains.server.auth;


import ru.geekbrains.server.SessionManager;
import ru.geekbrains.server.users.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.regex.Matcher;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.sql.DriverManager.getConnection;
import static ru.geekbrains.utils.MessageFormats.*;
import static ru.geekbrains.utils.Utils.outUTF;


public class AuthService
{

  private final SessionManager sessionManager;
  private final Connection dbConn;


  private static final String DRIVER_JDBC = "org.sqlite.JDBC";
  private static final String DB_URL = "jdbc:sqlite:chat_db.db";


  static
  {
	try
	{
	  Class.forName(DRIVER_JDBC);
	}
	catch (ClassNotFoundException e)
	{
	  e.printStackTrace();
	}
  }


  public AuthService(SessionManager sm)
  {
	sessionManager = sm;
	try
	{
	  dbConn = getConnection(DB_URL);
	}
	catch (SQLException e)
	{
	  throw new RuntimeException("Database connection error");
	}
  }


  private void authentication(Socket socket)
  {
	try
	{
	  DataInputStream inStream = new DataInputStream(socket.getInputStream());
	  DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

	  User user = tryAuthUserLoop(inStream, outStream);
	  sessionManager.connect(user, socket);
	}
	catch (IOException | AuthException e)
	{
	  e.printStackTrace();
	}
  }


  private User tryAuthUserLoop(DataInputStream inStream, DataOutputStream outStream)
  throws IOException, AuthException
  {
	while (!currentThread().isInterrupted())
	{
	  try
	  {
		String authMessage = inStream.readUTF();
		User user = authUser(authMessage);

		out.printf("Authorization for user %s successful%n", user.getLogin());
		outUTF(outStream, AUTH_SUCCESSFUL_MESSAGE);

		return user;
	  }
	  catch (AuthException e)
	  {
		out.println(e.getMessage());
		outUTF(outStream, AUTH_FAILS_MESSAGE);
	  }
	  catch (SQLException e)
	  {
		e.printStackTrace();
	  }
	}

	throw new AuthException("Authorization thread is interrupted");
  }


  private User authUser(String authMessage)
  throws AuthException, SQLException
  {
	Matcher matcher = AUTH_PATTERN.matcher(authMessage);

	if (!matcher.matches())
	  throw new AuthException("Incorrect authorization message: " + authMessage);

	String login = matcher.group(1);
	String pass = matcher.group(2);

	String sql = "SELECT * FROM chat_user WHERE login = ? and password = ?";
	PreparedStatement ps = dbConn.prepareStatement(sql);
	ps.setString(1, login);
	ps.setString(2, pass);

	ResultSet rs = ps.executeQuery();

	if (!rs.next())
	  throw new AuthException("Authorization for user " + login + " failed");

	User user = new User(login);

	if (sessionManager.isConnected(user))
	  throw new AuthException("User " + user.getLogin() + " connected already");

	return user;
  }


  public void close()
  {
	try
	{
	  dbConn.close();
	}
	catch (SQLException e)
	{
	  e.printStackTrace();
	}
  }


  public void startForNewConnection(Socket socket)
  {
	new Thread(() -> authentication(socket)).start();
  }

}