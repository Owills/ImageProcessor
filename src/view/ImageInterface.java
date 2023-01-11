package view;

import model.Color;

/**
 * interface responsible for drawing images with JScrollPanels (the image and its histograms).
 */
public interface ImageInterface {

  /**
   * sets the current image to draw from.
   * @param image the image
   */
  void setImage(Color[][] image);
}
