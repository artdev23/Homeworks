import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class ArrayFunctionsTest1
{

  private ArrayFunctions inst;

  private static final int time = 1;


  @Before
  public void init()
  {
	inst = new ArrayFunctions();
  }


  @Test(timeout = time)
  public void test_tail1()
  {
	int[] array = {1, 2, 4, 4, 2, 3, 4, 1, 7};
	int[] tail = inst.tail(array, 4);
	int[] exp = {1, 7};
	assertArrayEquals(exp, tail);
  }


  @Test(timeout = time, expected = RuntimeException.class)
  public void test_tail2()
  {
	int[] array = {1, 2, 2, 3, 1, 7};
	inst.tail(array, 4);
  }


  @Test(timeout = time, expected = IllegalArgumentException.class)
  public void test_tail3()
  {
	int[] array = {};
	inst.tail(array, 4);
  }


  @Test(timeout = time)
  public void test_tail4()
  {
	int[] array = {4, 4, 4};
	int[] tail = inst.tail(array, 4);
	int[] exp = {};
	assertArrayEquals(exp, tail);
  }

}