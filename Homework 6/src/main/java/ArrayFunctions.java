import static java.util.Arrays.*;


public class ArrayFunctions
{

  int[] tail(int[] arr, int afterLast)
  {
	if (arr == null || arr.length == 0)
	  throw new IllegalArgumentException("Empty array");

	int index = -1;
	int last = arr.length - 1;

	for (int i = last; i >= 0; i--)
	{
	  if (arr[i] == afterLast)
	  {
		index = i;
		break;
	  }
	}

	if (index == -1)
	  throw new RuntimeException("Array no contains " + afterLast);

	int tailSize = last - index;
	int[] tail = new int[tailSize];

	if (tailSize > 0)
	  tail = copyOfRange(arr, index + 1, arr.length);

	return tail;
  }


  boolean isOnlyTwoNumbers(int[] arr, int num1, int num2)
  {
	if (arr == null || arr.length == 0)
	  throw new IllegalArgumentException("Empty array");

	if (num1 == num2)
	  throw new IllegalArgumentException("Numbers equals");

	if (arr.length == 1)
	  return false;

	sort(arr);

	if (binarySearch(arr, num1) < 0)
	  return false;

	if (binarySearch(arr, num2) < 0)
	  return false;

	boolean result = true;
	for (int x : arr)
	{
	  if (x != num1 && x != num2)
		result = false;
	}

	return result;
  }


}