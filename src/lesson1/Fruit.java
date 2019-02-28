package lesson1;


public abstract class Fruit
{

  abstract float getWeight();

}


class Apple
		extends Fruit
{

  private static final float WEIGHT = 1.0f;


  @Override
  float getWeight()
  {
	return WEIGHT;
  }

}


class Orange
		extends Fruit
{

  private static final float WEIGHT = 1.5f;


  @Override
  float getWeight()
  {
	return WEIGHT;
  }

}