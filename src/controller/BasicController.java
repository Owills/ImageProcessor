package controller;

import model.ImageProcessingModel;
import view.TextView;

/**
 * represents a controller for image processing taking in commands such as
 * load 'filename' 'name'
 * save 'filename' 'name'
 * darken 'amount' 'name' 'new name'
 * brighten 'amount' 'name' 'new name'
 * horizontal-flip 'name' 'new name'
 * vertical-flip 'name' 'new name'
 * value-component 'name' 'new name'
 * intensity-component 'name' 'new name'
 * luma-component 'name' 'new name'
 * red-component 'name' 'new name'
 * green-component 'name' 'new name'
 * blue-component 'name' 'new name'
 * quit.
 */
public class BasicController extends AbstractController {

  protected final ImageProcessingModel model;

  /**
   * given a ImageProcessingModel, TextView, and Readable creates a BasicController.
   *
   * @param model the image processing model
   * @param view  the view to send error messages
   * @param input the script of commands
   * @throws IllegalArgumentException if the given arguments are null
   */
  public BasicController(ImageProcessingModel model, TextView view, Readable input)
          throws IllegalArgumentException {
    super(view, input);
    if (model == null) {
      throw new IllegalArgumentException("given model must not be null");
    }
    this.model = model;
  }

  @Override
  public ImageProcessingModel getModel() {
    return this.model;
  }
}
