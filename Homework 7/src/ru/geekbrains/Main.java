package ru.geekbrains;


import ru.geekbrains.test.TestExecutor;


public class Main
{

  public static void main(String[] args)
  {
    TestExecutor exec = new TestExecutor(
            TestClass2.class,
            TestClass1.class
    );
    exec.start();
  }

}