package view;

/**
 * interface responsible for drawing the red, blue, green and intensity histograms of an image.
 */
public interface IHistogram extends ImageInterface {

  /**
   * gets an array where each elemnt corresponing to the list of red, blue, green and intensity
   * frequencies.
   * @return an array of array of frequencies.
   */
  int[][] getHistograms();
}
