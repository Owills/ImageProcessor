package model.Commands;

import model.Color;

/**
 * command interface where given an 2d array of colors representing an image performs a command.
 */
public interface Command {

  /**
   * given a 2d color array representation of an image execute this macro on it.
   *
   * @param image the image to be executed upon
   */
  Color[][] execute(Color[][] image) throws IllegalArgumentException;

  /**
   * sets the mask to the given image.
   * @param mask the image
   * @throws IllegalArgumentException if the mask is not black and white
   */
  void setMask(Color[][] mask) throws IllegalArgumentException;

}
