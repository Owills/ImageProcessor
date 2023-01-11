package model.Commands;

import model.Color;
import model.Colors;
import model.Commands.Command;

/**
 * represent of command the update a single pixel of an image based on a filter.
 */
public class Filter extends AbstractCommand {

  private final int row;
  private final int col;
  private final double[][] kernel;
  private final Colors colorComponent;


  /**
   * Creates a filter command.
   *
   * @param row            the row position of the pixel
   * @param col            the column position of the pixel
   * @param kernel         the filter
   * @param colorComponent which part of the pixel, r g b should be updated
   * @throws IllegalArgumentException if the kernel is invalid of odd
   */
  public Filter(int row, int col, double[][] kernel, Colors colorComponent)
          throws IllegalArgumentException {
    super();
    if (kernel.length % 2 != 1) {
      throw new IllegalArgumentException("Kernel must have odd dimensions");
    }
    for (double[] doubles : kernel) {
      if (doubles.length != kernel.length) {
        throw new IllegalArgumentException("Invalid Kernel");
      }
    }

    this.row = row;
    this.col = col;
    this.kernel = kernel;
    this.colorComponent = colorComponent;
  }

  /**
   * updates a pixel at the row, col position in the image based on the kernel and colorComponent.
   *
   * @param image the image to be executed upon
   * @return the image with the update pixel
   */
  @Override
  public Color[][] execute(Color[][] image) {
    this.checkMaskProperties(image);

    int num = (this.kernel.length - 1) / 2;
    int filterNum = 0;

    for (int i = 0; i < this.kernel.length; i++) {
      for (int j = 0; j < this.kernel[0].length; j++) {
        int r = this.row + (i - num);
        int c = this.col + (j - num);
        if (r >= 0 && c >= 0 && r < image.length && c < image[0].length) {
          if (this.colorComponent == Colors.RED) {
            filterNum += image[r][c].red * this.kernel[i][j];
          } else if (this.colorComponent == Colors.GREEN) {
            filterNum += image[r][c].green * this.kernel[i][j];
          } else if (this.colorComponent == Colors.BLUE) {
            filterNum += image[r][c].blue * this.kernel[i][j];
          }
        }
      }
    }

    // check if the row is actually in the image
    if (row >= 0 && col >= 0 && row < image.length && col < image[0].length) {
      if (mask[row][col].red == 0 && mask[row][col].green == 0 && mask[row][col].blue == 0) {
        if (this.colorComponent == Colors.RED) {
          image[row][col] = new Color(filterNum, image[row][col].green, image[row][col].blue);
        } else if (this.colorComponent == Colors.GREEN) {
          image[row][col] = new Color(image[row][col].red, filterNum, image[row][col].blue);
        } else if (this.colorComponent == Colors.BLUE) {
          image[row][col] = new Color(image[row][col].red, image[row][col].green, filterNum);
        }
      }

    }
    return image;
  }

}
