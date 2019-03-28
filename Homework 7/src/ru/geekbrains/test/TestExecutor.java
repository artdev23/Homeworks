package ru.geekbrains.test;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.stream.Collectors.toList;


public class TestExecutor
{

  private final Class[] classes;


  public TestExecutor(Class... classes)
  {
	this.classes = classes;
  }


  public void start()
  {
    for (Class c : classes)
    {
      try
      {
        out.println(c.getSimpleName()+": test started");
        start(c);
      }
      catch (TestException e)
      {
        e.printStackTrace();
      }
    }
  }


  private void start(Class clazz)
  throws TestException
  {
    Method[] methods = clazz.getMethods();

    List<Method> methodsBefore = Stream.of(methods)
                                  .filter(x -> x.getAnnotation(BeforeSuite.class) != null)
                                  .collect(toList());

    List<Method> methodsTest = Stream.of(methods)
                                 .filter(x -> x.getAnnotation(Test.class) != null)
                                 .collect(toList());

    List<Method> methodsAfter = Stream.of(methods)
                                     .filter(x -> x.getAnnotation(AfterSuite.class) != null)
                                     .collect(toList());

    invokeBefore(clazz, methodsBefore);
    invokeAllTests(clazz, methodsTest);
    invokeAfter(clazz, methodsAfter);
  }


  private void invokeBefore(Class clazz, List<Method> methods)
  throws TestException
  {
    invokeSingle(clazz, methods, BeforeSuite.class);
  }


  private void invokeAfter(Class clazz, List<Method> methods)
  throws TestException
  {
    invokeSingle(clazz, methods, AfterSuite.class);
  }


  private void invokeAllTests(Class clazz, List<Method> methods)
  throws TestException
  {
    if (methods.isEmpty())
      return;

    Object inst = createInstance(clazz);

    Comparator<Method> c = (o1,o2) ->
    {
      int pr1 = o1.getAnnotation(Test.class).priority();
      int pr2 = o2.getAnnotation(Test.class).priority();
      return pr1 - pr2;
    };
    methods.sort(c);

    for (Method m : methods)
    {
      execMethod(inst, m);
    }
  }


  private void invokeSingle(Class clazz, List<Method> methods, Class annot)
  throws TestException
  {
    if (methods.size() > 1)
    {
      throw new TestException(clazz.getSimpleName() + ": must be single @" +
                              annot.getSimpleName() + " method");
    }

    if (methods.size() == 1)
    {
      Object inst = createInstance(clazz);
      Method m = methods.get(0);
      execMethod(inst, m);
    }
  }


  private void execMethod(Object inst, Method m)
  throws TestException
  {
    checkSignature(m);

    try
    {
      m.invoke(inst, (Object[]) null);
    }
    catch (IllegalAccessException | InvocationTargetException e)
    {
      throw new TestException(e);
    }
  }


  private Object createInstance(Class clazz)
  throws TestException
  {
    try
    {
      return clazz.newInstance();
    }
    catch (InstantiationException | IllegalAccessException e)
    {
      throw new TestException(clazz.getSimpleName() + ": must be public default constructor");
    }
  }


  private void checkSignature(Method method)
  throws TestException
  {
    String name = method.getName();

    if (method.getReturnType() != void.class)
    {
      throw new TestException(name + ": return type must be a void");
    }

    if (method.getParameterCount() > 0)
    {
      throw new TestException(name + ": must not be parameters");
    }
  }

}