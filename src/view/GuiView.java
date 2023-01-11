package view;

import controller.ControllerFeatures;
import model.Color;

/**
 * graphic user interface for a image processing editor.
 */
public interface GuiView {

  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated, and therefore it must be redrawn.
   */
  void refresh();

  /**
   * Display a message in a suitable area of the GUI.
   *
   * @param message the message to be displayed
   */
  void renderMessage(String message);

  /**
   * assign this view with a controller.
   * @param c controller
   */
  void accept(ControllerFeatures c);

  /**
   * updates the gui.
   * @param args command and its arguments
   */
  void updateGui(String[] args);

  /**
   * determine if a image processor is set to preview setting
   * @return true if the image should be previewed
   */
  boolean getPartial();

  /**
   * gets a mask image that will preview the command only on the part of the image shown.
   * @return a black and white mask image
   */
  Color[][] getMask();

  /**
   * set the display the preview to the given image.
   * @param preview the image
   */
  void setPreview(Color[][] preview);

  /**
   * updates the preview when the preview box has changed.
   */
  void updatePreview();
}
