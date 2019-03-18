package lesson5;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Race
{

  private List<Stage> stages;


  public Race()
  {
	stages = new ArrayList<>();
  }


  public void setStages(Stage... list)
  {
	stages = Arrays.asList(list);
  }


  public List<Stage> getStages()
  {
	return stages;
  }

}