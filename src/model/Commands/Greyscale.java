package model.Commands;

/**
 * represent a command that can greyscale an image.
 */
public class Greyscale extends ColorTransformation {

  /**
   * creates a ColorTransformation with a matrix based on luma greyscale.
   */
  public Greyscale() {
    super(new double[][]
            {{0.2126, 0.7152, 0.0722},
                    {0.2126, 0.7152, 0.0722},
                    {0.2126, 0.7152, 0.0722}});
  }
}
