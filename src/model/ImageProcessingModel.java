package model;

import java.io.File;

/**
 * This interface represents the operations offered by image processing model
 * (can process multiple images at once).
 */
public interface ImageProcessingModel {

  /**
   * Load an image from an ASCII PPM.
   *
   * @param filename the filename of the image to be loaded.
   * @param name     the name to refer to the image as
   * @throws IllegalArgumentException is the given file does not exist or is invalid
   */
  public void load(String filename, String name) throws IllegalArgumentException;

  /**
   * Load an image from an ASCII PPM, JPEG BPM.
   *
   * @param file the file of the image to be loaded.
   * @param name the name to refer to the image as
   * @throws IllegalArgumentException is the given file does not exist or is invalid
   */
  public void load(File file, String name) throws IllegalArgumentException;

  /**
   * Save an image to an ASCII PPM, if the given file exists, rewrites the file
   * otherwise save the image in a new file.
   *
   * @param filename the name of the file to save the image to.
   * @param name     the image to be saved
   * @throws IllegalArgumentException if the image does not exist
   */
  public void save(String filename, String name) throws IllegalArgumentException;

  /**
   * Adds the given value to the red, green, blue values capping at 255 (or zero if the value
   * is negative / darkens the image).
   *
   * @param change  the change in brightness of the image
   * @param name    the image to be edited
   * @param newName the name to refer to the new image as
   * @throws IllegalArgumentException if the image does not exist
   */
  public void adjustBrightness(int change, String name, String newName)
          throws IllegalArgumentException;

  /**
   * flips an image horizontally.
   *
   * @param name    the image to be edited
   * @param newName the name to refer to the new image as
   * @throws IllegalArgumentException if the image does not exist
   */
  public void flipHorizontal(String name, String newName)
          throws IllegalArgumentException;

  /**
   * flips an image vertically.
   *
   * @param name    the image to be edited
   * @param newName the name to refer to the new image as
   * @throws IllegalArgumentException if the image does not exist
   */
  public void flipVertical(String name, String newName)
          throws IllegalArgumentException;

  /**
   * Visualizes individuals channels to create greyscale images.
   *
   * @param type    the channel (RED, GREEN, or BLUE)
   * @param name    the image to be edited
   * @param newName the name to refer to the new image as
   * @throws IllegalArgumentException if the image does not exist
   */
  public void greyscale(Colors type, String name, String newName)
          throws IllegalArgumentException;

  /**
   * Creates a greyscale image by visualizing the brightness of an image.
   *
   * @param type    the brightness type (VALUE, INTENSITY, or LUMA)
   * @param name    the image to be edited
   * @param newName the name to refer to the new image as
   * @throws IllegalArgumentException if the image does not exist
   */
  public void greyscale(Brightnesses type, String name, String newName)
          throws IllegalArgumentException;


  /**
   * gets a copy of a pixel grid representation of an image.
   *
   * @param name the name of the image
   * @return 2d Color array representing an image
   * @throws IllegalArgumentException if the image does not exist
   */
  public Color[][] getImage(String name) throws IllegalArgumentException;
}
