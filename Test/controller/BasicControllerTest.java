package controller;

import model.BasicImageProcessingModel;
import model.ImageProcessingModel;
import view.TextView;


/**
 * tests for the BasicController class.
 */
public class BasicControllerTest extends AbstractControllerTest {


  @Override
  public ImageProcessingModel setModel() {
    return new BasicImageProcessingModel();
  }

  @Override
  public ImageProcessingController setController(ImageProcessingModel model,
                                                 TextView view, Readable input) {
    return new BasicController(model, view, input);
  }


}