package ru.geekbrains;


import ru.geekbrains.test.AfterSuite;
import ru.geekbrains.test.BeforeSuite;
import ru.geekbrains.test.Test;

import static java.lang.System.out;


public class TestClass1
{

  @BeforeSuite
  public void init()
  {
    out.println("init is running");
  }

  @Test(priority=10)
  public void test1()
  {
    out.println("test1 is running");
  }

  @Test
  public void test2()
  {
	out.println("test2 is running");
  }

  public void test3()
  {
	out.println("test3 is running");
  }

  @Test
  private void test4()
  {
	out.println("test4 is running");
  }

  @AfterSuite
  public void done()
  {
    out.println("done is running");
  }

}