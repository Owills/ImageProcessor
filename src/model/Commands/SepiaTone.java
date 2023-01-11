package model.Commands;

/**
 * represent of command the update the colors of an image based on the sepia tone.
 */
public class SepiaTone extends ColorTransformation {

  /**
   * creates a ColorTransformation with the matrix based on sepia tones.
   */
  public SepiaTone() {
    super(new double[][]
        {{0.393, 0.789, 0.189},
                {0.349, 0.686, 0.168},
                {0.272, 0.534, 0.131}});
  }
}
