package model.Commands;

/**
 * represent a command that can blur an image.
 */
public class Blur extends ImageFilter {

  /**
   * creates an image filter with the blur kernel.
   */
  public Blur() {
    super(new double[][]
        {{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
                {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
                {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}});
  }


}
