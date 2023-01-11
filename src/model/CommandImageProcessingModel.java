package model;

import model.Commands.Command;

/**
 * image processing model that can take in commands in line with the command design pattern.
 */
public interface CommandImageProcessingModel extends ImageProcessingModel {


  /**
   * accepts a command and executes the command on the given image, and saves it to a new image.
   *
   * @param name    the image to execute the command on
   * @param newName the name to save the new image as
   * @param c       the command
   * @throws IllegalArgumentException if the image does not exist
   */
  void accept(String name, String newName, Command c) throws IllegalArgumentException;

}
