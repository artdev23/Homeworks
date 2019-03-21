package lesson5;


import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static java.text.MessageFormat.format;


public class Car
		implements Runnable
{

  private final Race race;
  private final int speed;
  private final String name;

  private static int CARS_COUNT = 0;
  private static CyclicBarrier cb;
  private static CountDownLatch cdl;
  private static Semaphore smp;
  private static CountDownLatch cdlFinish;


  public Car(Race race, int speed)
  {
	this.race = race;
	this.speed = speed;
	CARS_COUNT++;
	name = "Участник #" + CARS_COUNT;
  }


  public static void syncStart()
  {
	if (cdl == null)
	{
	  cb = new CyclicBarrier(CARS_COUNT);
	  cdl = new CountDownLatch(CARS_COUNT);
	  cdlFinish = new CountDownLatch(CARS_COUNT);

	  return;
	}

	try
	{
	  cdl.await();
	}
	catch (InterruptedException e)
	{
	  e.printStackTrace();
	}
  }


  public static void limitCarsCountInTunnel(int count)
  {
	smp = new Semaphore(count);
  }


  public static void waitFinishAll()
  {
	try
	{
	  cdlFinish.await();
	}
	catch (InterruptedException e)
	{
	  e.printStackTrace();
	}
  }


  public String getName()
  {
	return name;
  }


  public int getSpeed()
  {
	return speed;
  }


  @Override
  public void run()
  {
	try
	{
	  out.println(format("{0} готовится", name));

	  int millis = 500 + (int) (random() * 800);
	  sleep(millis);

	  out.println(format("{0} готов", name));

	  cdl.countDown();
	  cb.await();
	}
	catch (Exception e)
	{
	  e.printStackTrace();
	}

	go();
	cdlFinish.countDown();
  }


  private void go()
  {
	List<Stage> stages = race.getStages();

	try
	{
	  for (int i = 0; i < stages.size(); i++)
	  {
		Stage stage = stages.get(i);

		if (stage instanceof Tunnel)
		  smp.acquire();

		stage.go(this);
	  }
	}
	catch (InterruptedException e)
	{
	  e.printStackTrace();
	}
	finally
	{
	  smp.release();
	}
  }

}