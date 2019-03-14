import static java.lang.System.*;


public class Exc1
{

  private final Object lock = new Object();
  private volatile char currentLetter = 'A';


  public static void main(String[] args)
  {
	Exc1 exc1 = new Exc1();
	Thread t1 = new Thread(() -> exc1.printA());
	Thread t2 = new Thread(() -> exc1.printB());
	Thread t3 = new Thread(() -> exc1.printC());
	t1.start();
	t2.start();
	t3.start();
  }


  public void printA()
  {
	synchronized (lock)
	{
	  try
	  {
		for (int i = 0; i < 5; i++)
		{
		  while (currentLetter != 'A')
			lock.wait();

		  out.print("A");
		  currentLetter = 'B';
		  lock.notifyAll();
		}
	  }
	  catch (InterruptedException e)
	  {
		e.printStackTrace();
	  }
	}
  }


  public void printB()
  {
	synchronized (lock)
	{
	  try
	  {
		for (int i = 0; i < 5; i++)
		{
		  while (currentLetter != 'B')
			lock.wait();

		  out.print("B");
		  currentLetter = 'C';
		  lock.notifyAll();
		}
	  }
	  catch (InterruptedException e)
	  {
		e.printStackTrace();
	  }
	}
  }


  public void printC()
  {
	synchronized (lock)
	{
	  try
	  {
		for (int i = 0; i < 5; i++)
		{
		  while (currentLetter != 'C')
			lock.wait();

		  out.print("C");
		  currentLetter = 'A';
		  lock.notifyAll();
		}
	  }
	  catch (InterruptedException e)
	  {
		e.printStackTrace();
	  }
	}
  }

}