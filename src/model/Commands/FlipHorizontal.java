package model.Commands;

import model.Color;

public class FlipHorizontal extends AbstractCommand {

  public FlipHorizontal () {
    super();
  }


  @Override
  public Color[][] execute(Color[][] image) throws IllegalArgumentException {
    this.checkMaskProperties(image);
    Color[][] newImage = new Color[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        if (mask[i][j].red == 0 && mask[i][j].green == 0 && mask[i][j].blue == 0) {
          newImage[i][j] = image[i][image[0].length - j - 1];
        } else {
          newImage[i][j] = image[i][j];
        }
      }
    }
    return newImage;
  }
}
