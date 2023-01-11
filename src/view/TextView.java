package view;

import java.io.IOException;

/**
 * represents the view for an image processor, responsible for transmitting output.
 */
public interface TextView {

  /**
   * Transmits a message to the destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if the Transmission fails
   */
  public void renderMessage(String message) throws IOException;
}
