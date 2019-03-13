package ru.geekbrains.client.logic;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.text.MessageFormat.format;
import static java.time.format.DateTimeFormatter.*;


public class Message
{

  private final String userFrom;
  private final String userTo;
  private final String text;
  private final LocalDate date;

  private final DateTimeFormatter dateFormatter = ofPattern("dd.MM.yyyy hh:mm:ss");


  public Message(String userFrom, String userTo, String text)
  {
	this.userFrom = userFrom;
	this.userTo = userTo;
	this.text = text;
	this.date = LocalDate.now();
  }


  public String getUserFrom()
  {
	return userFrom;
  }


  public String getUserTo()
  {
	return userTo;
  }


  public String getText()
  {
	return text;
  }


  public LocalDate getDate()
  {
	return date;
  }


  @Override
  public String toString()
  {
	String pattern = "[{2} from {0}: {1}]";
	String dt = date.format(dateFormatter);
	String str = format(pattern, userFrom, text, dt);

	return str;
  }


  public static Message fromString(String str)
  {
	return null;
  }

}