package model.Commands;

import model.Color;
import model.Commands.Command;

/**
 * represents a command that can update the colors in an image based on a color matrix.
 */
public abstract class ColorTransformation extends AbstractCommand {
  private final double[][] matrix;

  /**
   * given a color matrix creates a ColorTransformation.
   *
   * @param matrix color matrix
   * @throws IllegalArgumentException if the matrix is invalid
   */
  public ColorTransformation(double[][] matrix) throws IllegalArgumentException {
    super();
    if (matrix.length != 3) {
      throw new IllegalArgumentException("matrix must have three dimensions");
    }
    for (double[] doubles : matrix) {
      if (doubles.length != matrix.length) {
        throw new IllegalArgumentException("Invalid matrix");
      }
    }
    this.matrix = matrix;
  }

  /**
   * updates an image based of the color matrix, by multiplying the r g b values of each pixel,
   * by the color matrix.
   *
   * @param image the image to be executed upon
   * @return an updated image.
   */
  public Color[][] execute(Color[][] image) {
    this.checkMaskProperties(image);
    Color[][] newImage = new Color[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        if (mask[i][j].red == 0 && mask[i][j].green == 0 && mask[i][j].blue == 0) {
          int r = image[i][j].red;
          int g = image[i][j].green;
          int b = image[i][j].blue;
          newImage[i][j] = new Color((int) (r * matrix[0][0] + g * matrix[0][1] + b * matrix[0][2]),
                  (int) (r * matrix[1][0] + g * matrix[1][1] + b * matrix[1][2]),
                  (int) (r * matrix[2][0] + g * matrix[2][1] + b * matrix[2][2]));
        } else {
          newImage[i][j] = image[i][j];
        }
      }
    }
    return newImage;
  }


}
