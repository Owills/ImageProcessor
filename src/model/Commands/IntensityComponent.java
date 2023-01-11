package model.Commands;

public class IntensityComponent extends ColorTransformation {

  /**
   * creates a ColorTransformation with value component matrix.
   */

  public IntensityComponent()  {
    super(new double[][]
            {{1.0/3.0, 1.0/3.0, 1.0/3.0},
                    {1.0/3.0, 1.0/3.0, 1.0/3.0},
                    {1.0/3.0, 1.0/3.0, 1.0/3.0}});
  }
}
