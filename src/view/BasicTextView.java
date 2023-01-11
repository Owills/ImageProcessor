package view;

import java.io.IOException;

import model.ImageProcessingModel;

/**
 * basic text view  responsible with receiving error messages.
 */
public class BasicTextView implements TextView {
  protected final ImageProcessingModel im;
  private final Appendable destination;

  /**
   * Given a BasicImageProcessingModel creates a TextView with the output as System.out.
   *
   * @param im the BasicImageProcessingModel
   * @throws IllegalArgumentException if the given model is null
   */
  public BasicTextView(ImageProcessingModel im) throws IllegalArgumentException {
    if (im == null) {
      throw new IllegalArgumentException("Given model cannot be null");
    }
    this.im = im;
    this.destination = System.out;
  }

  /**
   * Given a BasicImageProcessingModel creates a TextView with the given output.
   *
   * @param im          the BasicImageProcessingModel
   * @param destination the place to transmit output
   * @throws IllegalArgumentException if the given model or appendable is null
   */
  public BasicTextView(ImageProcessingModel im, Appendable destination)
          throws IllegalArgumentException {
    if (im == null) {
      throw new IllegalArgumentException("Given model cannot be null");
    }
    if (destination == null) {
      throw new IllegalArgumentException("Given destination cannot be null");
    }
    this.im = im;
    this.destination = destination;
  }

  /**
   * Transmits a message to the destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if the Transmission fails
   */
  public void renderMessage(String message) throws IOException {
    try {
      this.destination.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission to destination failed");
    }
  }
}

