package model.Commands;

import model.Color;
import model.Commands.Command;

/**
 * command for downscaling and image.
 */
public class DownScale extends AbstractCommand {

  private final int width;
  private final int height;

  /**
   * given a width and height creates a downscale command.
   * @param width the width of the new image
   * @param height the height of the new image
   * @throws IllegalArgumentException if the width or height are not positive
   */
  public DownScale (int width, int height) throws IllegalArgumentException {
    super();
    if (height < 1 || width < 1) {
      throw new IllegalArgumentException("Please give a valid image size to downsize too");
    }
    this.height = height;
    this.width = width;
  }

  @Override
  public void setMask(Color[][] mask) throws IllegalArgumentException {
    throw new IllegalArgumentException("Cannot Perform Partial Downscale");
  }


  @Override
  public Color[][] execute(Color[][] image) throws IllegalArgumentException {
    this.checkMaskProperties(image);
    if (height > image.length || width> image[0].length) {
      throw new IllegalArgumentException("Downsize size is bigger than given image");
    }
    Color[][] newImage = new Color[height][width];

    for (int i = 0; i < newImage.length; i++) {
      for (int j = 0; j < newImage[0].length; j++) {
        double x = ((j * image[0].length * 1.0) / newImage[0].length);
        double y = ((i * image.length * 1.0) / newImage.length);

        int xFloor = (int) Math.floor(x);
        int xCeil = (int) Math.ceil(x);

        int yFloor = (int) Math.floor(y);
        int yCeil = (int) Math.ceil(y);


        if (yFloor != yCeil &&  xFloor != xCeil) {
          Color a = image[yFloor][xFloor];
          Color b = image[yFloor][xCeil];
          Color c = image[yCeil][xFloor];
          Color d =  image[yCeil][xCeil];

          //red
          double m = b.red * (x - Math.floor(x))  + a.red * (Math.ceil(x) - x);
          double n = d.red * (x - Math.floor(x))  + c.red * (Math.ceil(x) - x);
          int red = (int) (n * (y - Math.floor(y)) + m * (Math.ceil(y) - y));

          //blue
          m = b.blue * (x - Math.floor(x))  + a.blue * (Math.ceil(x) - x);
          n = d.blue * (x - Math.floor(x))  + c.blue * (Math.ceil(x) - x);
          int blue = (int) (n * (y - Math.floor(y)) + m * (Math.ceil(y) - y));

          //green
          m = b.green * (x - Math.floor(x))  + a.green * (Math.ceil(x) - x);
          n = d.green * (x - Math.floor(x))  + c.green * (Math.ceil(x) - x);
          int green = (int) (n * (y - Math.floor(y)) + m * (Math.ceil(y) - y));

          newImage[i][j] = new Color(red, green, blue);
        } else {
          newImage[i][j] = new Color(image[yFloor][xFloor].red, image[yFloor][xFloor].green,
                image[yFloor][xFloor].blue);
        }
      }
    }
    return newImage;
  }
}
