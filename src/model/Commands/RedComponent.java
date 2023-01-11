package model.Commands;

public class RedComponent extends ColorTransformation {

  /**
   * creates a ColorTransformation with only the red component of a matrix.
   */
  public RedComponent() throws IllegalArgumentException {
    super(new double[][]
            {{1.0, 0.0, 0.0},
                    {1.0, 0.0, 0.0},
                    {1.0, 0.0, 0.0}});
  }
}
