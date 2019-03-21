package lesson5;


import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static java.text.MessageFormat.format;


public class Tunnel
		extends Stage
{

  public Tunnel()
  {
	length = 80;
	description = "Тоннель " + length + " метров";
  }


  @Override
  public void go(Car car)
  {
	String name = car.getName();

	try
	{
	  out.println(format("{0} готовится к этапу(ждет): {1}", name, description));

	  out.println(format("{0} начал этап: {1}", name, description));

	  int millis = length / car.getSpeed() * 1000;
	  sleep(millis);
	}
	catch (Exception e)
	{
	  e.printStackTrace();
	}
	finally
	{
	  out.println(format("{0} закончил этап: {1}", name, description));
	}
  }

}