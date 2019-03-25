package ru.geekbrains.test;


public class TestException
		extends Exception
{

  public TestException(String msg)
  {
    super(msg);
  }


  public TestException(Exception cause)
  {
    super("Unexpected exception", cause);
  }

}