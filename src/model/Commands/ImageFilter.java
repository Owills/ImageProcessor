package model.Commands;

import model.Color;
import model.Colors;
import model.Commands.Command;
import model.Commands.Filter;

/**
 * represents a command that updates an image based on a filter.
 */
public abstract class ImageFilter extends AbstractCommand {
  private final double[][] filter;

  public ImageFilter(double[][] filter) {
    super();
    this.filter = filter;
  }

  /**
   * updates an entire image based on a filter, without changing the original image.
   *
   * @param image the image to be executed upon
   * @return a new image.
   */
  @Override
  public Color[][] execute(Color[][] image) {

    this.checkMaskProperties(image);
    Color[][] newImage = new Color[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        Color original = image[i][j];
        if (mask[i][j].red == 0 && mask[i][j].green == 0 && mask[i][j].blue == 0) {
          int r = new Filter(i, j, filter, Colors.RED).execute(image)[i][j].red;
          int g = new Filter(i, j, filter, Colors.GREEN).execute(image)[i][j].green;
          int b = new Filter(i, j, filter, Colors.BLUE).execute(image)[i][j].blue;
          newImage[i][j] = new Color(r, g, b);
          image[i][j] = original;
        } else {
          newImage[i][j] = original;
        }
      }
    }

    return newImage;
  }

}
