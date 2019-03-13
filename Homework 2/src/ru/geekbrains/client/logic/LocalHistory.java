package ru.geekbrains.client.logic;


import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class LocalHistory
{

  private final UIhandler ui;
  private final String fileName;

  private static final String USERNAME_STUB = "{username}";
  private static final String fileNamePattern = "history_" + USERNAME_STUB + ".txt";
  private static final Charset CS = Charset.forName("UTF-8");
  private static final int recentCount = 100;


  public LocalHistory(String username, UIhandler ui)
  {
	this.ui = ui;
	fileName = fileNamePattern.replace(USERNAME_STUB, username);
  }


  public void save()
  throws IOException
  {
	List<Message> messages = ui.getAllMessages();

	try (
			OutputStream stream = new FileOutputStream(fileName, true);
			Writer out = new OutputStreamWriter(stream, CS);
			BufferedWriter writer = new BufferedWriter(out))
	{

	  int count = messages.size();
	  messages = messages.subList(recentCount, count);

	  for (Message msg : messages)
	  {
		String str = msg.toString();
		writer.append(str);
		writer.newLine();
	  }
	}
  }


  public void load()
  throws IOException
  {
	try (
			InputStream stream = new FileInputStream(fileName);
			Reader in = new InputStreamReader(stream, CS);
			BufferedReader reader = new BufferedReader(in))
	{

	  Stream<String> lines = reader.lines();
	  long count = lines.count();
	  lines = lines.skip(count - recentCount);

	  List<Message> msgs = lines
								   .map(x -> Message.fromString(x))
								   .collect(toList());

	  ui.addMessages(msgs);
	}
  }


}