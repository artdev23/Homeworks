package lesson1;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.System.out;


public class Box<T extends Fruit>
{

  private List<T> fruits;

  private static final double WEIGHT_DELTA = 0.00001;


  public Box()
  {
	fruits = new ArrayList<>();
  }


  public void addFruit(T fruit)
  {
	fruits.add(fruit);
  }


  public float getWeight()
  {
	if (fruits.isEmpty())
	  return 0;

	float weightFruit = fruits.get(0).getWeight();
	int count = fruits.size();
	float weight = weightFruit * count;

	return weight;
  }


  public boolean compare(Box<?> other)
  {
	float diff = abs(getWeight() - other.getWeight());

	return diff < WEIGHT_DELTA;
  }


  public static boolean compare(Box<?> box1, Box<?> box2)
  {
	return box1.compare(box2);
  }


  public void moveFruitsTo(Box<T> other)
  {
	other.fruits.addAll(fruits);
	fruits.clear();
  }


  public static void main(String... args)
  {
	Box<Apple> boxWithApples = new Box<>();

	boxWithApples.addFruit(new Apple());
	boxWithApples.addFruit(new Apple());
	boxWithApples.addFruit(new Apple());
	out.println("Вес коробки с яблоками: " + boxWithApples.getWeight());


	Box<Orange> boxWithOranges = new Box<>();

	boxWithOranges.addFruit(new Orange());
	boxWithOranges.addFruit(new Orange());
	out.println("Вес коробки с апельсинами: " + boxWithOranges.getWeight());


	boolean eq = compare(boxWithApples, boxWithOranges);
	out.println("Веса коробок " + (eq ? "равны" : "отличаются"));


	Box<Apple> box = new Box<>();
	boxWithApples.moveFruitsTo(box);
	out.println("Вес коробки с яблоками после пересыпки в другую коробку: " + boxWithApples.getWeight());
  }

}