package lesson1;


import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.*;
import static java.util.Arrays.*;


public class App
{

  public static <T>
  void replaceTwoElem(T[] array, int index1, int index2)
  {
    T temp = array[index1];
	array[index1] = array[index2];
	array[index2] = temp;
  }


  public static <T>
  ArrayList<T> toList(T[] array)
  {
	ArrayList<T> result = new ArrayList<>(asList(array));

	return result;
  }


  public static void main(String... args)
  {
    Object[] arr = {1,2,3};
	replaceTwoElem(arr, 0, 2);
	out.println(Arrays.toString(arr));

	ArrayList<Object> list = toList(arr);
	out.println(list);
  }

}