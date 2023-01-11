package model.Commands;

import model.Color;

public class ValueComponent extends AbstractCommand {

  public ValueComponent () {
    super();
  }

  @Override
  public Color[][] execute(Color[][] image) {
    this.checkMaskProperties(image);
    Color[][] newImage = new Color[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        if (mask[i][j].red == 0 && mask[i][j].green == 0 && mask[i][j].blue == 0) {
          int c = image[i][j].green;
          if (image[i][j].red > c) {
            c = image[i][j].red;
          }
          if (image[i][j].blue > c) {
            c = image[i][j].blue;
          }
          newImage[i][j] = new Color(c, c, c);
        } else {
          newImage[i][j] = image[i][j];
        }
      }
    }
    return newImage;
  }


}
