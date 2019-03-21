import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ArrayFunctionsTest2
{

  private ArrayFunctions inst;

  private static final int time = 1;


  @Before
  public void init()
  {
	inst = new ArrayFunctions();
  }


  @Test(timeout = time)
  public void test_isOnlyTwoNumbers1()
  {
	int[] array = {1,1,1,4,4,1,4,4};
	assertTrue(inst.isOnlyTwoNumbers(array, 1, 4));
  }


  @Test(timeout = time)
  public void test_isOnlyTwoNumbers2()
  {
	int[] array = {1,1,1,1,1,1};
	assertFalse(inst.isOnlyTwoNumbers(array, 1, 4));
  }


  @Test(timeout = time)
  public void test_isOnlyTwoNumbers3()
  {
	int[] array = {4,4,4,4};
	assertFalse(inst.isOnlyTwoNumbers(array, 1, 4));
  }


  @Test(timeout = time)
  public void test_isOnlyTwoNumbers4()
  {
	int[] array = {1,4,4,1,1,4,3};
	assertFalse(inst.isOnlyTwoNumbers(array, 1, 4));
  }


  @Test(timeout = time, expected = IllegalArgumentException.class)
  public void test_isOnlyTwoNumbers5()
  {
	int[] array = {};
	inst.isOnlyTwoNumbers(array, 1, 4);
  }


  @Test(timeout = time, expected = IllegalArgumentException.class)
  public void test_isOnlyTwoNumbers6()
  {
	int[] array = {};
	inst.isOnlyTwoNumbers(array, 1, 1);
  }

}