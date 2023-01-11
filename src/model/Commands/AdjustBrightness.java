package model.Commands;

import model.Color;

public class AdjustBrightness extends AbstractCommand {
  private final int amount;

  public AdjustBrightness(int amount) {
    super();
    this.amount = amount;
  }

  @Override
  public Color[][] execute(Color[][] image) throws IllegalArgumentException {
    this.checkMaskProperties(image);
    Color[][] newImage = new Color[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        if (mask[i][j].red == 0 && mask[i][j].green == 0 && mask[i][j].blue == 0) {
          int r = image[i][j].red + amount;
          int g = image[i][j].green + amount;
          int b = image[i][j].blue + amount;
          newImage[i][j] = new Color(r, g, b);
        } else {
          newImage[i][j] = image[i][j];
        }
      }
    }
    return newImage;
  }
}
