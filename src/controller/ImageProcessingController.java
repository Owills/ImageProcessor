package controller;

/**
 * Represents a controller for an image processor.
 */
public interface ImageProcessingController {

  /**
   * runs a new image processor.
   *
   * @throws IllegalStateException if the controller is unable to read input or transmit output
   */
  void processImages() throws IllegalStateException;
}
