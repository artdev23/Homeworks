package lesson5;


import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static java.text.MessageFormat.format;


public class Road
		extends Stage
{

  public Road(int length)
  {
	this.length = length;
	description = "Дорога " + length + " метров";
  }


  @Override
  public void go(Car car)
  {
	try
	{
	  String name = car.getName();
	  out.println(format("{0} начал этап: {1}", name, description));

	  int millis = length / car.getSpeed() * 1000;
	  sleep(millis);

	  out.println(format("{0} закончил этап: {1}", name, description));
	}
	catch (InterruptedException e)
	{
	  e.printStackTrace();
	}
  }

}