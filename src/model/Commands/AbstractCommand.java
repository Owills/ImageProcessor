package model.Commands;

import java.util.Objects;

import model.Color;

/**
 * represents basic functionality for any command with preview features.
 */
public abstract class AbstractCommand implements Command {

  protected Color[][] mask;
  protected boolean hasMask;

  /**
   * creates an command.
   */
  public AbstractCommand() {
    this.hasMask = false;
  }

  public void setMask(Color[][] mask) throws IllegalArgumentException {
    for (int i = 0; i < mask.length; i ++ ) {
      for (int j = 0; j < mask[0].length; j ++ ) {
        if (mask[i][j].red != 0 || mask[i][j].green != 0 || mask[i][j].blue != 0 ) {
          if (mask[i][j].red != 255 || mask[i][j].green != 255 || mask[i][j].blue != 255) {
            throw new IllegalArgumentException("Mask must be black and white");
          }
        }
      }
    }
    this.mask = mask;
    this.hasMask = true;
  }

  /**
   * given an image, creates a full black mask.
   * @param image the image
   */
  protected void setFullPaintMask(Color[][] image) {
    this.mask = new Color[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        mask[i][j] = new Color(0,0,0);
      }
    }
  }

  /**
   * checks that the mask has correct properties.
   * @param image the image being checked
   * @throws IllegalArgumentException if the image has incorrect size
   */
  protected void checkMaskProperties(Color[][] image) throws IllegalArgumentException {
    if (!this.hasMask) {
      this.setFullPaintMask(image);
    }
    if (image.length != mask.length || image[0].length != mask[0].length) {
      throw new IllegalArgumentException("Mask must have same dimensions as image");
    }
  }

}
