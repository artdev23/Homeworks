package ru.geekbrains;


import ru.geekbrains.test.BeforeSuite;
import ru.geekbrains.test.Test;

import static java.lang.System.out;


public class TestClass2
{

  @BeforeSuite
  public void init1()
  {
	out.println("init1 is running");
  }

  @BeforeSuite
  public void init2()
  {
	out.println("init2 is running");
  }

  @Test
  public void test1()
  {
	out.println("test1 is running");
  }

  @Test(priority=10)
  public void test2()
  {
	out.println("test2 is running");
  }

  @Test(priority=4)
  public void test3()
  {
	out.println("test3 is running");
  }

  @Test
  protected void test4()
  {
	out.println("test4 is running");
  }

}