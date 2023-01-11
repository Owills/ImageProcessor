package model.Commands;

public class BlueComponent extends ColorTransformation {

  /**
   * creates a ColorTransformation with only the red component of a matrix.
   */
  public BlueComponent() throws IllegalArgumentException {
    super(new double[][]
            {{0.0, 0.0, 1.0},
                    {0.0, 0.0, 1.0},
                    {0.0, 0.0, 1.0}});
  }
}
