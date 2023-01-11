package model;


/**
 * tests for basic image processing model class.
 */
public class TestBasicImageProcessingModel extends AbstractModelTests {


  @Override
  public ImageProcessingModel setModel() {
    return new BasicImageProcessingModel();
  }


}
