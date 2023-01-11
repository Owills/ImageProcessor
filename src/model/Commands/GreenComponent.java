package model.Commands;

public class GreenComponent extends ColorTransformation {

  /**
   * creates a ColorTransformation with only the red component of a matrix.
   */
  public GreenComponent() throws IllegalArgumentException {
    super(new double[][]
            {{0.0, 1.0, 0.0},
                    {0.0, 1.0, 0.0},
                    {0.0, 1.0, 0.0}});
  }
}
