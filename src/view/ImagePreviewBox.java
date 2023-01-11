package view;

/**
 * image with 200 by 200 preview box.
 */
public interface ImagePreviewBox extends ImageInterface {

  /**
   * determine if a pixel is within the preview box
   * @param row the row position of the pixel
   * @param col the column position of the preview box
   * @return true if the pixel within the preview box
   */
  boolean isWithinPreview(int row, int col);
}
