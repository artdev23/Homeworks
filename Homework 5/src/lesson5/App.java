package lesson5;


import java.util.stream.IntStream;

import static java.lang.Math.random;
import static java.lang.System.out;


public class App
{

  private static final int CARS_COUNT = 8;
  private static final String eventPrefix = "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>>";


  public static void main(String[] args)
  {
	out.printf("%n%s Подготовка!!!%n", eventPrefix);

	Stage[] stages = {
			new Road(60),
			new Tunnel(),
			new Road(40)
	};

	Race race = new Race();
	race.setStages(stages);

	Car[] cars = new Car[CARS_COUNT];
	for (int i = 0; i < CARS_COUNT; i++)
	{
	  int speed = 20 + (int) (random() * 10);
	  cars[i] = new Car(race, speed);
	}

	Car.syncStart();
	Car.limitCarsCountInTunnel(CARS_COUNT / 2);

	IntStream.range(0, CARS_COUNT)
			 .forEach(i -> new Thread(cars[i]).start());

	Car.syncStart();

	out.printf("%n%s Гонка началась!!!%n", eventPrefix);

	Car.waitFinishAll();
	out.printf("%n%s Гонка закончилась!!!%n", eventPrefix);
  }

}